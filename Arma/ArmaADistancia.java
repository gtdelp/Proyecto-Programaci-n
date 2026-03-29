package Arma;

import Personaje.Personaje;

// Esta clase hereda de Arma. Representa un arma que se usa desde lejos, como un arco o una pistola.
public class ArmaADistancia extends Arma {

    // Constructor de ArmaADistancia. 
    // Igual que la clase padre, necesitamos el nombre y el daño base.
    public ArmaADistancia(String nombre, int danoBase) {
        // Llamamos al constructor de la clase padre (Arma) pasándole el nombre y el daño.
        super(nombre, danoBase);
    }

    // Sobrescribimos la forma en que esta arma calcula su daño.
    @Override
    public int calcularDanio(Personaje atacante, Personaje defensor) {
        // Al atacar a distancia, hacemos el daño del arma sumado al ataque normal del personaje.
        // (Es decir, de momento funciona igual que cualquier arma normal)
        return danoBase + atacante.ataqueBase;
    }
}
