package Arma;
import Personaje.Personaje;

// Esta clase hereda de Arma. Representa armas como espadas o hachas (para golpear de cerca).
public class ArmaCuerpoACuerpo extends Arma {

    // Constructor que usamos al crear una nueva espada, hacha, etc.
    public ArmaCuerpoACuerpo(String nombre, int danoBase) {
        // Mandamos la información a la clase Padre (Arma) para que la guarde
        super(nombre, danoBase);
    }

    // Sobrescribimos el cálculo del daño. 
    // Queremos que las armas cuerpo a cuerpo hagan un poco más de daño si el atacante es muy fuerte.
    @Override
    public int calcularDanio(Personaje atacante, Personaje defensor) {
        // La fórmula cambia: Daño del arma + (Ataque base del personaje incrementado en un 20%).
        // Al multiplicarlo por 1.2, hace más daño que un arma a distancia, 
        // y como pedíamos que devuelva un entero (int), forzamos el tipo al final poniéndole (int).
        return danoBase + (int)(atacante.ataqueBase * 1.2);
    }
}
