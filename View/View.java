package View;

import Model.Card;

public class View {

    public void menuPrincipal() {
        System.out.println("------------------------------------------");
        System.out.println("|             Juego BlackJack            |");
        System.out.println("| Bienvenido:                            |");
        System.out.println("------------------------------------------");
        System.out.println("| 1- Jugar con IA                        |");
        System.out.println("| 2- Jugar con otros jugadores           |");
        System.out.println("| 3- Salir                               |");
        System.out.println("------------------------------------------");
    }

    public void menuJugadores() {
        System.out.println("------------------------------------------");
        System.out.println("|                                        |");
        System.out.println("| Selecciona la cantidad  de jugadores   |");
        System.out.println("|                                        |");
        System.out.println("------------------------------------------");
        System.out.println("| 2 jugadores                            |");
        System.out.println("| 3 jugadores                            |");
        System.out.println("| 4 jugadores                            |");
        System.out.println("------------------------------------------");
    }

    public void nombreJugadores() {
        System.out.println("------------------------------------------");
        System.out.println("|                                        |");
        System.out.println("|   Indica el nombre de los jugadores:   |");
        System.out.println("|                                        |");
        System.out.println("------------------------------------------");
    }

    public void nombreJugador() {
        System.out.println("------------------------------------------");
        System.out.println("|                                        |");
        System.out.println("|   Indica el nombre del jugador:        |");
        System.out.println("|                                        |");
        System.out.println("------------------------------------------");
    }

    public static void cardInterface(Card card) {

        int value = card.getValue();
        String suit = card.getSuit();

        if (suit == "Diamantes") {
            suit = "♦";
        } else if (suit == "Treboles") {
            suit = "♣";
        } else if (suit == "Picas") {
            suit = "♠";
        } else if (suit == "Corazones") {
            suit = "♥";
        }

        String cardView = "";
        switch (value) {
            case 1:
                cardView += "A";
                break;
            case 11:
                cardView += "J";
                break;
            case 12:
                cardView += "Q";
                break;
            case 13:
                cardView += "K";
                break;
            default:
                cardView += value;
                break;
        }
        System.out.println("┌───────┐");
        System.out.println("| " + cardView + "     |");
        System.out.println("|       |");
        System.out.println("|   " + suit + "   |");
        System.out.println("|       |");
        System.out.println("|     " + cardView + " |");
        System.out.println("└───────┘");
    }

    public void gameRules() {
        try {
            Thread.sleep(2000);
            System.out.println("");
            System.out.println("REGLAS DEL JUEGO: el objetivo es obtener una mano con un valor cercano a 21 sin pasarse. ");
            System.out.println("Se juega contra la inteligencia artificial o contra jugadores reales. Las cartas numéricas");
            System.out.println("valen su valor, las figuras valen 10, y el as puede valer 1 u 11 según convenga al jugador.");
            System.out.println("");

        } catch (InterruptedException e) {
        }
    }

    public void blackJackView() {
        System.out.println(" /$$$$$$$ /$$       /$$$$$$   /$$$$$$ /$$   /$$    /$$$$$  /$$$$$$   /$$$$$$ /$$   /$$");
        System.out.println("| $$__  $| $$      /$$__  $$ /$$__  $| $$  /$$/   |__  $$ /$$__  $$ /$$__  $| $$  /$$/");
        System.out.println("| $$  \\ $| $$     | $$  \\ $$| $$  \\__| $$ /$$/       | $$| $$  \\ $$| $$  \\__| $$ /$$/");
        System.out.println("| $$$$$$$| $$     | $$$$$$$$| $$     | $$$$$/        | $$| $$$$$$$$| $$     | $$$$$/");
        System.out.println("| $$__  $| $$     | $$__  $$| $$     | $$  $$   /$$  | $$| $$__  $$| $$     | $$  $$");
        System.out.println("| $$  \\ $| $$     | $$  | $$| $$    $| $$\\  $$ | $$  | $$| $$  | $$| $$    $| $$\\  $$");
        System.out.println("| $$$$$$$| $$$$$$$| $$  | $$|  $$$$$$| $$ \\  $$|  $$$$$$/| $$  | $$|  $$$$$$| $$ \\  $$");
        System.out.println("|_______/|________|__/  |__/ \\______/|__/  \\__/ \\______/ |__/  |__/\\______/ |__/  \\__/");
    }
}
