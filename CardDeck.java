/*
 * CardDeck Class - Class for the card decks used in the game
 * @author Jayant Chawla and Pui Kin Chan
 * */


import java.io.FileWriter;
import java.io.IOException;
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

    public synchronized ArrayList<Card> returnCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(ArrayList<Card> newCards) {
        this.cardsInDeck = newCards;
    }

    public synchronized void addCardToDeck(Card card) {
        this.cardsInDeck.add(card);
    }


    public synchronized Card drawCard() {
        if (!cardsInDeck.isEmpty()) {
            return cardsInDeck.remove(0); // Remove and return the top card
        }
        return null; // Or throw an exception if the deck is empty
    }

    //writes the decks hand to an output file
    public void writeDeckHand(){
        try {
            FileWriter myWriter = new FileWriter("deck" + cardDeckID + "_output.txt");
            myWriter.write("deck" + cardDeckID + " contents: " + cardsInDeck + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error when creating deck file");
            e.printStackTrace();
        }
    }




}
