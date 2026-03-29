package AtaqueEspecial;

import Personaje.Personaje;

// Esta clase (CuracionDirecta) hereda (extiende) de nuestra clase base AtaqueEspecial.
// Significa que es una habilidad, pero especificamente sirve para curar ("restaurar vida") a alguien del equipo aliado de golpe.
public class CuracionDirecta extends AtaqueEspecial {

    // Este es el constructor de nuestra clase CuracionDirecta.
    // Nos pide los datos básicos de la habilidad: nombre, cuánto maná/energía cuesta,
    // una pequeña descripción, el tiempo de espera (cooldown) y cuánta vida cura (potenciaBase).
    public CuracionDirecta(String nombre, int costeDeRecurso, String descripcion,
                           int cooldownMax, int potenciaBase) {
        // Con "super" estamos llamando al constructor de la clase padre (AtaqueEspecial).
        // Le pasamos estos valores para que la clase base guarde el nombre, coste, etc.
        super(nombre, costeDeRecurso, descripcion, cooldownMax, potenciaBase);
        
        // Puesto que esto es una curación, nuestro objetivo no es herir a un enemigo.
        // Sobrescribimos el tipoObjetivo a ALIADO.
        this.tipoObjetivo = TipoObjetivo.ALIADO;
    }

    // Aquí "sobrescribimos" (@Override) el método lanzar de la clase padre.
    // Queremos que haga lo normal (gastar maná y aplicar cooldown), pero además que cure.
    @Override
    public void lanzar(Personaje caster, Personaje objetivo) {
        // Primero, ejecuta lo que hace la clase padre (gastar recurso y poner cooldown).
        super.lanzar(caster, objetivo);
        
        // Calculamos cuánta vida vamos a curar usando nuestra potencia base.
        int curacion = calcularEfecto(caster, objetivo);
        
        // Mostramos un mensajito en pantalla explicando qué acaba de pasar en batalla.
        System.out.println("    " + caster.nombre + " lanza " + nombre + " sobre " + objetivo.nombre + " restaurando " + curacion + " de vida!");
        
        // Literalmente aplicamos la curación al personaje aliado que pusimos como objetivo.
        objetivo.curar(curacion);
    }
}