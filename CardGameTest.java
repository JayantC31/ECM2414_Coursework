import org.junit.jupiter.api.*;
import java.io.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {
    // create an input stream when testing
    private final InputStream originalSystemIn = System.in;
    private GameClass gameClass;
    // start the game
    @BeforeEach
    void setUp() {
        gameClass = new GameClass();
    }

    //make sure the players are tested for by checking ID
    @Test
    void PlayerTest() {
        var game = new GameClass();
        game.setNumberOfPlayers(4);
        //game.createPlayers(); // Create players
        assertEquals(4, game.getNumberOfPlayers());
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void UserInputsANDPackLocationTest() {
        String simulatedInput = "4\n" + // input for number of players
                "test.txt\n"; // input for the pack location
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        gameClass.UserInputs();

        assertEquals("test.txt", gameClass.getPackLocation());
    }

    @Test
    void testDeckSize() {
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
    void PackLocationTest(){
        var gameClass = new GameClass();
        gameClass.setPackLocation("test.txt");
        // check if location has been found and is correct
        assertEquals("test.txt", gameClass.getPackLocation());
    }



    @Test
    void testDeckID() {
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