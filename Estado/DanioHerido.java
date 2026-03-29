public class DaniadoHerido extends Estado {

    public DaniadoHerido(int turnosRestantes, int potenciaPorTurnos) {
        super("DaniadoHerido", turnosRestantes, potenciaPorTurnos);
    }

    @Override
    public void alProcesarTurno(Personaje objetivo) {
        System.out.println("    >> [DaniadoHerido] " + objetivo.nombre
                + " sangra y pierde " + potenciaPorTurnos + " de vida.");
        objetivo.recibirDanioDirecto(potenciaPorTurnos);
    }
}
