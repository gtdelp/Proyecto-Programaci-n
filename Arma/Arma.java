public class Arma {

    protected String nombre;
    protected int danoBase;

    public Arma(String nombre, int danoBase) {
        this.nombre   = nombre;
        this.danoBase = danoBase;
    }

    public int calcularDanio(Personaje atacante, Personaje defensor) {
        return danoBase + atacante.ataqueBase;
    }

    public String descripcion() {
        return nombre + " (daño base: " + danoBase + ")";
    }
}
