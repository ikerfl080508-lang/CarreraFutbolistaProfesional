import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Fecha inicio: 22/10/2026
Autor: IFL
Desc: Carrera profesional de futbolista

He añadido lo último visto en clase (public static) para simplificar mucho código repetido.
*/

public class Carreraprofesional {

    // ===== COLORES ANSI PARA LA TERMINAL =====
    static final String RESET = "\u001B[0m";
    static final String ROJO = "\u001B[31m";
    static final String VERDE = "\u001B[32m";
    static final String AMARILLO = "\u001B[33m";
    static final String AZUL = "\u001B[34m";
    static final String PURPURA = "\u001B[35m";
    static final String CIAN = "\u001B[36m";
    static final String BOLD = "\u001B[1m";

    // ===== DATOS COMPARTIDOS Y ESTADO DEL JUGADOR =====
    static final String LINEA = AZUL + "========================================" + RESET;
    static BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    
    // Estado del jugador que evoluciona durante la partida
    static int energia = 100;
    static int dinero = 500;
    static int puntosCarrera = 0;
    static int partidosJugados = 0;
    
    static double altura;
    static double peso;
    static int posicionElegida; // 1: Delantero, 2: Centrocampista, 3: Defensa, 4: Portero

    // Array unidimensional
    static String[] inventarioLogros = { "Bota de Cobre", "Vacante", "Vacante", "Vacante" };

    // Uso "static" antes de cada variable declarada fuera de cualquier public static para que se puedan usar en todas ellas.
    // Si declarase esas variables dentro del main, las tendría que declarar de nuevo en los demás public static como LeerOpcionMenu o LeerOpcionSimple
    // El "static" lo necesito porque si no, al compilar, me daría el siguiente error: "Non-static variable [nombre de variable] cannot be referenced from a static context"

