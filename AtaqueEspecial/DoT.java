package AtaqueEspecial;

import Personaje.Personaje;
import Estado.Infectado;

// Esta clase (DoT - Damage over Time / Daño en el Tiempo) hereda de AtaqueEspecial.
// Su propósito no es hacer daño directo, sino aplicar un estado negativo al enemigo
// (como veneno o quemadura) que le quitará vida en cada turno.
public class DoT extends AtaqueEspecial {

    // Constructor de nuestro ataque de daño en el tiempo.
    // Nos pide los mismos datos básicos, pero aquí 'cooldownMax' se interpretará
    // como cuánto dura el estado negativo, y 'potenciaBase' como el daño que hace por turno.
    public DoT(String nombre, int costeDeRecurso, String descripcion,
               int cooldownMax, int potenciaBase) {
        // Guardamos todo usando el constructor de la clase padre
        super(nombre, costeDeRecurso, descripcion, cooldownMax, potenciaBase);
        
        // Es un ataque negativo, así que el objetivo siempre será un ENEMIGO.
        this.tipoObjetivo = TipoObjetivo.ENEMIGO;
    }

    // Sobrescribimos el método lanzar.
    // En vez de quitar vida al instante, aplicará una condición o estado al enemigo.
    @Override
    public void lanzar(Personaje caster, Personaje objetivo) {
        // Como siempre, llamamos al padre para que gaste el maná/energía.
        super.lanzar(caster, objetivo);
        
        // Un mensaje en consola para indicar que la habilidad fue usada.
        System.out.println("    " + caster.nombre + " lanza " + nombre + " sobre " + objetivo.nombre + "! Aplica danio por turno.");
        
        // Creamos un nuevo objeto 'Infectado', que es nuestro estado de daño over time.
        // Le pasamos la duración (cooldownMax) y su daño periódico (potenciaBase).
        Infectado estado = new Infectado(cooldownMax, potenciaBase);
        
        // Añadimos finalmente este estado negativo a la lista de estados del enemigo.
        objetivo.aplicarEstado(estado);
    }
}