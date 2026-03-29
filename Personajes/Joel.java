public class Joel extends Personaje {

    public Joel() {
        super("Joel", TipodePersonaje.Joel, 100, 100, 0, 25);
        this.armaEquipada = new ArmaCuerpoACuerpo("Bate", 30);
        this.ataquesEspeciales.add(new DanoDirecto("Golpe Brutal",   0, "Danio directo masivo", 3, 50));
        this.ataquesEspeciales.add(new DoT        ("Cuchillo Sucio", 0, "Infeccion lenta",      4,  8));
    }
}
