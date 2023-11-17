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

    public ArrayList<Card> returnCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(ArrayList<Card> newCards) {
        this.cardsInDeck = newCards;
    }

    public void addCardToDeck(Card card) {
        this.cardsInDeck.add(card);
    }


    public synchronized Card drawCard() {
        if (!cardsInDeck.isEmpty()) {
            return cardsInDeck.remove(cardsInDeck.size() - 1); // Remove and return the top card
        }
        return null; // Or throw an exception if the deck is empty
    }

    public void run() {
        try (FileWriter fileWriter = new FileWriter("deck" + cardDeckID + "_output.txt")) {
            // Loop to continue the deck's actions until the game is won
            fileWriter.write("Deck " + cardDeckID + " initial deck " + cardsInDeck + "\n");
            while (!GameClass.gameWon) {
                fileWriter.write("Deck " + cardDeckID + " deck " + cardsInDeck + "\n");


                }
            } catch (IOException exception) {
                System.out.println("PROBLEM for deck");
            }
    }


}
