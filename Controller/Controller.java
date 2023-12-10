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

    public void start() { //Aqui se muestra el menú del juego que vamos a seleccionar.
        int option = 0;
        view.menuPrincipal();


        option = utils.scOptions(1, 3);


        switch (option) {
            case 1:
                playWhithIA();

                break;

            case 2:
                play();

                break;

            case 3:
                System.out.println("bye bye");
                break;
        }

    }

    public void playWhithIA() {
        boolean repeat;
        Scanner teclado = new Scanner(System.in);
        int option = 0;
        Deck deck = new Deck();
        Player humanPlayer = game.addPlayerAndIA(); //jugador humano
        Player aiPlayer = new Player("IA", 0, true); // Crear jugador IA

        deck.crearDeck();
        for (int i = 0; i < 2; i++) {
            Card card = deck.randomCard();
            game.calculatePoints(humanPlayer, card);
            Card card2 = deck.randomCard();
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


    public void play() {
        int option = 0;
        Deck deck = new Deck();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> outPlayers = new ArrayList<>();
        players = game.addPlayer();
        System.out.println(players);
        deck.crearDeck();
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                if (player.isIA()) {
                    System.out.println("-----------------------------");
                    System.out.println("La IA " + player.getName() + " va a sacar carta");
                } else {
                    System.out.println("-----------------------------");
                    System.out.println(player.getName() + " vas a sacar carta");
                }

                Card card = deck.randomCard();
                game.calculatePoints(player, card);
            }
        }
        // menu de opcion de coger carta o plantarse
        do {
            System.out.println(" 1 seguir, 2 salir ");
            option = utils.scOptions(1, 2);

            switch (option) {
                case 1:

                    for (Player player : players) {
                        if (player.isIA()) {
                            if (!decideToPlay(player)) {
                                System.out.println(player.getName() + " se ha plantado");
                                outPlayers.add(player);
                            } else {
                                System.out.println("-----------------------------");
                                System.out.println("la IA " + player.getName() + " va a sacar carta");
                                Card card = deck.randomCard();
                                game.calculatePoints(player, card);
                                if (player.getScore() > 21) {
                                    System.out.println(player.getName() + " ha perdido");
                                    outPlayers.add(player);
                                }
                            }
                        } else if (!player.isIA()) {
                            System.out.println("-----------------------------");
                            System.out.println(player.getName() + " vas a sacar carta");
                            Card card = deck.randomCard();
                            game.calculatePoints(player, card);

                            if (player.getScore() > 21) {
                                System.out.println(player.getName() + " ha perdido");
                                outPlayers.add(player);
                            }
                        }
                    }
                    players.removeAll(outPlayers);
                    //System.out.println(players); comentario de comprobación
                    //System.out.println(lostPlayers); comentario de comprobación
                    break;

                case 2:
                    Player p = players.getFirst();
                    System.out.println("tu puntuacion es: " + p.getScore());
                    break;
            }
        } while (option != 2);
    }

    public boolean decideToPlay(Player p) {
        return p.getScore() <= 16;
    }
}
