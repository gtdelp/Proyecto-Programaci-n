package AtaqueEspecial;

import Personaje.Personaje;

// Esta es la clase principal (o clase padre) de la que van a heredar todos los ataques, hechizos y habilidades especiales.
// Las demás clases como DanoDirecto, CuracionDirecta, DoT y HoT se basan en esta estructura.
public class AtaqueEspecial {

    // Declaramos las propiedades básicas que todo ataque va a tener en el juego:
    public String nombre; // El nombre del ataque (ej. Bola de Fuego)
    public int costeDeRecurso; // Cuánto maná, energía o ira cuesta usar la habilidad
    public String descripcion; // Una breve explicación de qué hace el ataque
    public int cooldownMax; // Cuántos turnos hay que esperar para volver a usarlo (tiempo de recarga)
    public int potenciaBase; // Qué tan fuerte es el ataque (el valor del daño o la curación que hace)
    public TipoObjetivo tipoObjetivo; // A quién se le lanza el ataque (a un aliado o a un enemigo)

    // Creamos un Enum (una lista de opciones fijas) para saber a quién podemos atacar.
    // Solo puede ser un enemigo (para hacer daño) o un aliado (para curar o poner ventajas).
    public enum TipoObjetivo {
        ENEMIGO,
        ALIADO
    }

    // Este es el constructor. Sirve para crear (o instanciar) un nuevo ataque especial 
    // dándole los valores iniciales cada vez que creamos uno en el juego.
    public AtaqueEspecial(String nombre, int costeDeRecurso, String descripcion,
                          int cooldownMax, int potenciaBase) {
        // Asignamos los valores que llegan por parámetro a las propiedades de nuestra clase (usando 'this')
        this.nombre = nombre;
        this.costeDeRecurso = costeDeRecurso;
        this.descripcion = descripcion;
        this.cooldownMax = cooldownMax;
        this.potenciaBase = potenciaBase;
        
        // Por defecto, asumimos que los ataques son contra enemigos. 
        // Si el ataque es de curación (para un aliado), lo cambiaremos después en su propia subclase.
        this.tipoObjetivo = TipoObjetivo.ENEMIGO;
    }

    // Este método comprueba si el personaje cumple los requisitos para usar el ataque
    // (es decir, si tiene suficiente recurso y si no está en tiempo de espera/cooldown).
    public boolean puedeLanzarse(Personaje caster) {
        // Comprobamos si el personaje tiene menos recurso del que pide el ataque...
        // Si es así, devolvemos false (no se puede lanzar el ataque).
        if (caster.recursoActual < costeDeRecurso) return false;
        
        // Comprobamos si el ataque está en la lista de cooldowns (tiempos de espera) del personaje...
        // Y si su tiempo de espera es mayor que 0 (aún le faltan turnos), devolvemos false.
        if (caster.cooldowns.containsKey(nombre) && caster.cooldowns.get(nombre) > 0) return false;
        
        // Si logra pasar de las dos comprobaciones anteriores, entonces sí puede lanzarse y devolvemos true.
        return true;
    }

    // Este método ejecuta la parte básica de lanzar el ataque: gasta el recurso y pone el ataque en cooldown.
    // Después, cada tipo de ataque (hijo) añadirá su propio efecto concreto (quitar vida, curar, etc).
    public void lanzar(Personaje caster, Personaje objetivo) {
        // Le restamos el coste del ataque al recurso (maná, energía, etc.) del personaje que lo lanza (el caster)
        caster.gastarRecurso(costeDeRecurso);
        
        // Si el ataque tiene un tiempo de espera definido (cooldownMax > 0), 
        // lo añadimos al diccionario de cooldowns del personaje.
        if (cooldownMax > 0) caster.cooldowns.put(nombre, cooldownMax);
    }

    // Método que calcula la cantidad de efecto (daño o curación) que va a hacer el ataque.
    // Por ahora, solo devuelve la potencia base que configuramos al principio.
    // Las clases hijas podrán modificar esto si el daño, por ejemplo, debe escalar con la fuerza del personaje.
    public int calcularEfecto(Personaje caster, Personaje objetivo) {
        return potenciaBase;
    }

    // Un "getter" estándar para obtener el nombre de este ataque.
    public String getNombre() { return nombre; }

    // Otro "getter" estándar para saber a qué tipo de objetivo va dirigido (aliado o enemigo).
    public TipoObjetivo getTipoObjetivo() { return tipoObjetivo; }

    // Sobrescribimos el método toString() para que, si imprimimos un objeto AtaqueEspecial en la consola, 
    // nos muestre un texto bonito y formateado con toda la información en lugar del típico código de memoria.
    @Override
    public String toString() {
        return nombre + " (Coste:" + costeDeRecurso + " | CD:" + cooldownMax + "t | Pot:" + potenciaBase + ") - " + descripcion;
    }
}
