package Estado;

import Personaje.Personaje;

// Esta es la clase base (padre) para cualquier efecto o condición que dure varios turnos.
// Ejemplos de estados: Estar envenenado, sangrando, o regenerando vida poco a poco.
// Las clases hijas como Curacion, DaniadoHerido e Infectado se basan en esta estructura.
public class Estado {

    // Todo estado necesita estas características básicas:
    public String nombre; // Cómo se llama el estado (ej. "Veneno")
    public int turnosRestantes; // Cuántos turnos va a durar el efecto antes de desaparecer
    public int potenciaPorTurnos; // Cuánto daño o curación hace en cada turno

    // El constructor. Lo llamamos cuando queremos crear un nuevo estado en el juego.
    public Estado(String nombre, int turnosRestantes, int potenciaPorTurnos) {
        // Configuramos los valores de nuestro objeto estado.
        this.nombre = nombre;
        this.turnosRestantes = turnosRestantes;
        this.potenciaPorTurnos = potenciaPorTurnos;
    }

    // Método que se ejecuta justo en el momento en que alguien recibe este estado.
    // Solo imprime un mensaje para que el jugador se entere de lo que pasó.
    public void alAplicar(Personaje objetivo) {
        System.out.println("    [+Estado] " + objetivo.nombre + " recibe: " + nombre + " (" + turnosRestantes + " turnos)");
    }

    // Este método es muy importante. Se ejecuta cada vez que pasa un turno.
    // En esta clase base está vacío porque no sabemos qué hace el estado (si curar, si dañar...).
    // Serán las clases hijas las que escriban aquí el comportamiento exacto.
    public void alProcesarTurno(Personaje objetivo) {
        // Las subclases definen el efecto que ocurre en el turno.
    }

    // Método que se ejecuta justo cuando el estado desaparece porque se le acabaron los turnos.
    public void alExpirar(Personaje objetivo) {
        System.out.println("    [-Estado] " + nombre + " ha expirado en " + objetivo.nombre);
    }

    // Método sencillo para restarle un turno a la duración que le queda al estado.
    // Se llamará al final de cada ronda.
    public void reducirDuracion() {
        turnosRestantes--;
    }

    // Método para saber si el estado ya debe desaparecer.
    // Devuelve 'true' (verdadero) si los turnos restantes son 0 o menos.
    public boolean haExpirado() {
        return turnosRestantes <= 0;
    }

    // --- A partir de aquí solo hay "Getters", que son métodos para que otras clases 
    // puedan consultar los valores de este estado. ---
    
    // Método para obtener el nombre del estado
    public String getNombre() {
        return nombre;
    }

    // Método para saber cuántos turnos le quedan al estado
    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    // Método para saber qué tan fuerte (daño/curación) es el estado por cada turno
    public int getPotenciaPorTurnos() {
        return potenciaPorTurnos;
    }
}
