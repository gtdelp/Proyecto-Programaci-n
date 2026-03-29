package AtaqueEspecial;

import Personaje.Personaje;
import Estado.Curacion;

// Esta clase (HoT - Healing over Time / Curación en el Tiempo) hereda de AtaqueEspecial.
// Sirve para aplicar un estado positivo a un aliado que le irá curando un poco cada turno.
public class HoT extends AtaqueEspecial {

    // Constructor que define la curación a lo largo del tiempo.
    // Igual que DoT, aquí aprovechamos las variables:
    // cooldownMax = cuánto va a durar el efecto, potenciaBase = cuánta vida cura por turno.
    public HoT(String nombre, int costeDeRecurso, String descripcion,
               int cooldownMax, int potenciaBase) {
        // Llamada al constructor del padre para inicializar los valores
        super(nombre, costeDeRecurso, descripcion, cooldownMax, potenciaBase);
        
        // Como esto cura y ayuda, el objetivo por defecto es un ALIADO.
        this.tipoObjetivo = TipoObjetivo.ALIADO;
    }

    // Sobrescribimos lanzar. No cura de golpe, solo pone la "pegatina" o estado positivo.
    @Override
    public void lanzar(Personaje caster, Personaje objetivo) {
        // Ejecutamos lo que hace el padre (gastar maná/energía)
        super.lanzar(caster, objetivo);
        
        // Texto informativo en la consola
        System.out.println("    " + caster.nombre + " lanza " + nombre + " sobre " + objetivo.nombre + "! Aplica curacion por turno.");
        
        // Creamos un nuevo efecto/estado llamado 'Curacion'.
        // Le pasamos cuántos turnos durará y cuánto va a curar en cada uno.
        Curacion estado = new Curacion(cooldownMax, potenciaBase);
        
        // Le transferimos este estado positivo al aliado para que empiece a sanar con el tiempo.
        objetivo.aplicarEstado(estado);
    }
}