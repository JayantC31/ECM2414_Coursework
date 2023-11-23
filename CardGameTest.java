import org.junit.jupiter.api.*;


import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {

    /*@Test
    void PlayerTest() {

        var game = new GameClass();
        game.setNumberOfPlayers(4);
        //game.createPlayers(); // Create players

        assertEquals(4, game.getNumberOfPlayers());
    }
`
     */

    private final InputStream originalSystemIn = System.in;
    private GameClass gameClass;

    @BeforeEach
    void setUp() {
        gameClass = new GameClass();
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void UserInputsANDPackLocationTest() {
        String simulatedInput = "4\n" + // Simulated input for number of players
                "test.txt\n"; // Simulated input for pack location
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        gameClass.UserInputs();

        assertEquals("test.txt", gameClass.getPackLocation());
    }

    @Test
    void testDeckSize() {
        // Create a new CardDeck with an ID and an empty list of cards
        var cardDeck = new CardDeck(4, new ArrayList<>());

        // Add some cards to the deck
        cardDeck.addCardToDeck(new Card(1)); // Assuming Card constructor takes an integer
        cardDeck.addCardToDeck(new Card(2));
        cardDeck.addCardToDeck(new Card(3));
        cardDeck.addCardToDeck(new Card(4));
        cardDeck.addCardToDeck(new Card(5));
        cardDeck.addCardToDeck(new Card(6));
        cardDeck.addCardToDeck(new Card(7));
        cardDeck.addCardToDeck(new Card(8));

        // Assert that the size of the deck is as expected
        assertEquals(8, cardDeck.returnCardsInDeck().size());
    }

    /*
    @Test
    void testCorrectLocationInput() {
        // Simulate user input
        String simulatedInput = "test.txt\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        // Call the method under test
        gameClass.UserInputs();

        // Assert that the location is set correctly
        assertEquals("test.txt", gameClass.getPackLocation());
    }

     */
   /* @Test
    void PackLocationTest(){
        var gameClass = new GameClass();
        gameClass.setPackLocation("test.txt");

        assertEquals("test.txt", gameClass.getPackLocation());
    }


    */


    @Test
    void testDeckID() {
        // Create a new CardDeck with an ID and an empty list of cards
        var cardDeck = new CardDeck(4, new ArrayList<>());

        // Assert that the deck ID is as expected
        assertEquals(4, cardDeck.returnCardDeckID());
    }
    @Test
    public void testDeckRunnable() {
        // Create an instance of deckRunnable
        deckRunnable deckRunnable = new deckRunnable();

        // Run the deckRunnable in a new thread
        Thread thread = new Thread(deckRunnable);
        thread.start();

        // Let the thread run for some time (adjust this based on the expected sleep time)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Optionally, you can check some observable effects or states influenced by the run method
        // For example, you might have a boolean flag that is set during the run method
        // assertTrue(deckRunnable.isSomeFlagSet());

        // Ensure that the thread has finished its execution
        assertFalse(thread.isAlive());
    }


}