package Personaje;

import Arma.Arma;
import Estado.Estado;
import AtaqueEspecial.AtaqueEspecial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Esta es la clase base (o clase padre) central del juego. Representa a cualquier personaje que pelee.
// Las diferentes clases como Joel, Ellie y Abby heredan de esta clase principal.
public class Personaje {

    // --- Identidad del personaje ---
    public String nombre; // El nombre del personaje (ej. "Joel")
    public TipodePersonaje tipoClase; // Enum para clasificar rápidamente al personaje

    // --- Estadísticas de Vida ---
    public int vidaMax; // Vida máxima que puede llegar a tener
    public int vidaActual; // La vida que tiene en cada momento del combate

    // --- Estadísticas de Recurso (Maná, Energía, etc.) ---
    public int recursoMax; // La cantidad máxima de recursos (para usar ataques especiales)
    public int recursoActual; // Recurso disponible en ese momento

    // --- Estadísticas base de combate ---
    public int ataqueBase; // Cuánto ataque natural tiene sin aplicar armas
    public int defensaBase; // Resistencia natural a los golpes

    // --- Equipamiento ---
    public Arma armaEquipada; // El arma que lleva en la mano para golpear

    // --- Colecciones (Listas) de habilidades y estados ---
    public List<Estado> estadosActivos; // Los efectos que tiene aplicados en este momento (quemaduras, curaciones...)
    public List<AtaqueEspecial> ataquesEspeciales; // Las habilidades que sabe usar este personaje

    // --- Cooldowns (Tiempos de recarga) ---
    // Usamos un Map (Diccionario) para guardar: <Nombre del ataque, Turnos que le faltan para estar listo>
    public Map<String, Integer> cooldowns;

    // --- Estado general del combate ---
    public boolean vivo; // Nos dice si sigue respirando o si ya perdió el combate
    public boolean defendiendo; // Nos dice si este turno ha elegido la acción de 'Defenderse'

    // Este es el constructor gigante. Es donde inicializamos a un personaje desde cero.
    public Personaje(String nombre, TipodePersonaje tipoClase, int vidaMax,
                     int recursoMax, int ataqueBase, int defensaBase) {
        // Guardamos todo en las variables de clase
        this.nombre = nombre;
        this.tipoClase = tipoClase;
        this.vidaMax = vidaMax;
        
        // Al nacer el personaje, su vida y recurso actuales son el máximo que se nos dijo.
        this.vidaActual = vidaMax;
        this.recursoMax = recursoMax;
        this.recursoActual = recursoMax;
        
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        
        // Empieza vivo y sin defenderse
        this.vivo = true;
        this.defendiendo = false;
        
        // Inicializamos las listas y diccionarios para que estén vacíos listos para usarse (evitando errores NullPointer)
        this.estadosActivos = new ArrayList<>();
        this.ataquesEspeciales = new ArrayList<>();
        this.cooldowns = new HashMap<>();
    }

    // Método sencillo que nos devuelve true si la vida actual es mayor que cero
    public boolean estaVivo() {
        return vidaActual > 0;
    }

    // Método para cuando el personaje recibe un golpe normal (calcula resistencia).
    public void recibirDanio(int cantidad) {
        // Si el personaje usó la acción de 'Defender', su defensa para este turno es el doble.
        // Si no, es la defensa normal.
        int defEfectiva = defendiendo ? defensaBase * 2 : defensaBase;
        
        // El daño real es lo que me pegan menos lo que resisto. 
        // Usamos Math.max para que nunca me hagan "daño negativo" (o sea, que me curen por tener mucha defensa).
        int danioReal = Math.max(cantidad - defEfectiva, 0);
        
        // Restamos el daño real a la vida. Igual que antes, Math.max evita bajar a vida negativa.
        vidaActual = Math.max(vidaActual - danioReal, 0);
        
        // Si la vida llegó a cero, cambiamos su estado a "muerto" (vivo = false).
        if (vidaActual == 0) vivo = false;
    }

    // A diferencia de recibirDanio(), este método ignora completamente la defensa (escudos / armaduras).
    // Suelen usarlo ataques especiales como el veneno (Daño en el tiempo DoT), o ataques letales.
    public void recibirDanioDirecto(int cantidad) {
        vidaActual = Math.max(vidaActual - cantidad, 0);
        if (vidaActual == 0) vivo = false;
    }