    public static void main(String[] args) throws IOException {

        System.out.println(LINEA);
        System.out.printf(BOLD + CIAN + "%25s%n" + RESET, "¡Bienvenido a tu carrera de futbolista profesional! 😊");
        System.out.println(LINEA);
        System.out.println("\nSimulador de carrera profesional de un futbolista en la UEFA Champions League. 🏆");
        System.out.println("Gestiona tu energía, entrena, gana dinero y supera los partidos eliminatorios. ⚔️");
        System.out.println(AMARILLO + "¡Cuidado! Si tu energía llega a 0, tu jugador se lesionará y perderás. 🤕" + RESET);

        System.out.print("\nPor favor, introduzca el nombre de su jugador/a: ");
        String nombre = lector.readLine();

        System.out.println("\nDatos físicos de tu jugador/a:"); // Todavía no he implementado que influyan en las decisiones
        boolean alturaValida = false;
        while (!alturaValida) {
            System.out.print("Por favor, introduzca la altura en centímetros: ");
            String entrada = lector.readLine().trim();
            
            if (entrada.length() > 0) { // Para evitar que no escriba nada
                boolean esValido = true;
                int puntos = 0;
                
                for (int i = 0; i < entrada.length(); i++) {
                    char c = entrada.charAt(i);
                    if (c == '.') {
                        puntos++;
                        if (puntos > 1) { // Compruebo que no escriba más de un punto
                            esValido = false;
                        }
                    } else if (c < '0' || c > '9') {
                        esValido = false;
                    }
                }
                
                if (esValido) {
                    altura = Double.parseDouble(entrada);
                    if (altura > 0) { // Para evitar que no escriba nada
                        alturaValida = true;
                    } else {
                        System.out.println(ROJO + "Error: La altura debe ser mayor que 0." + RESET);
                    }
                } else {
                    System.out.println(ROJO + "Error: Debe introducir un número válido." + RESET);
                }
            } else {
                System.out.println(ROJO + "Error: Entrada vacía. Inténtalo de nuevo." + RESET);
            }
        }
        boolean pesoValido = false;
        while (!pesoValido) {
            System.out.print("Por favor, introduzca el peso en kg: ");
            String entrada = lector.readLine().trim();
            
            if (entrada.length() > 0) { // Para evitar que no escriba nada
                boolean esValido = true;
                int puntos = 0;
                
                for (int i = 0; i < entrada.length(); i++) {
                    char c = entrada.charAt(i);
                    if (c == '.') {
                        puntos++;
                        if (puntos > 1) { // Compruebo que no escriba más de un punto
                            esValido = false;
                        }
                    } else if (c < '0' || c > '9') {
                        esValido = false;
                    }
                }
                
                if (esValido) {
                    peso = Double.parseDouble(entrada);
                    if (peso > 0) { // Para evitar que no escriba nada
                        pesoValido = true;
                    } else {
                        System.out.println(ROJO + "Error: El peso debe ser mayor que 0." + RESET);
                    }
                } else {
                    System.out.println(ROJO + "Error: Debe introducir un número válido." + RESET);
                }
            } else {
                System.out.println(ROJO + "Error: Entrada vacía. Inténtalo de nuevo." + RESET);
            }
        }

        // Selección de equipo usando arrays unidimensionales
        System.out.println("Perfecto, " + BOLD + nombre + RESET + ". Selecciona tu equipo:");
        String[] equipos = { "Real Madrid C.F.", "F.C. Barcelona", "Bayern de Múnich", "Paris Saint-Germain" };
        int equipo = leerOpcionMenu(1, equipos.length, equipos);
        System.out.println(VERDE + "¡Has fichado por el " + equipos[equipo - 1] + "! ⚽" + RESET);

        // Selección de posición
        System.out.println("\nSelecciona tu posición en el campo:");
        String[] posiciones = { "Delantero", "Centrocampista", "Defensa", "Portero" };
        posicionElegida = leerOpcionMenu(1, posiciones.length, posiciones);
        System.out.println(VERDE + "¡Jugarás como " + posiciones[posicionElegida - 1] + "! ⚽" + RESET);

        // MENÚ PRINCIPAL INTERACTIVO
        boolean jugando = true;
        while (jugando) {
            System.out.println(LINEA);
            System.out.printf(BOLD + "%25s%n" + RESET, " MENÚ PRINCIPAL ");
            System.out.println(LINEA);
            System.out.println("1. Jugar siguiente partido de Champions ⚔️");
            System.out.println("2. Entrenar duro (Ganas puntos, ganas dinero y gastas energía). Tienes " + energia + " de energía 🔋");
            System.out.println("3. Descansar en el spa (Recuperas energía y gastas dinero) 🧖🏽‍♂️");
            System.out.println("4. Consultar estado del jugador e inventario 👑");
            System.out.println("5. Retirarse 🔚");

            int opcionMenu = leerOpcionSimple(1, 5);

            switch (opcionMenu) {
                case 1:
                    if (energia <= 20) {
                        System.out.println(ROJO + "Estás demasiado cansado para jugar. ¡Descansa antes de saltar al campo! 🪫" + RESET);
                    } else {
                        partidosJugados++;
                        boolean exito;
                        if (partidosJugados == 1) {
                            exito = jugarCuartos();
                        } else if (partidosJugados == 2) {
                            exito = jugarSemifinal();
                        } else {
                            exito = jugarFinal();
                        }

                        if (!exito) {
                            System.out.println(ROJO + "\n=== GAME OVER: Has sido eliminado de la competición. ❌ ===" + RESET);
                            jugando = false;
                        } else if (partidosJugados >= 3) {
                            System.out.println(VERDE + BOLD + "\n=== 🎉🏆 ¡ENHORABUENA! Has ganado la Champions League 🏆🎉 ===" + RESET);
                            jugando = false;
                        }
                    }
                    break;

                case 2:
                    System.out.println(VERDE + "Has sido el mejor en el entrenamiento físico. 🏋️" + RESET);
                    puntosCarrera += 50;
                    energia -= 25; // Se gasta más energía en el entrenamiento porque se hace un esfuerzo físico mayor que en los partidos
                    dinero += 100;
                    if (energia < 0){
                        energia = 0;
                    }
                    break;

                case 3:
                    if (dinero < 150) {
                        System.out.println(ROJO + "No tienes suficiente dinero (cuesta 150€). 💸" + RESET);
                    } else {
                        dinero -= 150;
                        energia += 40;
                        if (energia > 100){
                            energia = 100;
                        }
                        System.out.println(VERDE + "Te has recuperado en el spa. Energía recuperada. 🔋" + RESET);
                    }
                    break;

                case 4:
                    mostrarEstadoJugador();
                    break;

                case 5:
                    System.out.println(AMARILLO + "Enhorabuena por tu carrera 👏" + RESET);
                    jugando = false;
                    break;
            }

            // Condición de derrota por agotamiento de energía
            if (energia <= 0) {
                System.out.println(ROJO + "\nTe has desmayado por cansancio absoluto. ¡Has perdido la temporada! 🤕" + RESET);
                jugando = false;
            }
        }
    }

    public static int leerOpcionMenu(int min, int max, String[] opciones) throws IOException {
        System.out.println(LINEA);
        for (int i = 0; i < opciones.length; i++) {
            System.out.println(CIAN + (i + 1) + ". " + RESET + opciones[i]);
        }
        System.out.println(LINEA);
        return leerOpcionSimple(min, max);
    }

