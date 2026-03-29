public class Abby extends Personaje {

    public Abby() {
        super("Abby", TipodePersonaje.Abby, 50, 75, 50, 50);
        this.armaEquipada = new ArmaCuerpoACuerpo("Bate", 30);
        this.ataquesEspeciales.add(new HoT        ("Medicina",  20, "Curacion continua",  4, 10));
        this.ataquesEspeciales.add(new CuracionDirecta("Cura",  15, "Curacion inmediata", 2, 30));
        this.ataquesEspeciales.add(new DanoDirecto("Golpe Seco", 0, "Golpe con fuerza",   3, 40));
    }
}
