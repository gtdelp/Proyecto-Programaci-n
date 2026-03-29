public class ArmaADistancia extends Arma {

    public ArmaADistancia(String nombre, int danoBase) {
        super(nombre, danoBase);
    }

    @Override
    public int calcularDanio(Personaje atacante, Personaje defensor) {
        // A distancia: daño base + ataqueBase normal
        return danoBase + atacante.ataqueBase;
    }
}