    public static int leerOpcionSimple(int min, int max) throws IOException {
        int opcion = -1;
        boolean valido = false;
        
        while (!valido) {
            System.out.print("Por favor, introduce una opción (" + min + " - " + max + "): ");
            String entrada = lector.readLine().trim();
            
            if (entrada.length() > 0) {
                opcion = 0;
                boolean esNumero = true;
                for (int i = 0; i < entrada.length(); i++) {
                    char c = entrada.charAt(i);
                    if (c < '0' || c > '9') {
                        esNumero = false;
                    }
                }
                
                if (esNumero) {
                    opcion = Integer.parseInt(entrada);
                    if (opcion >= min && opcion <= max) {
                        valido = true;
                    } else {
                        System.out.println(ROJO + "Error: El número debe estar entre " + min + " y " + max + "." + RESET);
                    }
                } else {
                    System.out.println(ROJO + "Error: Debe introducir obligatoriamente un número válido." + RESET);
                }
            } else {
                System.out.println(ROJO + "Error: Entrada vacía. Inténtalo de nuevo." + RESET);
            }
        }
        return opcion;
    }

    // CONSULTA DE ESTADO E INVENTARIO
    public static void mostrarEstadoJugador() {
        System.out.println(LINEA);
        System.out.println(BOLD + CIAN + " ESTADO ACTUAL DEL JUGADOR " + RESET);
        System.out.println(LINEA);
        System.out.println("- Altura: " + altura + " cm");
        System.out.println("- Peso: " + peso + " kg");
        System.out.println("- Energía disponible: " + energia + " / 100");
        System.out.println("- Dinero acumulado: " + dinero + " €");
        System.out.println("- Puntos de carrera: " + puntosCarrera);
        System.out.println("- Partidos de Champions superados: " + partidosJugados);
        
        // Recorrido de array unidimensional
        System.out.println("\n[INVENTARIO DE TROFEOS Y LOGROS]:");
        for (int i = 0; i < inventarioLogros.length; i++) {
            System.out.println("  * Posición " + (i + 1) + ": " + inventarioLogros[i]);
        }
        System.out.println(LINEA);
    }

    // SISTEMA DE TANDA DE PENALTIS (Depende de si eres portero o jugador de campo)
    public static boolean gestionarPenaltisCuartos() throws IOException {
        System.out.println(AMARILLO + "\n>>> ¡EMPATE EN EL MARCADOR! EL PARTIDO SE DECIDE EN TANDA DE PENALTIS ⚔️ <<<" + RESET);
        
        int eleccionUsuario;
        int ladoExito;
        
        if (posicionElegida == 4) { // Portero
            System.out.println("Te colocas bajo palos. ¡Eres el portero y debes adivinar dónde tirará el penalti el rival!");
            String[] opcionesPortero = { "Lanzarse a la izquierda", "Quedarse en el centro", "Lanzarse a la derecha" };
            eleccionUsuario = leerOpcionMenu(1, 3, opcionesPortero);

            ladoExito = 1; 
            
            if (eleccionUsuario == ladoExito) {
                System.out.println(VERDE + "¡¡¡ESPECTACULAR PARADA!!! Adivinaste la trayectoria y blocas el balón. ¡Ganáis en los penaltis! ✅" + RESET);
                puntosCarrera += 100;
                inventarioLogros[1] = "Medalla Semifinalista 🥉";
                dinero += 300;
                return true;
            } else {
                System.out.println(ROJO + "El lanzador engaña a tu estirada y marca gol. Habéis perdido en los penaltis. ❌" + RESET);
                return false;
            }
        } else { // Jugador de campo
            System.out.println("Te toca asumir la máxima responsabilidad. Vas a lanzar el penalti decisivo.");
            String[] opcionesLanzador = { "Chutar fuerte a la escuadra izquierda", "Tirar raso al centro", "Colocar ajustado al palo derecho" };
            eleccionUsuario = leerOpcionMenu(1, 3, opcionesLanzador);
            
            if (eleccionUsuario == 1 || eleccionUsuario == 2) {
                System.out.println(VERDE + "¡¡¡GOOOOL!!! Engañaste por completo al portero. ¡Victoria! ✅" + RESET);
                puntosCarrera += 100;
                inventarioLogros[1] = "Medalla Semifinalista 🥉";
                dinero += 300;
                return true;
            } else {
                System.out.println(ROJO + "¡Lo adivinó el portero! Tu disparo se estrella contra sus guantes. Eliminados. ❌" + RESET);
                return false;
            }
        }
    }