    // Este método cura al personaje sumándole vida.
    public void curar(int cantidad) {
        // Math.min nos asegura de que por mucho que le curemos, 
        // la vidaActual nunca va a pasarse de su tope máximo permitido (vidaMax).
        vidaActual = Math.min(vidaActual + cantidad, vidaMax);
    }

    // Método que intenta consumir maná/energía al usar un ataque especial.
    // Si no tiene suficiente, devuelve false. Si tiene, se lo resta y devuelve true.
    public boolean gastarRecurso(int coste) {
        if (recursoActual >= coste) {
            recursoActual -= coste;
            return true;
        }
        return false;
    }

    // Simple función para asignarle o cambiarle el arma que tiene en la mano.
    public void equiparArma(Arma arma) {
        this.armaEquipada = arma;
    }

    // Con este método le pegamos una "etiqueta" de estado (quemado, regenerando...)
    public void aplicarEstado(Estado estado) {
        // Primero, creamos un "Iterator" (un recorrido de la lista) 
        // para buscar si el personaje ya tenía ese mismo estado aplicado de antes.
        Iterator<Estado> it = estadosActivos.iterator();
        while (it.hasNext()) {
            if (it.next().getNombre().equals(estado.getNombre())) {
                // Si el estado ya estaba, lo borramos. 
                // Así hacemos que el estado se renueve el tiempo (no se pueden acumular infinitos del mismo).
                it.remove();
                break;
            }
        }
        
        // Añadimos el nuevo estado a la lista
        estadosActivos.add(estado);
        // Exigimos que el estado haga lo que hace cuando se aplica por primera vez
        estado.alAplicar(this);
    }

    // Este mecanismo procesa cómo afectan los estados activos que tiene el personaje cada inicio de turno.
    public void procesarEstados() {
        // Si está muerto, no tiene sentido aplicar venenos ni curas.
        if (!estaVivo()) return;
        
        Iterator<Estado> it = estadosActivos.iterator();
        while (it.hasNext()) {
            Estado e = it.next();
            
            // Cada estado hace su efecto en este turno (quitar vida, curar...)
            e.alProcesarTurno(this);
            // El estado reduce en 1 su duración
            e.reducirDuracion();
            
            // Si después de reducirse le toca desaparecer...
            if (e.haExpirado()) {
                e.alExpirar(this);
                // Lo borramos de la lista de forma segura usando el iterador.
                it.remove();
            }
            // Si justo uno de estos efectos le ha matado (ej. veneno fuerte), dejamos de calcular el resto.
            if (!estaVivo()) break;
        }
    }

    // Esto ocurre en cada turno: reduce en 1 todos los tiempos de espera de sus ataques especiales.
    public void reducirCooldowns() {
        // Recorremos el diccionario usando Map.Entry
        Iterator<Map.Entry<String, Integer>> it = cooldowns.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            // Le quitamos 1 al número de turnos que le faltaban a ese ataque especial
            int nuevo = entry.getValue() - 1;
            
            // Si ya no le queda tiempo de espera, lo borramos de la lista de cooldowns "está libre"
            if (nuevo <= 0) it.remove();
            // Si la cuenta atrás sigue, guardamos el nuevo tiempo de espera
            else entry.setValue(nuevo);
        }
    }

    // Este es un método bonito para mostrar la "Ficha Técnica de Resumen" del personaje en la consola.
    public String resumenCombate() {
        StringBuilder sb = new StringBuilder(); // Usamos StringBuilder para ir pegando pedazos de texto eficientemente
        
        sb.append(nombre).append(" [").append(tipoClase).append("]");
        sb.append(" | HP: ").append(vidaActual).append("/").append(vidaMax);
        
        if (recursoMax > 0)
            sb.append(" | Rec: ").append(recursoActual).append("/").append(recursoMax);
            
        if (armaEquipada != null)
            sb.append(" | Arma: ").append(armaEquipada.descripcion());
            
        // Si tiene algún estado puesto, también lo mostramos uno a uno
        if (!estadosActivos.isEmpty()) {
            sb.append(" | Estados: ");
            for (int i = 0; i < estadosActivos.size(); i++) {
                Estado e = estadosActivos.get(i);
                sb.append(e.getNombre()).append("(").append(e.getTurnosRestantes()).append("t)");
                if (i < estadosActivos.size() - 1) sb.append(", ");
            }
        }
        return sb.toString();
    }

    // Si alguien hace "print(Personaje)", en lugar del texto basura de memoria de Java, enseñamos el bonito de arriba.
    @Override
    public String toString() {
        return resumenCombate();
    }
}