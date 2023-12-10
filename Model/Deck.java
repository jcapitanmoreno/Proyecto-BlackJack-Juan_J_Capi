package Model;

import com.sun.jdi.Value;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Arrays;
import java.util.List;

public class Deck {

    private List<Card> cards;


    public void crearDeck() {
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


    public void shuffleDeck() {
        Collections.shuffle(cards);
        /*for (Card cartas: cards) {       para comprobar si funciona el shuffle
            System.out.println(cartas);
        }*/
    }

    public Card randomCard() {
        Card card = cards.remove(0);
        System.out.println("tu carta es: " + card.Value + " de " + card.Suit);

        return card;

    }
/*
    @Override
    public String toString() {
        return "Deck{" + "cards=" + Arrays.toString(cards) + '}';
    }*/

}
