package Personaje;

import Arma.ArmaCuerpoACuerpo;
import AtaqueEspecial.DanoDirecto;
import AtaqueEspecial.DoT;

// Clase que representa a Joel, otro personaje jugable. Hereda de Personaje.
public class Joel extends Personaje {

    // Constructor que inicializa los valores de Joel al crearlo en el juego.
    public Joel() {
        // Configuramos sus bases: 
        // Nombre("Joel"), Tipo(Joel), vidaMax(100), recursoMax(100), ataqueBase(0), defensaBase(25).
        // Tiene mucha vida y defensa, pero poco ataque natural.
        super("Joel", TipodePersonaje.Joel, 100, 100, 0, 25);
        
        // Como arma usa un Bate cuerpo a cuerpo.
        this.armaEquipada = new ArmaCuerpoACuerpo("Bate", 30);
        
        // Entrena un ataque de daño directo (Golpe Brutal) y un daño en el tiempo (Cuchillo Sucio).
        // 1. Golpe Brutal: 0 coste recurso, 3 de cooldown, 50 de daño directo masivo.
        this.ataquesEspeciales.add(new DanoDirecto("Golpe Brutal",   0, "Danio directo masivo", 3, 50));
        // 2. Cuchillo Sucio: 0 coste, pone veneno o infección de 8 puntos durante 4 turnos.
        this.ataquesEspeciales.add(new DoT        ("Cuchillo Sucio", 0, "Infeccion lenta",      4,  8));
    }
}