    public static boolean gestionarPenaltisSemifinales() throws IOException {
        System.out.println(AMARILLO + "\n>>> ¡EMPATE EN EL MARCADOR! EL PARTIDO SE DECIDE EN TANDA DE PENALTIS ⚔️ <<<" + RESET);
        
        int eleccionUsuario;
        int ladoExito;
        
        if (posicionElegida == 4) { // Portero
            System.out.println("Te colocas bajo palos. ¡Eres el portero y debes adivinar dónde tirará el penalti el rival!");
            String[] opcionesPortero = { "Lanzarse a la izquierda", "Quedarse en el centro", "Lanzarse a la derecha" };
            eleccionUsuario = leerOpcionMenu(1, 3, opcionesPortero);

            ladoExito = 3; 
            
            if (eleccionUsuario == ladoExito) {
                System.out.println(VERDE + "¡¡¡ESPECTACULAR PARADA!!! Adivinaste la trayectoria y blocas el balón. ¡Ganáis en los penaltis! ✅" + RESET);
                puntosCarrera += 200;
                inventarioLogros[2] = "Medalla Finalista 🥈";
                dinero += 500;
                return true;
            } else {
                System.out.println(ROJO + "El lanzador engaña a tu estirada y marca gol. Habéis perdido en los penaltis. ❌" + RESET);
                return false;
            }
        } else { // Jugador de campo
            System.out.println("Te toca asumir la máxima responsabilidad. Vas a lanzar el penalti decisivo.");
            String[] opcionesLanzador = { "Chutar fuerte a la escuadra izquierda", "Tirar raso al centro", "Colocar ajustado al palo derecho" };
            eleccionUsuario = leerOpcionMenu(1, 3, opcionesLanzador);
            
            if (eleccionUsuario == 3 || eleccionUsuario == 2) {
                System.out.println(VERDE + "¡¡¡GOOOOL!!! Engañaste por completo al portero. ¡Victoria! ✅" + RESET);
                puntosCarrera += 200;
                inventarioLogros[2] = "Medalla Finalista 🥈";
                dinero += 500;
                return true;
            } else {
                System.out.println(ROJO + "¡Lo adivinó el portero! Tu disparo se estrella contra sus guantes. Eliminados. ❌" + RESET);
                return false;
            }
        }
    }

    public static boolean gestionarPenaltisFinal() throws IOException {
        System.out.println(AMARILLO + "\n>>> ¡EMPATE EN EL MARCADOR! EL PARTIDO SE DECIDE EN TANDA DE PENALTIS ⚔️ <<<" + RESET);
        
        int eleccionUsuario;
        int ladoExito;
        
        if (posicionElegida == 4) { // Portero
            System.out.println("Te colocas bajo palos. ¡Eres el portero y debes adivinar dónde tirará el penalti el rival!");
            String[] opcionesPortero = { "Lanzarse a la izquierda", "Quedarse en el centro", "Lanzarse a la derecha" };
            eleccionUsuario = leerOpcionMenu(1, 3, opcionesPortero);

            ladoExito = 2; 
            
            if (eleccionUsuario == ladoExito) {
                System.out.println(VERDE + "¡¡¡ESPECTACULAR PARADA!!! Adivinaste la trayectoria y blocas el balón. ¡Ganáis en los penaltis! ✅" + RESET);
                inventarioLogros[3] = "Trofeo Champions League 🏆";
                dinero += 1000;
                puntosCarrera += 500;
                return true;
            } else {
                System.out.println(ROJO + "El lanzador engaña a tu estirada y marca gol. Habéis perdido en los penaltis. ❌" + RESET);
                return false;
            }
        } else { // Jugador de campo
            System.out.println("Te toca asumir la máxima responsabilidad. Vas a lanzar el penalti decisivo.");
            String[] opcionesLanzador = { "Chutar fuerte a la escuadra izquierda", "Tirar raso al centro", "Colocar ajustado al palo derecho" };
            eleccionUsuario = leerOpcionMenu(1, 3, opcionesLanzador);
            
            if (eleccionUsuario == 1 || eleccionUsuario == 3) {
                System.out.println(VERDE + "¡¡¡GOOOOL!!! Engañaste por completo al portero. ¡Victoria! ✅" + RESET);
                inventarioLogros[3] = "Trofeo Champions League 🏆";
                dinero += 1000;
                puntosCarrera += 500;
                return true;
            } else {
                System.out.println(ROJO + "¡Lo adivinó el portero! Tu disparo se estrella contra sus guantes. Eliminados. ❌" + RESET);
                return false;
            }
        }
    }

    // PARTIDOS CON EVENTOS SEGÚN LA POSICIÓN

