package Controller;

import Model.Card;
import Model.Player;
import Utils.Utils;
import View.View;

import Model.Deck;
import Model.Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    Utils utils = new Utils();
    Card card = new Card();
    Game game = new Game();
    View view = new View();


    /**
     * Inicia la ejecución del juego. Muestra el menú principal y permite al jugador seleccionar una opción.
     * Las opciones incluyen jugar contra la inteligencia artificial, jugar con otros jugadores o salir del juego.
     * Después de seleccionar una opción, la función ejecuta la acción correspondiente y muestra mensajes de espera.
     * El bucle continuará hasta que el usuario elija salir del juego.
     */
    public void start() { //Aquí se muestra el menú del juego que vamos a seleccionar.
        int option = 0;


        do {
            view.menuPrincipal();
            option = utils.scOptions(1, 3);

            switch (option) {
                case 1:
                    playWhithIA();
                    try {
                        Thread.sleep(2000);
                        System.out.println("");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 2:
                    playWithPlayers();
                    try {
                        Thread.sleep(2000);
                        System.out.println("");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 3:
                    System.out.println("bye bye");
                    break;
            }
        }while (option!=3);

    }

    /**
     * Inicia una partida entre un jugador humano y la inteligencia artificial (IA).
     * El menu indica si quieres seguir queriendo carta o se planta (la iA toma sus propias decisiones)
     *
     */
    public void playWhithIA() {
        boolean repeat;
        Scanner teclado = new Scanner(System.in);
        int option = 0;
        Deck deck = new Deck();
        Player humanPlayer = game.addPlayerAndIA(); //jugador humano
        Player aiPlayer = new Player("IA", 0); // Crear jugador IA

        deck.crearDeck();
        for (int i = 0; i < 2; i++) {
            Card card = deck.randomCard();
            view.cardInterface(card);
            game.calculatePoints(humanPlayer, card);
            Card card2 = deck.randomCard();
            view.cardInterface(card2);
            game.calculatePoints(aiPlayer, card2);
        }
        // Juega el jugador humano primero
        do {
            System.out.println("1 seguir, 2 salir");
            option = utils.scOptions(1, 2);

            if (option == 1) {
                System.out.println("-----------------------------");
                System.out.println("Vas a sacar carta");
                Card card = deck.randomCard();
                view.cardInterface(card);
                game.calculatePoints(humanPlayer, card);

                if (humanPlayer.getScore() > 21) {
                    System.out.println("Has perdido");

                }
            } else {
                System.out.println("te has plantado");
            }

        } while (option == 1 && humanPlayer.getScore() <= 21);

        // Juega la IA

        do {
            repeat = decideToPlay(aiPlayer);
            //System.out.println(repeat);

            if (repeat) {
                System.out.println("-----------------------------");
                System.out.println("La IA va a sacar carta");
                Card card = deck.randomCard();
                view.cardInterface(card);
                game.calculatePoints(aiPlayer, card);

                if (aiPlayer.getScore() > 21) {
                    System.out.println("La IA ha perdido");
                }
            } else {
                System.out.println(aiPlayer.getName() + " se ha plantado");
            }
        } while (repeat && aiPlayer.getScore() <= 21);


        System.out.println("Tu puntuación es: " + humanPlayer.getScore());
        System.out.println("La IA puntuación es: " + aiPlayer.getScore());

        // Determinar al ganador
        if (humanPlayer.getScore() > 21 && aiPlayer.getScore() > 21) {
            System.out.println("Ambos jugadores han perdido. ¡Es un empate!");
        } else if (humanPlayer.getScore() <= 21 && (aiPlayer.getScore() > 21 || humanPlayer.getScore() > aiPlayer.getScore())) {
            System.out.println("¡Felicidades! Has ganado. La IA ha perdido.");
        } else if (aiPlayer.getScore() <= 21 && (humanPlayer.getScore() > 21 || humanPlayer.getScore() < aiPlayer.getScore())) {
            System.out.println("La IA gana. ¡Has perdido!");
        } else {
            System.out.println("¡¡EMPATE!!");
        }
    }


    public void playWithPlayers() {

        int option;
        Deck deck = new Deck();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> outPlayers = new ArrayList<>();

        // Agregar jugadores (hasta 4 jugadores)
        players = game.addPlayer();

        deck.crearDeck();

        // Repartir cartas iniciales
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                System.out.println("-----------------------------");
                System.out.println(player.getName() + " vas a sacar carta");
                Card card = deck.randomCard();
                view.cardInterface(card);
                game.calculatePoints(player, card);
                System.out.println("-----------------------------");
            }
        }

        // Jugar turno por turno
        for (Player player : players) {
            do {
                System.out.println("-----------------------------");
                System.out.println(player.getName() + ", es tu turno:");
                System.out.println("1. Pedir carta");
                System.out.println("2. Plantarse");
                System.out.println("-----------------------------");

                option = utils.scOptions(1, 2);

                switch (option) {
                    case 1:
                        System.out.println(player.getName() + " vas a sacar carta");
                        Card card = deck.randomCard();
                        view.cardInterface(card);
                        game.calculatePoints(player, card);

                        if (player.getScore() > 21) {
                            System.out.println(player.getName() + " ha perdido");
                            outPlayers.add(player);
                        }
                        break;

                    case 2:
                        System.out.println(player.getName() + " se ha plantado");
                        break;
                }

            } while (option == 1 && player.getScore() <= 21);
        }

        // Mostrar resultados después de que todos los jugadores hayan jugado
        for (Player player : players) {
            System.out.println(player.getName() + ", tu puntuación es: " + player.getScore());
        }
        // Limpia la lista de jugadores perdidos
        players.removeAll(outPlayers);

        Player ganador = game.determineWinner(players);

        // Mostrar el ganador o indicar si hay un empate
        if (ganador != null) {
            System.out.println("El ganador es: " + ganador.getName() + " con " + ganador.getScore() + " puntos.");
        } else {
            System.out.println("¡Es un empate!");
        }
    }

    public boolean decideToPlay(Player p) {
        return p.getScore() <= 16;
    }

}
