public class Ellie extends Personaje {

    public Ellie() {
        super("Ellie", TipodePersonaje.Ellie, 30, 25, 100, 25);
        this.armaEquipada = new ArmaADistancia("Pistola", 25);
        this.ataquesEspeciales.add(new DoT("Spray Esporas",    25, "Esporas infectantes", 3, 10));
        this.ataquesEspeciales.add(new DoT("Flecha Infectada", 20, "Flecha con veneno",   4,  8));
    }
}