    public static boolean jugarCuartos() throws IOException {
        System.out.println(PURPURA + "\n>>> CUARTOS DE FINAL: 🆚 BORUSSIA DORTMUND <<<" + RESET);
        energia -= 20;
        int golesAFavor = 0;
        int golesEnContra = 0;
        
        // EVENTO 1: Minuto 15
        System.out.println("\n--- Minuto 15 del Partido ---");
        String[] eventos1;
        if (posicionElegida == 1) { // Delantero
            eventos1 = new String[]{ "Desmarcarte en profundidad y buscar el mano a mano", "Pasar el balón a tu compañero que está solo", "Centrar el balón al punto de penalti" };
        } else if (posicionElegida == 2) { // Centrocampista
            eventos1 = new String[]{ "Filtrar un pase largo al hueco", "Centrar el balón buscando el remate de algún compañero", "Cambiar el balón de lado con un pase alto" };
        } else if (posicionElegida == 3) { // Defensa
            eventos1 = new String[]{ "Anticiparte al delantero rival", "Entrada arriesgada pero calculada", "Aguantar el regate esperando ayuda de algún compañero" };
        } else { // Portero (4)
            eventos1 = new String[]{ "Salir con autoridad a blocar un centro lateral", "Te chutan un balón ajustado y te estiras para intentar llegar", "Sales con los puños a parar un centro al punto de penalti" };
        }
        
        int op1 = leerOpcionMenu(1, 3, eventos1);
        if (op1 == 2) {
            System.out.println(VERDE + "¡Acción excelente! Consigues desequilibrar y anotas un GOL A FAVOR. ✅" + RESET);
            golesAFavor++;
        } else if (op1 == 1) {
            System.out.println(AMARILLO + "Ningún gol. La jugada se neutraliza tácticamente sin incidencias. ⚔️" + RESET);
        } else {
            System.out.println(ROJO + "¡Error grave! El rival aprovecha el fallo y anota GOL EN CONTRA. ❌" + RESET);
            golesEnContra++;
        }
        System.out.println(BOLD + CIAN + "   [MARCADOR PARCIAL] --> " + golesAFavor + " - " + golesEnContra + RESET);

        // EVENTO 2: Minuto 50
        System.out.println("\n--- Minuto 50 del Partido ---");
        String[] eventos2;
        if (posicionElegida == 1) {
            eventos2 = new String[]{ "Rematar de cabeza un centro", "Chutar desde la frontal del área", "Adelantarte al pase que te da tu mediocentro" };
        } else if (posicionElegida == 2) {
            eventos2 = new String[]{ "Conducir y disparar fuerte buscando sorprender", "Cambio de juego orientado a la banda", "Pasar el balón a tu delantero que tira un desmarque" };
        } else if (posicionElegida == 3) {
            eventos2 = new String[]{ "Estirarte para intentar llegar a cortar un balón", "Despejar de cabeza con seguridad", "Avanzar líneas con propósito de dejar en fuera de juego al delantero rival" };
        } else {
            eventos2 = new String[]{ "Realizar una estirada a disparo lejano", "Blocar un chute desde la frontal", "Tirarte al suelo en un mano a mano con el delantero" };
        }

        int op2 = leerOpcionMenu(1, 3, eventos2);
        if (op2 == 3) {
            System.out.println(VERDE + "¡Impresionante! Esto se traduce en un GOL A FAVOR. ✅" + RESET);
            golesAFavor++;
        } else if (op2 == 2) {
            System.out.println(AMARILLO + "Ningún gol. El encuentro sigue muy trabado en el centro. ⚔️" + RESET);
        } else {
            System.out.println(ROJO + "¡GRAN ERROR! El rival castiga con un GOL EN CONTRA. ❌" + RESET);
            golesEnContra++;
        }
        System.out.println(BOLD + CIAN + "   [MARCADOR PARCIAL] --> " + golesAFavor + " - " + golesEnContra + RESET);

        // EVENTO 3: Minuto 80
        System.out.println("\n--- Minuto 80 del Partido ---");
        String[] eventos3;
        if (posicionElegida == 1) {
            eventos3 = new String[]{ "Definir con una vaselina", "Tirar con tu pierna mala", "Centrar el balón con tu pierna mala" };
        } else if (posicionElegida == 2) {
            eventos3 = new String[]{ "Intentar filtrar un pase de la muerte", "Tocar atrás para perder tiempo", "Mover el balón de un lado a otro" };
        } else if (posicionElegida == 3) {
            eventos3 = new String[]{ "Realizar una entrada agresiva al delantero que se va solo", "Conservar la posición para defender en bloque", "Romper el fuera de juego" };
        } else {
            eventos3 = new String[]{ "Jugar un balón alto con tu extremo", "Gritar a la defensa para que avancen", "Quedarte bajo los palos esperando parar un posible tiro" };
        }

        int op3 = leerOpcionMenu(1, 3, eventos3);
        if (op3 == 1) {
            System.out.println(VERDE + "¡Acción culminada con éxito! Sube un nuevo GOL A FAVOR al electrónico. ✅" + RESET);
            golesAFavor++;
        } else if (op3 == 3) {
            System.out.println(AMARILLO + "Ningún gol. Control total para mantener la calma sin goles. ⚔️" + RESET);
        } else {
            System.out.println(ROJO + "¡Error al final del partido! GOL EN CONTRA del Borussia. ❌" + RESET);
            golesEnContra++;
        }
        System.out.println(BOLD + CIAN + "   [MARCADOR PARCIAL] --> " + golesAFavor + " - " + golesEnContra + RESET);

        // RESOLUCIÓN DE CUARTOS

        System.out.println(BOLD + AMARILLO + "\n[MARCADOR FINAL DE CUARTOS]: " + golesAFavor + " - " + golesEnContra + RESET);
        if (golesAFavor > golesEnContra) {
            System.out.println(VERDE + "¡Victoria en el tiempo reglamentario! ✅" + RESET);
            puntosCarrera += 100;
            inventarioLogros[1] = "Medalla Semifinalista 🥉";
            dinero += 300;
            return true;
        } else if (golesAFavor == golesEnContra) {
            System.out.println(AMARILLO + "¡Empate absoluto! El partido se va a penaltis. ⚔️" + RESET);
            return gestionarPenaltisCuartos();
        } else {
            System.out.println(ROJO + "Derrota y eliminación en cuartos. ❌" + RESET);
            return false;
        }
    }

