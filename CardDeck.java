/*
 * CardDeck Class - Class for the card decks used in the game
 * @author Jayant Chawla and Pui Kin Chan
 * */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;


public class CardDeck extends Thread {

    private Queue<Card> cards = new LinkedList<>();

    public synchronized void addCard(Card card) {
        cards.add(card);
    }

    public synchronized Card drawCard() {
        return cards.poll();
    }

    // Method to help with debugging
    public synchronized List<Card> getCards() {
        List<Card> cardList = new ArrayList<>();
        for (Card card : cards) {
            cardList.add(card);
        }
        return cardList;
    }

}
