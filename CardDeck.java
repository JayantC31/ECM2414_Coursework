/*
 * CardDeck Class - Class for the card decks used in the game
 * @author Jayant Chawla and Pui Kin Chan
 * */


import java.util.ArrayList;

public class CardDeck extends Thread {

    private final Integer cardDeckID;
    private ArrayList<Card> cardsInDeck;

    public CardDeck(Integer cardDeckID, ArrayList<Card> cardsInDeck) {
        this.cardDeckID = cardDeckID;
        this.cardsInDeck = cardsInDeck;
    }

    public Integer returnCardDeckID() {
        return cardDeckID;
    }

    public ArrayList<Card> returnCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(ArrayList<Card> newCards) {
        this.cardsInDeck = newCards;
    }

    public void addCardToDeck(Card card) {
        this.cardsInDeck.add(card);
    }




}
