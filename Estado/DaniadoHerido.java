package Estado;

import Personaje.Personaje;

// Esta clase hereda de "Estado".
// Sirve para representar cuando un personaje está sangrando o gravemente herido, perdiendo vida por turno.
public class DaniadoHerido extends Estado {

    // Constructor que configura sangrado o heridas en el tiempo.
    // Necesitamos pasarle la duración en turnos y la potencia (cuánto daño hace cada turno).
    public DaniadoHerido(int turnosRestantes, int potenciaPorTurnos) {
        // Le pasamos estos valores a la clase padre y fijamos el nombre de este estado.
        super("DaniadoHerido", turnosRestantes, potenciaPorTurnos);
    }

    // Cada vez que le toca el turno al personaje, el juego llama a este método automáticamente.
    @Override
    public void alProcesarTurno(Personaje objetivo) {
        // Le dice al jugador por texto que el personaje acaba de recibir daño por su estado.
        System.out.println("    >> [DaniadoHerido] " + objetivo.nombre
                + " sangra y pierde " + potenciaPorTurnos + " de vida.");
                
        // Y le quitamos realmente esa cantidad de vida. Usamos "recibirDanioDirecto" (que ignora escudos o defensas, suele ser lo común en estos estados).
        objetivo.recibirDanioDirecto(potenciaPorTurnos);
    }
}