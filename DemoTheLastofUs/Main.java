/* public class Main {

    public static void main(String[] args) {

        // Armas
        ArmaCuerpoACuerpo arco    = new ArmaCuerpoACuerpo("Arco",    30);
        ArmaADistancia    pistola  = new ArmaADistancia   ("Pistola", 25);

        // Ataques Especiales
        DanoDirecto     golpe    = new DanoDirecto    ("Golpe",    20, "Daño directo",  3, 50);
        CuracionDirecta cura     = new CuracionDirecta("Cura",     15, "Cura directa",  2, 30);
        DoT             sprayesporas  = new DoT            ("Esporas",  25, "Daño continuo", 3, 10);
        HoT             medicina = new HoT            ("Medicina", 20, "Cura continua", 4, 10);

        // Estados
        DaniadoHerido herido    = new DaniadoHerido(3, 10);
        Infectado     infectado = new Infectado    (5,  8);
        Curacion      curacion  = new Curacion     (2, 15);

        // Personajes
        Joel  joel  = new Joel();
        Ellie ellie = new Ellie();
        Abby  abby  = new Abby();

        // Equipar armas
        joel.armaEquipada  = pistola;
        ellie.armaEquipada = arco;
        abby.armaEquipada  = arco;

        // Añadir estados
        joel.estadosActivos.add(infectado);
        ellie.estadosActivos.add(herido);
        abby.estadosActivos.add(curacion);

        // Añadir ataques especiales
        joel.ataquesEspeciales.add(golpe);
        ellie.ataquesEspeciales.add(sprayesporas);
        abby.ataquesEspeciales.add(medicina);
        abby.ataquesEspeciales.add(cura);

        // Mostrar
        System.out.println(joel.nombre  + " | vida: " + joel.vidaActual  + "/" + joel.vidaMax  + " | arma: " + joel.armaEquipada.nombre);
        System.out.println(ellie.nombre + " | vida: " + ellie.vidaActual + "/" + ellie.vidaMax + " | arma: " + ellie.armaEquipada.nombre);
        System.out.println(abby.nombre  + " | vida: " + abby.vidaActual  + "/" + abby.vidaMax  + " | arma: " + abby.armaEquipada.nombre);
    }
}
 */