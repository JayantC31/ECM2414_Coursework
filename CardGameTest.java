import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class CardGameTest {
    // create an input stream when testing
    private final InputStream originalSystemIn = System.in;
    private Game game;
    // start the game
    @BeforeEach
    void setUp() {
        game = new Game();
    }

    //make sure the players are tested for by checking ID
    @Test
    public void PlayerTest() {
        var game = new Game();
        game.setNumberOfPlayers(4);
        //game.createPlayers(); // Create players
        assertEquals(4, game.getNumberOfPlayers());
    }

    @AfterEach
    public void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }



    @Test
    public void testDeckSize() {
        // create a new deck and add cards given its ID
        var cardDeck = new CardDeck(4, new ArrayList<>());

        // Add some cards to the deck
        cardDeck.addCardToDeck(new Card(1)); // add integer values to the cards
        cardDeck.addCardToDeck(new Card(2));
        cardDeck.addCardToDeck(new Card(3));
        cardDeck.addCardToDeck(new Card(4));
        cardDeck.addCardToDeck(new Card(5));
        cardDeck.addCardToDeck(new Card(6));
        cardDeck.addCardToDeck(new Card(7));
        cardDeck.addCardToDeck(new Card(8));

        // check that deck size is as expected
        assertEquals(8, cardDeck.returnCardsInDeck().size());
    }


   @Test
   public void PackLocationTest(){
        var gameClass = new Game();
        gameClass.setPackLocation("test.txt");
        // check if location has been found and is correct
        assertEquals("test.txt", gameClass.getPackLocation());
    }



    @Test
    public void testDeckID() {
        // create a new CardDeck using an ID and an empty list of cards
        var cardDeck = new CardDeck(4, new ArrayList<>());

        // check that ID is same as created
        assertEquals(4, cardDeck.returnCardDeckID());
    }
    @Test
    public void testDeckRunnable() {
        // make an instance of deckRunnable
        deckRunnable deckRunnableTest = new deckRunnable();

        // run the deckRunnable with a new thread
        Thread thread = new Thread(deckRunnableTest);
        thread.start();

        // let the thread run for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // check if thread is alive
        assertFalse(thread.isAlive());
    }
    @Test
    public void testPlayerRunnable() {
        // create an instance of player runnable
        playerRunnable playerRunnableTest = new playerRunnable();

        // run the runnable in a new thread
        Thread thread = new Thread(playerRunnableTest);
        thread.start();

        // let the thread run for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // check if thread is alive
        assertFalse(thread.isAlive());
    }
    @Test
    public void testCardRunnable() {
        // create an instance of the card runnable
        cardRunnable CardRunnableTest = new cardRunnable();

        // run the card runnable in new thread
        Thread thread = new Thread(CardRunnableTest);
        thread.start();

        // let the thread run for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // check if thread is alive
        assertFalse(thread.isAlive());
    }
}