package Estado;

import Personaje.Personaje;

// Esta clase hereda de la clase base "Estado".
// Representa una condición positiva que va curando al personaje poco a poco a lo largo de los turnos.
public class Curacion extends Estado {

    // Constructor que usamos para crear el efecto de curación.
    // Solo necesitamos saber cuántos turnos va a durar y cuánta vida regenera por turno.
    public Curacion(int turnosRestantes, int potenciaPorTurnos) {
        // Llamamos al constructor del padre ("Estado") pasándole el nombre fijo "Curacion",
        // y los turnos y potencia que nos pidieron.
        super("Curacion", turnosRestantes, potenciaPorTurnos);
    }

    // Aquí "sobrescribimos" el método que se activa cada vez que pasa un turno en el juego.
    @Override
    public void alProcesarTurno(Personaje objetivo) {
        // Primero informamos en la consola de que el efecto hizo su trabajo.
        System.out.println("    >> [Curacion] " + objetivo.nombre
                + " se recupera " + potenciaPorTurnos + " de vida.");
                
        // Y por último, le aumentamos (curamos) la vida real al personaje.
        objetivo.curar(potenciaPorTurnos);
    }
}
