public class ArmaCuerpoACuerpo extends Arma {

    public ArmaCuerpoACuerpo(String nombre, int danoBase) {
        super(nombre, danoBase);
    }

    @Override
    public int calcularDanio(Personaje atacante, Personaje defensor) {
        // Cuerpo a cuerpo: se beneficia más del ataqueBase
        return danoBase + (int)(atacante.ataqueBase * 1.2);
    }
}
