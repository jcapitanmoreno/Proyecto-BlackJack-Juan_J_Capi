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
                    System.out.println("Gracias por jugar. ¡¡vuelve pronto!!");
                    break;
            }
        } while (option != 3);

    }

    /**
     * Inicia una partida entre un jugador humano y la inteligencia artificial (IA).
     * El menu indica si quieres seguir queriendo carta o se planta (la iA toma sus propias decisiones)
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
            try {
                Thread.sleep(2000);
                Card card = deck.randomCard();
                view.cardInterface(card);
                game.calculatePoints(humanPlayer, card);
            } catch (InterruptedException e) {
            }

            try {
                Thread.sleep(2000);
                Card card2 = deck.randomCard();
                view.cardInterface(card2);
                game.calculatePoints(aiPlayer, card2);
            } catch (InterruptedException e) {
            }


        }
        // Juega el jugador humano primero
        do {
            System.out.println("-----------------------------");
            System.out.println("1 Seguir, 2 Plantarse");
            System.out.println("-----------------------------");
            option = utils.scOptions(1, 2);

            if (option == 1) {
                System.out.println("-----------------------------");
                System.out.println("Vas a sacar carta");
                System.out.println("-----------------------------");
                Card card = deck.randomCard();
                view.cardInterface(card);
                game.calculatePoints(humanPlayer, card);

                if (humanPlayer.getScore() > 21) {
                    System.out.println("-----------------------------");
                    System.out.println("Has perdido");
                    System.out.println("-----------------------------");

                }
            } else {
                System.out.println("-----------------------------");
                System.out.println("Te has plantado");
                System.out.println("-----------------------------");
            }

        } while (option == 1 && humanPlayer.getScore() <= 21);

        // Juega la IA

        do {
            repeat = decideToPlay(aiPlayer);


            if (repeat) {
                System.out.println("-----------------------------");
                System.out.println("La IA va a sacar carta");
                System.out.println("-----------------------------");
                Card card = deck.randomCard();
                view.cardInterface(card);
                game.calculatePoints(aiPlayer, card);

                if (aiPlayer.getScore() > 21) {
                    System.out.println("-----------------------------");
                    System.out.println("La IA ha perdido");
                    System.out.println("-----------------------------");
                }
            } else {
                System.out.println("-----------------------------");
                System.out.println(aiPlayer.getName() + " se ha plantado");
                System.out.println("-----------------------------");
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

    /**
     * Método principal para ejecutar una partida del juego. Inicializa el mazo, agrega jugadores,
     * reparte cartas iniciales, permite a cada jugador tomar decisiones turno por turno
     * (pedir carta o plantarse), muestra los resultados y determina al ganador o si hay un empate al final.
     */
    public void playWithPlayers() {

        int option;
        Deck deck = new Deck();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> outPlayers = new ArrayList<>();

        // Agregar jugadores (hasta 4 jugadores)
        players = game.addPlayer();

        deck.crearDeck();

        // Repartir cartas iniciales
        try {
            for (Player player : players) {
                for (int i = 0; i < 2; i++) {


                    Thread.sleep(2000);

                    System.out.println("-----------------------------");
                    System.out.println(player.getName() + " vas a sacar carta");
                    Card card = deck.randomCard();
                    view.cardInterface(card);
                    game.calculatePoints(player, card);
                    System.out.println("-----------------------------");
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
                        System.out.println("-----------------------------");
                        System.out.println(player.getName() + " vas a sacar carta");
                        System.out.println("-----------------------------");
                        Card card = deck.randomCard();
                        view.cardInterface(card);
                        game.calculatePoints(player, card);

                        if (player.getScore() > 21) {
                            System.out.println("-----------------------------");
                            System.out.println(player.getName() + " ha perdido");
                            System.out.println("-----------------------------");
                            outPlayers.add(player);
                        }
                        break;

                    case 2:
                        System.out.println("-----------------------------");
                        System.out.println(player.getName() + " se ha plantado");
                        System.out.println("-----------------------------");
                        break;
                }

            } while (option == 1 && player.getScore() <= 21);
        }

        // Muestra resultados después de que todos los jugadores hayan jugado
        for (Player player : players) {
            System.out.println("-----------------------------");
            System.out.println(player.getName() + ", tu puntuación es: " + player.getScore());
            System.out.println("-----------------------------");
        }
        // Limpia la lista de jugadores que han perdido
        players.removeAll(outPlayers);

        Player ganador = game.determineWinner(players);

        // Muestra el ganador o indica si hay un empate
        if (ganador != null) {
            System.out.println("-----------------------------");
            System.out.println("El ganador es: " + ganador.getName() + " con " + ganador.getScore() + " puntos.");
            System.out.println("-----------------------------");
        } else {
            System.out.println("-----------------------------");
            System.out.println("¡Es un empate!");
            System.out.println("-----------------------------");
        }
    }

    /**
     * Función que sirve para decidir si la IA sigue jugando o se planta
     *
     * @param p recibe el objeto "Player" como "P"
     * @return devuelve el score que queremos para que la IA deje de jugar o siga jugando
     */
    public boolean decideToPlay(Player p) {
        return p.getScore() <= 16;
    }

}