    public static boolean jugarSemifinal() throws IOException {
        System.out.println(PURPURA + "\n>>> SEMIFINALES: 🆚 ARSENAL <<<" + RESET);
        energia -= 20;
        int golesAFavor = 0;
        int golesEnContra = 0;
        
        // EVENTO 1: Minuto 25
        System.out.println("\n--- Minuto 25 del Partido ---");
        String[] eventos1;
        if (posicionElegida == 1) {
            eventos1 = new String[]{ "Tirar un desmarque", "Presionar la salida del central rival", "Chutar fuerte buscando sorprender" };
        } else if (posicionElegida == 2) {
            eventos1 = new String[]{ "Presión intensa para robar el balón", "Pase al hueco medido para el delantero", "Pase largo hacia el extremo en la banda" };
        } else if (posicionElegida == 3) {
            eventos1 = new String[]{ "Anticipación por alto", "Meter cuerpo al extremo", "Entrar fuerte al delantero" };
        } else {
            eventos1 = new String[]{ "Intentar blocar un disparo cruzado", "Puños firmes para despejar un córner", "Saque de puerta en corto" };
        }

        int op1 = leerOpcionMenu(1, 3, eventos1);
        if (op1 == 1) {
            System.out.println(VERDE + "¡Acción estelar! Lográis materializar un GOL A FAVOR. ✅" + RESET);
            golesAFavor++;
        } else if (op1 == 2) {
            System.out.println(AMARILLO + "Ningún gol. Máxima igualdad táctica sobre el césped. ⚔️" + RESET);
        } else {
            System.out.println(ROJO + "¡Desajuste defensivo! El Arsenal anota GOL EN CONTRA. ❌" + RESET);
            golesEnContra++;
        }
        System.out.println(BOLD + CIAN + "   [MARCADOR PARCIAL] --> " + golesAFavor + " - " + golesEnContra + RESET);

        // EVENTO 2: Minuto 60
        System.out.println("\n--- Minuto 60 del Partido ---");
        String[] eventos2;
        if (posicionElegida == 1) {
            eventos2 = new String[]{ "Saltar en un córner en contra para despejar", "Buscar paredes con los centrocampistas dentro del área rival", "Intentar ganar un duelo aéreo" };
        } else if (posicionElegida == 2) {
            eventos2 = new String[]{ "Pase entre líneas", "Presionar en bloque la salida de balón", "Hacer repliegue rápido después de un córner" };
        } else if (posicionElegida == 3) {
            eventos2 = new String[]{ "Intentar sacar un balón bajo palos", "Mantener la concentración en bloque bajo", "Entrar a un regate del extremo" };
        } else {
            eventos2 = new String[]{ "Intentar controlar con el pecho un pase de tu defensa", "Parar el balón en dos tiempos", "Tirarte a la derecha en un 1vs1" };
        }

        int op2 = leerOpcionMenu(1, 3, eventos2);
        if (op2 == 2) {
            System.out.println(VERDE + "¡Genialidad táctica! Sube otro GOL A FAVOR al marcador. ✅" + RESET);
            golesAFavor++;
        } else if (op2 == 3) {
            System.out.println(AMARILLO + "Ningún gol. Resistencia firme sin alterar el marcador. ⚔️" + RESET);
        } else {
            System.out.println(ROJO + "¡Error imperdonable! El Arsenal marca GOL EN CONTRA. ❌" + RESET);
            golesEnContra++;
        }
        System.out.println(BOLD + CIAN + "   [MARCADOR PARCIAL] --> " + golesAFavor + " - " + golesEnContra + RESET);

        // EVENTO 3: Minuto 85
        System.out.println("\n--- Minuto 85 del Partido ---");
        String[] eventos3;
        if (posicionElegida == 1) {
            eventos3 = new String[]{ "Vaselina ante la salida del portero", "Aguantar resultado y tocar atrás", "Disparo fuerte desde 35 metros" };
        } else if (posicionElegida == 2) {
            eventos3 = new String[]{ "Cambio de ritmo al partido", "Temporizar y conservar la posesión", "Pase alto peligroso a tu portero" };
        } else if (posicionElegida == 3) {
            eventos3 = new String[]{ "Entrada al borde del área", "Cubrir la posición de tu lateral que subió en un ataque", "Falta táctica para cortar contra" };
        } else {
            eventos3 = new String[]{ "Volar para intentar sacar un balón a la escuadra", "Intentar blocar el balón colgado", "Saque de puerta largo a tu extremo" };
        }

        int op3 = leerOpcionMenu(1, 3, eventos3);
        if (op3 == 1) {
            System.out.println(VERDE + "¡Jugadón definitivo! Anotáis un GOL A FAVOR clave. ✅" + RESET);
            golesAFavor++;
        } else if (op3 == 3) {
            System.out.println(AMARILLO + "Ningún gol. Control de los tiempos sin sobresaltos. ⚔️" + RESET);
        } else {
            System.out.println(ROJO + "¡Jarro de agua fría! GOL EN CONTRA en el tramo final. ❌" + RESET);
            golesEnContra++;
        }
        System.out.println(BOLD + CIAN + "   [MARCADOR PARCIAL] --> " + golesAFavor + " - " + golesEnContra + RESET);

        // RESOLUCIÓN DE SEMIFINAL

        System.out.println(BOLD + AMARILLO + "\n[MARCADOR FINAL DE SEMIFINAL]: " + golesAFavor + " - " + golesEnContra + RESET);
        if (golesAFavor > golesEnContra) {
            System.out.println(VERDE + "¡Victoria brillante en la semifinal! ✅" + RESET);
            puntosCarrera += 200;
            inventarioLogros[2] = "Medalla Finalista 🥈";
            dinero += 500;
            return true;
        } else if (golesAFavor == golesEnContra) {
            System.out.println(AMARILLO + "¡Empate absoluto! Nos jugamos el pase en los penaltis. ⚔️" + RESET);
            return gestionarPenaltisSemifinales();
        } else {
            System.out.println(ROJO + "Derrota dolorosa en semifinales. ❌" + RESET);
            dinero -= 100;
            return false;
        }
    }

