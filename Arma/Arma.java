package Arma;

import Personaje.Personaje;

// Esta es la clase principal que define lo que es un Arma en nuestro juego.
// Servirá como base para los diferentes tipos de armas (cuerpo a cuerpo y a distancia).
public class Arma {

    // Cualquier arma va a tener al menos estas dos características básicas:
    public String nombre; // El nombre del arma (ej: Espada Larga, Arco de Madera)
    public int danoBase; // Cuánto daño físico es capaz de hacer por sí sola

    // El constructor de la clase. Nos piden dos datos para poder crear (instanciar) un arma nueva.
    public Arma(String nombre, int danoBase) {
        // Asignamos los valores que entran por parámetro a las variables de nuestra clase usando 'this'.
        this.nombre   = nombre;
        this.danoBase = danoBase;
    }

    // Este método calcula cuánto daño en total va a hacer el arma en un ataque.
    public int calcularDanio(Personaje atacante, Personaje defensor) {
        // En esta clase base, la fórmula es simplemente: Daño del arma + ataque natural del personaje
        return danoBase + atacante.ataqueBase;
    }

    // Un método simple para devolver información del arma en forma de texto.
    public String descripcion() {
        return nombre + " (daño base: " + danoBase + ")";
    }

    // Getter para poder obtener el nombre del arma desde otras clases.
    public String getNombre() {
        return nombre;
    }
}