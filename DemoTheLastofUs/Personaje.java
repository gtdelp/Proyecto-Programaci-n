/* import java.util.ArrayList;
import java.util.List;

public class Personaje {

    // Identidad
    protected String nombre;
    protected TipodePersonaje tipoClase;

    // Vida
    protected int vidaMax;
    protected int vidaActual;

    // Recurso
    protected int recursoMax;
    protected int recursoActual;

    // Estadística Base
    protected int ataqueBase;
    protected int defensaBase;

    // Equipamiento
    protected Arma armaEquipada;

    // Colecciones asociadas
    protected List<Estado> estadosActivos;
    protected List<AtaqueEspecial> ataquesEspeciales;

    // Estado Combate
    protected boolean vivo;
    protected boolean defendiendo;

    public Personaje(String nombre, TipodePersonaje tipoClase, int vidaMax, int recursoMax, int ataqueBase, int defensaBase) {
        this.nombre = nombre;
        this.tipoClase = tipoClase;
        this.vidaMax = vidaMax;
        this.vidaActual = vidaMax;
        this.recursoMax = recursoMax;
        this.recursoActual = recursoMax;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.vivo = true;
        this.defendiendo = false;
        this.estadosActivos = new ArrayList<>();
        this.ataquesEspeciales = new ArrayList<>();
    }
}
 */