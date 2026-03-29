package Estado;

import Personaje.Personaje;

// Esta clase hereda de "Estado". 
// Representa una infección o envenenamiento que daña al personaje cada turno.
public class Infectado extends Estado {

    // Constructor de la infección. 
    // Requiere la duración (turnos) y el daño en cada turno.
    public Infectado(int turnosRestantes, int potenciaPorTurnos) {
        // Le mandamos los datos a la clase principal (Estado) junto con su nombre predeterminado.
        super("Infectado", turnosRestantes, potenciaPorTurnos);
    }

    // Acción que se ejecuta una vez por cada turno que pasa el personaje con la afección.
    @Override
    public void alProcesarTurno(Personaje objetivo) {
        // Mostramos en la consola cuánto daño acaba de recibir.
        System.out.println("    >> [Infectado] " + objetivo.nombre
                + " sufre " + potenciaPorTurnos + " de dano por infeccion.");
                
        // Le restamos la vida al personaje saltándonos también las reducciones por armadura.
        objetivo.recibirDanioDirecto(potenciaPorTurnos);
    }
}