    public static boolean jugarFinal() throws IOException {
        System.out.println(PURPURA + "\n>>> GRAN FINAL: 🆚 JUVENTUS DE TURÍN <<<" + RESET);
        energia -= 20;
        int golesAFavor = 0;
        int golesEnContra = 0;
        
        // EVENTO 1: Minuto 30
        System.out.println("\n--- Minuto 30 del Partido ---");
        String[] eventos1;
        if (posicionElegida == 1) {
            eventos1 = new String[]{ "Cabezazo potente tras córner", "Intentar sorprender con disparo lejano", "Control con el muslo y chutar de volea" };
        } else if (posicionElegida == 2) {
            eventos1 = new String[]{ "Centrar córner al punto de penalti", "Centrar un córner al primer palo", "Centrar córner al segundo palo" };
        } else if (posicionElegida == 3) {
            eventos1 = new String[]{ "Intentar bloquear el disparo del extremo rival", "Meter carga al delantero rival", "Saltar para despejar un córner" };
        } else {
            eventos1 = new String[]{ "Lanzarse abajo a la derecha para tapar un tiro", "Quedarte en el centro porque piensas que el balón se va fuera", "Lanzarse arriba a la derecha para tapar un tiro" };
        }

        int op1 = leerOpcionMenu(1, 3, eventos1);
        if (op1 == 3) {
            System.out.println(VERDE + "¡Acierto total! Rompéis la portería italiana con un GOL A FAVOR. ✅" + RESET);
            golesAFavor++;
        } else if (op1 == 2) {
            System.out.println(AMARILLO + "Ningún gol. Máxima tensión y respeto mutuo en el campo. ⚔️" + RESET);
        } else {
            System.out.println(ROJO + "¡Error defensivo! La Juventus anota GOL EN CONTRA. ❌" + RESET);
            golesEnContra++;
        }
        System.out.println(BOLD + CIAN + "   [MARCADOR PARCIAL] --> " + golesAFavor + " - " + golesEnContra + RESET);

        // EVENTO 2: Minuto 70
        System.out.println("\n--- Minuto 70 del Partido ---");
        String[] eventos2;
        if (posicionElegida == 1) {
            eventos2 = new String[]{ "Saltar lo máximo para rematar un centro", "Chute muy potente a portería desde 27 metros", "Dar al balón de volea" };
        } else if (posicionElegida == 2) {
            eventos2 = new String[]{ "Intentar hacer una jugada ensayada", "Pase alto al delantero", "Cambio de juego a la otra banda" };
        } else if (posicionElegida == 3) {
            eventos2 = new String[]{ "Entrada agresiva al delantero", "Mantener la línea defensiva alta para presionar", "Saltar contra el delantero por un balón aéreo" };
        } else {
            eventos2 = new String[]{ "Controlar un pase de tu defensa con las manos", "Controlar un pase de tu defensa con la cabeza", "Controlar un pase de tu defensa con los pies" };
        }

        int op2 = leerOpcionMenu(1, 3, eventos2);
        if (op2 == 2) {
            System.out.println(VERDE + "¡Acción magistral! El balón besa la red: GOL A FAVOR. ✅" + RESET);
            golesAFavor++;
        } else if (op2 == 3) {
            System.out.println(AMARILLO + "Ningún gol. Control táctico absoluto sin sorpresas. ⚔️" + RESET);
        } else {
            System.out.println(ROJO + "¡Mal calculado! GOL EN CONTRA de la Juventus. ❌" + RESET);
            golesEnContra++;
        }
        System.out.println(BOLD + CIAN + "   [MARCADOR PARCIAL] --> " + golesAFavor + " - " + golesEnContra + RESET);

        // EVENTO 3: Minuto 89
        System.out.println("\n--- Minuto 89 del Partido ---");
        String[] eventos3;
        if (posicionElegida == 1) {
            eventos3 = new String[]{ "Chilena en el descuento", "Perder tiempo en el córner rival", "Chutar desde 39 metros porque ves al portero adelantado" };
        } else if (posicionElegida == 2) {
            eventos3 = new String[]{ "Pase filtrado a la espalda rival", "Pase a tu extremo que está solo", "Pase atrás para perder tiempo" };
        } else if (posicionElegida == 3) {
            eventos3 = new String[]{ "Saltar en un balón aéreo contra el delantero rival", "Despejar el balón con un pase alto", "Regatear al delantero porque no tienes línea de pase" };
        } else {
            eventos3 = new String[]{ "Subir un córner en la última jugada del partido", "Perder tiempo", "Salir a despejar de puños un balón en el punto de penalti" };
        }

        int op3 = leerOpcionMenu(1, 3, eventos3);
        if (op3 == 1) {
            System.out.println(VERDE + BOLD + "¡¡¡GOLAZO HISTÓRICO EN EL ÚLTIMO SUSPIRO!!! ¡GOL A FAVOR! ✅" + RESET);
            golesAFavor++;
        } else if (op3 == 2) {
            System.out.println(AMARILLO + "Ningún gol. Consumo de los últimos segundos con maestría. ⚔️" + RESET);
        } else {
            System.out.println(ROJO + "¡Mazazo definitivo! GOL EN CONTRA en el tiempo añadido. ❌" + RESET);
            golesEnContra++;
        }
        System.out.println(BOLD + CIAN + "   [MARCADOR PARCIAL] --> " + golesAFavor + " - " + golesEnContra + RESET);

        // RESOLUCIÓN DE LA FINAL

        System.out.println(BOLD + AMARILLO + "\n[MARCADOR FINAL DE LA GRAN FINAL]: " + golesAFavor + " - " + golesEnContra + RESET);
        if (golesAFavor > golesEnContra) {
            System.out.println(VERDE + BOLD + "¡Victoria absoluta! ¡Campeones de la Champions League! ✅" + RESET);
            inventarioLogros[3] = "Trofeo Champions League 🏆";
            dinero += 1000;
            puntosCarrera += 500;
            return true;
        } else if (golesAFavor == golesEnContra) {
            System.out.println(AMARILLO + "¡Empate vibrante en la gran final! Toca jugárselo todo en la tanda de penaltis. ⚔️" + RESET);
            return gestionarPenaltisFinal();
        } else {
            System.out.println(ROJO + "Derrota cruel en los últimos compases de la final. ❌" + RESET);
            return false;
        }
    }
}