package Personaje;

import Arma.ArmaADistancia;
import AtaqueEspecial.DoT;

// Clase que representa a Ellie, otro de los personajes del juego. Hereda de Personaje.
public class Ellie extends Personaje {

    // Constructor que configura a Ellie al iniciar la partida.
    public Ellie() {
        // Configuramos sus bases: 
        // Nombre("Ellie"), Tipo(Ellie), vidaMax(30), recursoMax(25), ataqueBase(100), defensaBase(25).
        // Vemos que es de papel (poca vida y defensa) pero tiene mucho ataque natural.
        super("Ellie", TipodePersonaje.Ellie, 30, 25, 100, 25);
        
        // Arma de Ellie por defecto: Una pistola a distancia.
        this.armaEquipada = new ArmaADistancia("Pistola", 25);
        
        // Sus dos ataques especiales son de tipo "Daño en el tiempo (DoT)":
        // 1. Spray Esporas: cuesta 25, dura 3 turnos y quita 10 de vida por turno.
        this.ataquesEspeciales.add(new DoT("Spray Esporas",    25, "Esporas infectantes", 3, 10));
        // 2. Flecha Infectada: cuesta 20, dura 4 turnos y quita 8 de vida por turno.
        this.ataquesEspeciales.add(new DoT("Flecha Infectada", 20, "Flecha con veneno",   4,  8));
    }
}