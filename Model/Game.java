package Model;

import View.View;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {


    /**
     * Método para crear y configurar un nuevo jugador, solicitando el nombre al usuario.
     * Se utiliza una vista (view) para mostrar mensajes relacionados con la introducción del personaje.
     *
     * Función implementada en playWithIA
     *
     * @return Un objeto Player configurado con el nombre proporcionado por el usuario.
     */
    public static Player addPlayerAndIA() {
        Player p = new Player();
        String name = null;
        View view = new View();
        Scanner teclado = new Scanner(System.in);

        //añadir texto indicando la introducción de personaje (view.nombreJugador)
        view.nombreJugador();
        do {
            name = teclado.next();
        } while (name == " " || name == null || name == "IA" || name == "ia");
        p.setName(name);

        return p;
    }


    /**
     * Método para agregar jugadores al juego, solicitando el número de jugadores y sus nombres al usuario.
     * Utiliza una vista para mostrar mensajes relacionados con el menú de jugadores y la introducción de personajes.
     *
     * Función implementada en playWithPlayers
     *
     * @return Un ArrayList de objetos Player, cada uno configurado con un nombre único proporcionado por el usuario.
     */
    public static ArrayList<Player> addPlayer() {
        int nJugadores = 0;
        String name = null;
        View view = new View();

        view.menuJugadores();   // escribe el número de jugadores (vista)
        Scanner teclado = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();


        do { //poner texto indicando hasta cuantos jugadores se puede (entre 2-4)
            nJugadores = teclado.nextInt();

        } while (nJugadores <= 1 || nJugadores >= 5);
        //añadir texto indicando la introducción de personaje (vista)
        view.nombreJugadores();
        for (int i = 1; i <= nJugadores; i++) {
            boolean nombreUnico;
            do {
                nombreUnico = true;
                do {
                    name = teclado.next();
                } while (name == " " || name == null || name == "IA" || name == "ia");
                for (Player player : players) {
                    if (player.getName().equalsIgnoreCase(name)) {
                        nombreUnico = false;
                        System.out.println("El nombre ya está en uso. Por favor, introduce un nombre único.");
                    }
                }
            } while (!nombreUnico);

            if (i == 1) {
                players.add(new Player(name, 0));
            } else if (i != 1) {
                players.add(new Player(name, 0));
            }


        }
        return players;
    }

    /**
     * Calcula la puntuación del jugador después de recibir una carta y calcula cuanto vale el AS
     * segun el valor "score" del Player.
     *
     * @param player Jugador al que se le asigna la puntuación.
     * @param card   Carta que se ha repartido al jugador.
     */
    public static void calculatePoints(Player player, Card card) {
        int value = card.getValue();
        int actualScore = player.getScore();

        if (value == 11 || value == 12 || value == 13) {
            value = 10;
        } else if (value == 1 && actualScore < 11) {
            value = 11;
        }
        player.setScore(actualScore + value);
        System.out.println("la puntuación del jugador " + player.getName() + " es: " + player.getScore());


    }

    /**
     * Función que determina quien ha ganado la partida en playWhithPlayer
     * @param players lista de jugadores que recorre
     * @return devuelve el valor ganador que se igualó a player
     */
    public Player determineWinner(ArrayList<Player> players) {
        Player ganador = null;
        int puntuacionGanadora = 0;

        // Lista para almacenar a los jugadores con la puntuación ganadora
        ArrayList<Player> empate = new ArrayList<>();

        for (Player player : players) {
            int puntuacion = player.getScore();

            // Verificar si el jugador ha superado los 21 puntos
            if (puntuacion <= 21) {
                // Si la puntuación actual es mayor que la puntuación ganadora actual
                if (puntuacion > puntuacionGanadora) {
                    puntuacionGanadora = puntuacion;
                    ganador = player;
                    // Limpiar la lista de empate ya que tenemos un nuevo líder
                    empate.clear();
                } else if (puntuacion == puntuacionGanadora) {
                    // Si hay empate, agregar al jugador a la lista de empate
                    empate.add(player);
                }
            }
        }

        // Si hay empate, devuelve null(size() se utiliza comúnmente para determinar el número de elementos en una colección o la longitud de un array)
        if (empate.size() > 0) {
            ganador = null;
        }

        return ganador;
    }
}
