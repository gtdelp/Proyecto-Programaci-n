package AtaqueEspecial;

import Personaje.Personaje;

// Esta clase (DanoDirecto) hereda de la clase padre AtaqueEspecial.
// Representa cualquier ataque o hechizo que quite vida de forma instantánea al enemigo.
public class DanoDirecto extends AtaqueEspecial {

    // Este es el constructor para crear un nuevo ataque que haga daño directo.
    // Recibe los parámetros básicos (nombre, coste, descripción, tiempo de recarga y el daño que va a hacer).
    public DanoDirecto(String nombre, int costeDeRecurso, String descripcion,
                       int cooldownMax, int potenciaBase) {
        // Llamamos al constructor de la clase Padre usando "super" para que guarde todos estos datos básicos.
        super(nombre, costeDeRecurso, descripcion, cooldownMax, potenciaBase);
        
        // Como este ataque es para hacer daño, nos aseguramos de que el objetivo esperado sea un ENEMIGO.
        this.tipoObjetivo = TipoObjetivo.ENEMIGO;
    }

    // Sobrescribimos (@Override) el método lanzar. 
    // Además de gastar los recursos, queremos que golpee al objetivo.
    @Override
    public void lanzar(Personaje caster, Personaje objetivo) {
        // Con esto llamamos al método lanzar del padre (gasta el recurso y pone el ataque en tiempo de espera).
        super.lanzar(caster, objetivo);
        
        // Calculamos cuánto daño real le vamos a hacer al enemigo.
        int danio = calcularEfecto(caster, objetivo);
        
        // Escribimos en consola lo que está sucediendo en la pelea.
        System.out.println("    " + caster.nombre + " lanza " + nombre + " contra " + objetivo.nombre + " causando " + danio + " de danio!");
        
        // Por último, usamos un método del objetivo para restarle la vida por el golpe.
        objetivo.recibirDanio(danio);
    }
}