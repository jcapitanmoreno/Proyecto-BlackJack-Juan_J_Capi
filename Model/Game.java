package Model;

import View.View;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static Player addPlayerAndIA() {
        Player p = new Player();
        String name = null;
        View view = new View();
        Scanner teclado = new Scanner(System.in);

        //añadir texto indicando la introducción de personaje (vista)
        view.nombreJugador();
        do {
            name = teclado.next();
        } while (name == " " || name == null || name == "IA" || name == "ia");
        p.setName(name);

        return p;
    }

    public static ArrayList<Player> addPlayer() {
        int nJugadores = 0;
        String name = null;
        View view = new View();

        view.menuJugadores();   // escribe el número de jugadores (vista)
        Scanner teclado = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();

        //añadir texto indicando la introducción de personaje (vista)
        do { //poner texto indicando hasta cuantos jugadores se puede (entre 2-4)
            nJugadores = teclado.nextInt();

        } while (nJugadores <= 1 || nJugadores >= 5);
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
                players.add(new Player(name, 0, false));
            } else if (i != 1) {
                players.add(new Player(name, 0, true));
            }


        }
        return players;
    }

    public static void calculatePoints(Player player, Card card) {
        int value = card.getValue();
        int actualScore = player.getScore();

        if (value == 11 || value == 12 || value == 13) {
            value = 10;
        } else if (value == 1 && actualScore < 11) {
            value = 11;
        }
        player.setScore(actualScore + value);
        System.out.println("la puntuacion del jugador " + player.getName() + " es: " + player.getScore());


    }
}
