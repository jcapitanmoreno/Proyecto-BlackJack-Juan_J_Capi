package View;

import Model.Card;

public class View {

    public void menuPrincipal(){
        System.out.println("------------------------------------------");
        System.out.println("|             Juego BlackJack            |");
        System.out.println("| Bienvenido:                            |");
        System.out.println("------------------------------------------");
        System.out.println("| 1- Jugar con IA                        |");
        System.out.println("| 2- Jugar con otros jugadores           |");
        System.out.println("| 3- Salir                               |");
        System.out.println("------------------------------------------");
    }
    public void menuJugadores(){
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
    public void nombreJugadores(){
        System.out.println("------------------------------------------");
        System.out.println("|                                        |");
        System.out.println("|   Indica el nombre de los jugadores:   |");
        System.out.println("|                                        |");
        System.out.println("------------------------------------------");
    }
    public void nombreJugador(){
        System.out.println("------------------------------------------");
        System.out.println("|                                        |");
        System.out.println("|   Indica el nombre del jugador:        |");
        System.out.println("|                                        |");
        System.out.println("------------------------------------------");
    }
public static void cardInterface(Card card){

        int value = card.getValue();
        String suit = card.getSuit();

        if (suit == "Diamantes"){
            suit="♦";
        } else if (suit == "Treboles"){
            suit="♣";
        } else if (suit == "Picas"){
            suit="♠";
        } else if (suit == "Corazones"){
            suit="♥";
        }

        String cardVisual = "";
        switch (value) {
            case 1:
                cardVisual += "A";
                break;
            case 11:
                cardVisual += "J";
                break;
            case 12:
                cardVisual += "Q";
                break;
            case 13:
                cardVisual += "K";
                break;
            default:
                cardVisual += value;
                break;
        }
        System.out.println("┌───────┐");
        System.out.println("| " + cardVisual + "     |");
        System.out.println("|       |");
        System.out.println("|   " + suit + "   |");
        System.out.println("|       |");
        System.out.println("|     " + cardVisual + " |");
        System.out.println("└───────┘");
    }
}
