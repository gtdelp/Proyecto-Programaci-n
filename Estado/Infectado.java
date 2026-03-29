public class Infectado extends Estado {

    public Infectado(int turnosRestantes, int potenciaPorTurnos) {
        super("Infectado", turnosRestantes, potenciaPorTurnos);
    }

    @Override
    public void alProcesarTurno(Personaje objetivo) {
        System.out.println("    >> [Infectado] " + objetivo.nombre
                + " sufre " + potenciaPorTurnos + " de dano por infeccion.");
        objetivo.recibirDanioDirecto(potenciaPorTurnos);
    }
}
