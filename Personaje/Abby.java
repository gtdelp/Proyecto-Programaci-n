package Personaje;

import Arma.ArmaCuerpoACuerpo;
import AtaqueEspecial.HoT;
import AtaqueEspecial.CuracionDirecta;
import AtaqueEspecial.DanoDirecto;

// Esta clase representa a Abby, uno de los personajes jugables. Hereda de Personaje.
public class Abby extends Personaje {

    // El constructor por defecto. Cuando creamos un "new Abby()", el personaje se prepara aquí.
    public Abby() {
        // Llamamos al constructor de la clase padre (Personaje) y le pasamos sus atributos iniciales:
        // Nombre("Abby"), TipoDePersonaje(Abby), vidaMax(50), recursoMax(75), ataqueBase(50), defensaBase(50)
        super("Abby", TipodePersonaje.Abby, 50, 75, 50, 50);
        
        // Abby empieza por defecto equipada con un "Bate", que es un arma cuerpo a cuerpo de 30 de daño.
        this.armaEquipada = new ArmaCuerpoACuerpo("Bate", 30);
        
        // Le añadimos 3 habilidades especiales a su lista de ataquesEspeciales:
        // 1. Un "HoT" (Curación en el tiempo) que cura vida poco a poco.
        this.ataquesEspeciales.add(new HoT        ("Medicina",  20, "Curacion continua",  4, 10));
        
        // 2. Una "CuracionDirecta" que recupera mucha vida de golpe pero solo de una vez.
        this.ataquesEspeciales.add(new CuracionDirecta("Cura",  15, "Curacion inmediata", 2, 30));
        
        // 3. Un "DanoDirecto" para golpear muy fuerte a los enemigos gastando su tiempo.
        this.ataquesEspeciales.add(new DanoDirecto("Golpe Seco", 0, "Golpe con fuerza",   3, 40));
    }
}