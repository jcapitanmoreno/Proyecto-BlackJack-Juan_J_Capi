package Model;

import com.sun.jdi.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

public class Deck {

    private List<Card> cards;

    /**
     * Función que crea una baraja de cartas
     */
    public void crearDeck() {
        //Representa una lista dinámica, lo que significa que puedes agregar, eliminar y acceder a elementos de la lista de manera flexible.
        cards = new ArrayList<>();
        String[] suits = {"Corazones", "Picas", "Treboles", "Diamantes"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        for (String suit : suits) {
            for (int value : values) {
                cards.add(new Card(suit,value));

            }
        }
        shuffleDeck();
    }

    /**
     * Función que randomiza la baraja de cartas
     */
    public void shuffleDeck() {
        //colección de java que randomiza la baraja de cartas
        Collections.shuffle(cards);

    }

    /**
     * Función que devuelve una carta randomizada y la elimina para que no vuelva a aparecer
     * @return devuelve la carta randomizada
     */
    public Card randomCard() {
        Card card = cards.remove(0);
        System.out.println("--------------------------");
        System.out.println("tu carta es: " + card.Value + " de " + card.Suit);
        System.out.println("--------------------------");

        return card;

    }
}
