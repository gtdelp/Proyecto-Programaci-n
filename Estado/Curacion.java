public class Curacion extends Estado {

    public Curacion(int turnosRestantes, int potenciaPorTurnos) {
        super("Curacion", turnosRestantes, potenciaPorTurnos);
    }

    @Override
    public void alProcesarTurno(Personaje objetivo) {
        System.out.println("    >> [Curacion] " + objetivo.nombre
                + " se recupera " + potenciaPorTurnos + " de vida.");
        objetivo.curar(potenciaPorTurnos);
    }
}
