/**
 * CardDeck Class - Class for the card decks used in the game
 * @author Jayant Chawla and Pui Kin Chan
 * */


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CardDeck extends Thread {
    // the variables used for the deck and the cards in the deck
    private final Integer cardDeckID;
    private ArrayList<Card> cardsInDeck;
    // create the decks, using ID and the cards each deck contains
    public CardDeck(Integer cardDeckID, ArrayList<Card> cardsInDeck) {
        this.cardDeckID = cardDeckID;
        this.cardsInDeck = cardsInDeck;
    }
    // used to return ID of deck
    public Integer returnCardDeckID() {
        return cardDeckID;
    }
    // used to return the cards inside the  deck
    public synchronized ArrayList<Card> returnCardsInDeck() {
        return cardsInDeck;
    }
    // used to set the cards in deck
    public void setCardsInDeck(ArrayList<Card> newCards) {
        this.cardsInDeck = newCards;
    }
    // used to add cards to deck
    public synchronized void addCardToDeck(Card card) {
        this.cardsInDeck.add(card);
    }

    // used to draw a card from a player
    public synchronized Card drawCard() {
        if (!cardsInDeck.isEmpty()) {
            return cardsInDeck.remove(0); // return the card on top
        }
        return null; // return null as exception if deck is empty
    }

    //writes the decks hand to an output file
    public void writeDeckHand(){
        try {
            FileWriter myWriter = new FileWriter("deck" + cardDeckID + "_output.txt");
            // use FileWrite to make the game
            myWriter.write("deck" + cardDeckID + " contents: " + cardsInDeck + "\n");
            myWriter.close();
        } catch (IOException e) {
            // check for any exception when creating file
            System.out.println("Error when creating deck file");
            e.printStackTrace();
        }
    }




}
