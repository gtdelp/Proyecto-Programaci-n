package Main;

import Personaje.Personaje;
import Personaje.Joel;
import Personaje.Ellie;
import Personaje.Abby;
import AtaqueEspecial.AtaqueEspecial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Esta es la clase principal de nuestro programa. Es como el director del juego.
// Aquí se muestra el menú, se crean los dos equipos (el de los jugadores y el de los enemigos)
// y donde ocurre toda la lógica de turnos y peleas paso a paso.
public class Main {

    // Scanner y random globales
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    // Los dos equipos del combate
    private static List<Personaje> equipoBueno;
    private static List<Personaje> equipoMalo;

    // Control de rondas
    private static int ronda;

    // Modo de juego
    private static boolean modoAutomatico;

    // Estadisticas de combate
    private static int danioTotalBueno;
    private static int danioTotalMalo;
    private static int curacionTotalBueno;
    private static int curacionTotalMalo;

    // Este es el punto de inicio (main) de todo programa de Java.
    // Al ejecutar el código, esto es lo primero que se lee. Mostrará el menú principal
    // en un bucle "while" infinito hasta que pulsemos "Salir".
    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("====================================================");
            System.out.println("       RPG POR TURNOS - THE LAST OF US");
            System.out.println("====================================================");
            System.out.println("  1. Iniciar combate");
            System.out.println("  2. Salir");
            System.out.print("  Elige opcion: ");

            String linea = scanner.nextLine().trim();
            if (linea.equals("2")) {
                salir = true;
                System.out.println("  Hasta la proxima!");
                continue;
            }
            if (!linea.equals("1")) {
                System.out.println("  Opcion no valida.\n");
                continue;
            }

            // Elegir modo de juego
            System.out.println("\n  Elige modo de juego:");
            System.out.println("    1. Manual (tu controlas el equipo bueno)");
            System.out.println("    2. Automatico (IA vs IA)");
            System.out.print("  Modo: ");
            String modo = scanner.nextLine().trim();
            modoAutomatico = modo.equals("2");

            // Crear equipo bueno
            System.out.println("\n============ EQUIPO BUENO ============");
            equipoBueno = crearEquipo("Bueno");

            // Crear equipo malo
            System.out.println("\n============ EQUIPO MALO ============");
            System.out.println("  Como crear el equipo malo?");
            System.out.println("    1. Seleccion manual");
            System.out.println("    2. Generacion aleatoria (IA)");
            System.out.print("  Opcion: ");
            String opcionMalo = scanner.nextLine().trim();

            if (opcionMalo.equals("1")) {
                equipoMalo = crearEquipo("Malo");
            } else {
                equipoMalo = crearEquipoAleatorio();
                System.out.println("  Equipo malo generado:");
                for (Personaje p : equipoMalo) {
                    System.out.println("    - " + p.resumenCombate());
                }
            }

            // Iniciar combate
            iniciarCombate();

            System.out.println("  Pulsa ENTER para volver al menu...");
            scanner.nextLine();
        }

        scanner.close();
    }

    // ===================== CREACION DE EQUIPOS =====================

    // Función que nos hace preguntas en la consola para elegir 3 personajes.
    // Lo usaremos al principio para decir quién está en nuestro equipo.
    // Devuelve la lista ya creada y guardada con nuestros personajes.
    private static List<Personaje> crearEquipo(String nombreEquipo) {
        List<Personaje> equipo = new ArrayList<>();
        int[] contadores = {0, 0, 0}; // Joel, Ellie, Abby

        for (int i = 1; i <= 3; i++) {
            System.out.println("  Elige personaje " + i + " para equipo " + nombreEquipo + ":");
            System.out.println("    1. Joel  (HP:100 | ATK:0   | DEF:25 | REC:100) - Tanque");
            System.out.println("    2. Ellie (HP:30  | ATK:100 | DEF:25 | REC:25)  - DPS/DoT");
            System.out.println("    3. Abby  (HP:50  | ATK:50  | DEF:50 | REC:75)  - Hibrido/Sanadora");
            System.out.print("  Clase: ");

            int clase = leerEntero("", 1, 3);

            Personaje p;
            switch (clase) {
                case 1:
                    contadores[0]++;
                    p = new Joel();
                    if (contadores[0] > 1) p.nombre = "Joel_" + contadores[0];
                    break;
                case 2:
                    contadores[1]++;
                    p = new Ellie();
                    if (contadores[1] > 1) p.nombre = "Ellie_" + contadores[1];
                    break;
                default:
                    contadores[2]++;
                    p = new Abby();
                    if (contadores[2] > 1) p.nombre = "Abby_" + contadores[2];
                    break;
            }
            equipo.add(p);
            System.out.println("    Anadido: " + p.resumenCombate());
        }
        return equipo;
    }

    // Si el jugador elige jugar contra la IA (o elige IA contra IA), 
    // esta función mete personajes completamente al azar en un equipo en lugar de preguntar.
    private static List<Personaje> crearEquipoAleatorio() {
        List<Personaje> equipo = new ArrayList<>();
        int[] contadores = {0, 0, 0};

        for (int i = 0; i < 3; i++) {
            int tipo = random.nextInt(3);
            Personaje p;
            switch (tipo) {
                case 0:
                    contadores[0]++;
                    p = new Joel();
                    if (contadores[0] > 1) p.nombre = "Joel_" + contadores[0];
                    break;
                case 1:
                    contadores[1]++;
                    p = new Ellie();
                    if (contadores[1] > 1) p.nombre = "Ellie_" + contadores[1];
                    break;
                default:
                    contadores[2]++;
                    p = new Abby();
                    if (contadores[2] > 1) p.nombre = "Abby_" + contadores[2];
                    break;
            }
            equipo.add(p);
        }
        return equipo;
    }

    // ===================== MOTOR DE COMBATE =====================

    // El motor central del simulador. Es un bucle que no parará de dar vueltas 
    // mientras haya un personaje vivo en ambos lados. En cada ronda pasan 3 cosas: 
    // luchar, sufrir los venenos(estados), y bajar los cooldowns.
    private static void iniciarCombate() {
        ronda = 0;
        danioTotalBueno = 0;
        danioTotalMalo = 0;
        curacionTotalBueno = 0;
        curacionTotalMalo = 0;

        System.out.println("\n====================================================");
        System.out.println("    COMIENZA EL COMBATE: EQUIPO BUENO vs EQUIPO MALO");
        System.out.println("====================================================\n");
        mostrarEquipos();

        while (!combateTerminado()) {
            ronda++;
            System.out.println("\n----------------------------------------------------");
            System.out.println("                   RONDA " + ronda);
            System.out.println("----------------------------------------------------");

            // Fase 1: acciones con orden aleatorio
            faseAcciones();
            if (combateTerminado()) break;

            // Fase 2: procesar estados (DoT, HoT)
            faseEstados();
            if (combateTerminado()) break;

            // Fase 3: reducir cooldowns
            faseCooldowns();

            // Mostrar estado actual
            System.out.println("\n--- Estado tras ronda " + ronda + " ---");
            mostrarEquipos();
        }

        mostrarResumenFinal();
    }

    // Fase 1: Juntamos a todos los personajes vivos en una sola lista, 
    // la "barajamos" al azar (Collections.shuffle) y luego cada uno realiza su acción.
    private static void faseAcciones() {
        List<Personaje> orden = new ArrayList<>();
        for (Personaje p : equipoBueno) {
            if (p.estaVivo()) orden.add(p);
        }
        for (Personaje p : equipoMalo) {
            if (p.estaVivo()) orden.add(p);
        }
        Collections.shuffle(orden);

        System.out.println("\n[Fase de Acciones]");
        for (Personaje p : orden) {
            if (!p.estaVivo()) continue;
            if (combateTerminado()) break;

            // Resetear defensa del turno anterior
            p.defendiendo = false;

            boolean esBueno = equipoBueno.contains(p);
            if (esBueno && !modoAutomatico) {
                turnoManual(p);
            } else {
                turnoIA(p, esBueno);
            }
        }
    }

    // Fase 2: Venenos y curas continuas. Buscamos quién de los vivos tiene algo puesto
    // y aplicamos su efecto usando la función procesarEstados() que hicimos en Personaje.
    private static void faseEstados() {
        System.out.println("\n[Fase de Estados]");
        for (Personaje p : equipoBueno) {
            if (p.estaVivo()) p.procesarEstados();
        }
        for (Personaje p : equipoMalo) {
            if (p.estaVivo()) p.procesarEstados();
        }
    }

    // Fase 3: Tiempos de recarga. A todos los personajes vivos les baja en 1 turno 
    // el círculo de espera de todas sus habilidades porque acaba de pasar una ronda.
    private static void faseCooldowns() {
        for (Personaje p : equipoBueno) {
            if (p.estaVivo()) p.reducirCooldowns();
        }
        for (Personaje p : equipoMalo) {
            if (p.estaVivo()) p.reducirCooldowns();
        }
    }

    // ===================== TURNO MANUAL =====================

    // Esta función maneja toda la lógica visual de cuando te toca elegir.
    // Te pregunta a quién pegar, con qué armarla, a quién curar... etc.
    private static void turnoManual(Personaje p) {
        List<Personaje> enemigos = obtenerVivos(equipoMalo);
        List<Personaje> aliados = obtenerVivos(equipoBueno);

        System.out.println("\n>>> Turno de " + p.nombre + " " + p.resumenCombate());
        System.out.println("  Acciones:");
        System.out.println("    1. Atacar con arma");

        // Mostrar ataques especiales
        List<AtaqueEspecial> lista = new ArrayList<>();
        for (int i = 0; i < p.ataquesEspeciales.size(); i++) {
            AtaqueEspecial ae = p.ataquesEspeciales.get(i);
            String disp = ae.puedeLanzarse(p) ? "" : " [NO DISPONIBLE]";
            System.out.println("    " + (i + 2) + ". " + ae.toString() + disp);
            lista.add(ae);
        }
        int opDefender = 2 + lista.size();
        System.out.println("    " + opDefender + ". Defender");

        int opcion = leerEntero("  Elige accion: ", 1, opDefender);

        if (opcion == 1) {
            // Atacar con arma
            Personaje obj = elegirObjetivo(enemigos, "enemigo");
            int vidaAntes = obj.vidaActual;
            System.out.println("    " + p.nombre + " ataca a " + obj.nombre + " con " + p.armaEquipada.nombre + "!");
            int danio = p.armaEquipada.calcularDanio(p, obj);
            obj.recibirDanio(danio);
            danioTotalBueno += (vidaAntes - obj.vidaActual);
            if (!obj.estaVivo()) System.out.println("    ** " + obj.nombre + " ha caido! **");

        } else if (opcion == opDefender) {
            // Defender
            p.defendiendo = true;
            System.out.println("    " + p.nombre + " se pone en guardia! (defensa x2)");

        } else {
            // Ataque especial
            int idx = opcion - 2;
            AtaqueEspecial ae = lista.get(idx);
            if (!ae.puedeLanzarse(p)) {
                // Si no puede, ataca con arma automaticamente
                System.out.println("    No puedes lanzar " + ae.getNombre() + "! Atacando con arma...");
                Personaje obj = enemigos.get(random.nextInt(enemigos.size()));
                int vidaAntes = obj.vidaActual;
                System.out.println("    " + p.nombre + " ataca a " + obj.nombre + " con " + p.armaEquipada.nombre + "!");
                obj.recibirDanio(p.armaEquipada.calcularDanio(p, obj));
                danioTotalBueno += (vidaAntes - obj.vidaActual);
            } else {
                if (ae.getTipoObjetivo() == AtaqueEspecial.TipoObjetivo.ALIADO) {
                    Personaje obj = elegirObjetivo(aliados, "aliado");
                    int vidaAntes = obj.vidaActual;
                    ae.lanzar(p, obj);
                    curacionTotalBueno += Math.max(0, obj.vidaActual - vidaAntes);
                } else {
                    Personaje obj = elegirObjetivo(enemigos, "enemigo");
                    int vidaAntes = obj.vidaActual;
                    ae.lanzar(p, obj);
                    danioTotalBueno += Math.max(0, vidaAntes - obj.vidaActual);
                    if (!obj.estaVivo()) System.out.println("    ** " + obj.nombre + " ha caido! **");
                }
            }
        }
    }

    // ===================== TURNO IA =====================

    // Lógica o "cerebro" de la IA enemiga.
    // Prioridad: 1° Salvar aliados muriendo. 2° Tirar ataques especiales (30% prob). 3° Pegar normal.
    private static void turnoIA(Personaje p, boolean esBueno) {
        List<Personaje> enemigos = esBueno ? obtenerVivos(equipoMalo) : obtenerVivos(equipoBueno);
        List<Personaje> aliados = esBueno ? obtenerVivos(equipoBueno) : obtenerVivos(equipoMalo);

        System.out.println("\n>>> Turno de " + p.nombre + " (IA)");

        // Prioridad 1: curar aliado herido (<50% HP)
        Personaje aliadoHerido = null;
        for (Personaje a : aliados) {
            if (a.estaVivo() && a.vidaActual < a.vidaMax * 0.5) {
                if (aliadoHerido == null || a.vidaActual < aliadoHerido.vidaActual) {
                    aliadoHerido = a;
                }
            }
        }
        if (aliadoHerido != null) {
            for (AtaqueEspecial ae : p.ataquesEspeciales) {
                if (ae.getTipoObjetivo() == AtaqueEspecial.TipoObjetivo.ALIADO && ae.puedeLanzarse(p)) {
                    int vidaAntes = aliadoHerido.vidaActual;
                    ae.lanzar(p, aliadoHerido);
                    int curado = Math.max(0, aliadoHerido.vidaActual - vidaAntes);
                    if (esBueno) curacionTotalBueno += curado;
                    else curacionTotalMalo += curado;
                    return;
                }
            }
        }

        // Prioridad 2: ataque especial ofensivo (30% probabilidad)
        if (random.nextInt(100) < 30) {
            for (AtaqueEspecial ae : p.ataquesEspeciales) {
                if (ae.getTipoObjetivo() == AtaqueEspecial.TipoObjetivo.ENEMIGO && ae.puedeLanzarse(p)) {
                    Personaje obj = enemigos.get(random.nextInt(enemigos.size()));
                    int vidaAntes = obj.vidaActual;
                    ae.lanzar(p, obj);
                    int danioHecho = Math.max(0, vidaAntes - obj.vidaActual);
                    if (esBueno) danioTotalBueno += danioHecho;
                    else danioTotalMalo += danioHecho;
                    if (!obj.estaVivo()) System.out.println("    ** " + obj.nombre + " ha caido! **");
                    return;
                }
            }
        }

        // Prioridad 3: atacar con arma
        if (enemigos.isEmpty()) return;
        Personaje obj = enemigos.get(random.nextInt(enemigos.size()));
        int vidaAntes = obj.vidaActual;
        System.out.println("    " + p.nombre + " ataca a " + obj.nombre + " con " + p.armaEquipada.nombre + "!");
        obj.recibirDanio(p.armaEquipada.calcularDanio(p, obj));
        int danioReal = vidaAntes - obj.vidaActual;
        if (esBueno) danioTotalBueno += danioReal;
        else danioTotalMalo += danioReal;
        if (!obj.estaVivo()) System.out.println("    ** " + obj.nombre + " ha caido! **");
    }

    // ===================== UTILIDADES =====================

    // Un simple asistente para imprimir una lista de héroes con numeritos (1, 2, 3...)
    // y pedirnos que elijamos a un "objetivo" (para darle con el ataque).
    private static Personaje elegirObjetivo(List<Personaje> opciones, String tipo) {
        System.out.println("  Elige " + tipo + ":");
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + opciones.get(i).resumenCombate());
        }
        int idx = leerEntero("  Objetivo: ", 1, opciones.size());
        return opciones.get(idx - 1);
    }

    // Función útil genérica: Lee nuestro teclado, pero de forma "segura". 
    // Significa que si escribes "hola" en vez de un "1", no rompe (crashea) el programa. 
    // Vuelve a preguntarte hasta que metas un número que sirva.
    private static int leerEntero(String mensaje, int min, int max) {
        while (true) {
            if (!mensaje.isEmpty()) System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= min && valor <= max) return valor;
                System.out.print("    Introduce un numero entre " + min + " y " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("    Entrada no valida. Introduce un numero: ");
            }
        }
    }

    // Devuelve una nueva pequeña lista extrayendo de la grande solo a los personajes 
    // que cumplen la condición de estar vivos (para no apuntar a cadáveres).
    private static List<Personaje> obtenerVivos(List<Personaje> equipo) {
        List<Personaje> vivos = new ArrayList<>();
        for (Personaje p : equipo) {
            if (p.estaVivo()) vivos.add(p);
        }
        return vivos;
    }

    // Revisa si uno de los dos equipos ha muerto por completo.
    // En ese caso corta el bucle infinito del combate.
    private static boolean combateTerminado() {
        return todosMuertos(equipoBueno) || todosMuertos(equipoMalo);
    }

    // Función pequeña usada arriba, repasa si hay al menos uno vivo 
    // (devuelve false en cuanto encuentra uno vivo). Si no devuelve nada, ¡están todos muertos!
    private static boolean todosMuertos(List<Personaje> equipo) {
        for (Personaje p : equipo) {
            if (p.estaVivo()) return false;
        }
        return true;
    }

    // ===================== VISUALIZACION =====================

    // Printea cómo iba todo justo después de terminar un turno para que sepamos 
    // cómo van nuestras barras de vida y quién sigue vivo.
    private static void mostrarEquipos() {
        System.out.println("  EQUIPO BUENO:");
        for (Personaje p : equipoBueno) {
            String tag = p.estaVivo() ? "  " : "  [MUERTO] ";
            System.out.println("    " + tag + p.resumenCombate());
        }
        System.out.println("  EQUIPO MALO:");
        for (Personaje p : equipoMalo) {
            String tag = p.estaVivo() ? "  " : "  [MUERTO] ";
            System.out.println("    " + tag + p.resumenCombate());
        }
    }

    // Textos bonitos finales con los resultados de toda la pelea.
    private static void mostrarResumenFinal() {
        System.out.println("\n====================================================");
        System.out.println("              COMBATE FINALIZADO");
        System.out.println("====================================================");
        System.out.println("  Rondas jugadas: " + ronda);

        if (todosMuertos(equipoMalo)) {
            System.out.println("  VICTORIA DEL EQUIPO BUENO!");
        } else {
            System.out.println("  VICTORIA DEL EQUIPO MALO!");
        }

        System.out.println("\n--- Estado final ---");
        mostrarEquipos();

        System.out.println("\n--- Estadisticas ---");
        System.out.println("  Equipo Bueno -> Danio: " + danioTotalBueno + " | Curacion: " + curacionTotalBueno);
        System.out.println("  Equipo Malo  -> Danio: " + danioTotalMalo + " | Curacion: " + curacionTotalMalo);
        System.out.println("====================================================\n");
    }
}