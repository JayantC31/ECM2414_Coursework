import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameClassTest {

    private final InputStream originalSystemIn = System.in;
    private GameClass gameClass;
    private Player player;
    @Test
    public void UserInputsANDPackLocationTest() {
        String simulatedInput = "4\n" + // Simulated input for number of players
                "test.txt\n"; // Simulated input for pack location
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        gameClass.UserInputs();

        assertEquals("test.txt", gameClass.getPackLocation());
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testSetNumberOfPlayers() {
        GameClass game = new GameClass();

        // Set the number of players using a public method
        game.setNumberOfPlayers(4);

        // Use another public method to get the number of players
        assertEquals(Integer.valueOf(4), game.getNumberOfPlayers());
    }


    @Test
    public void testValidatePackValid() {
        GameClass game = new GameClass();
        game.setNumberOfPlayers(4);
        assertTrue(game.validatePack("test.txt"));
    }

    @Test
    public void testValidatePackInvalid() {
        GameClass game = new GameClass();
        String packtest = "test.txt";
        String packtest2 = "test2";
        String packtest3 = "test3.txt";
        String packtest4 = "test4.txt";
        game.setNumberOfPlayers(3);
        game.setPackLocation(packtest2);
        assertFalse(game.validatePack(packtest));
        assertFalse(game.validatePack(packtest2));
    }

    @Test
    public void testUserInputsValid() {
        GameClass game = new GameClass();
        // Mocking user input for testing
        String[] input = {"4", "test.txt"};
        Scanner userInputMock = new Scanner(Arrays.toString(input));
        game.UserInputs();
        assertEquals(Integer.valueOf(3), game.getNumberOfPlayers());
        assertEquals("test.txt", game.getPackLocation());
    }


    @Test
    void createCardsTest() throws FileNotFoundException {
        // Initialize GameClass and set the pack location
        GameClass game = new GameClass();
        game.setPackLocation("test.txt"); // Ensure this is the correct path to your test file

        // Create cards
        game.createCards();

        // Verify each card's face value is an integer
        for (Card card : game.returnPack()) {
            assertTrue(card.ReturnCardFaceValue() instanceof Integer, "Card face value is not an integer");
        }
    }

    @Test
    public void createDeckTest()  {
        GameClass game = new GameClass();
        game.setNumberOfDecks(4);

        assertEquals(4, game.getNumberOfDecks());

    }

    @Test
    public void PlayersTest() {
        GameClass game = new GameClass();

        // Set the number of players using a public method
        game.setNumberOfPlayers(4);

        assertEquals(4, game.getNumberOfPlayers());
    }

    @Test
    public void DistributeCardsTest() throws FileNotFoundException {
        GameClass game = new GameClass();
        game.setNumberOfPlayers(4);
        game.setNumberOfDecks(4);
        CardDeck leftDeck = new leftDeck;
        player.setLeftDeck();

        for (Player player : game.players) {
            assertEquals(4, player.returnPlayerCards());
        }

        for (Player player : game.leftDeck) {
            assertEquals(5, player.returnPlayerCards());
        }

    }

    @Test
    void checkWinnerTest() {
        // Create an instance of GameClass
        GameClass game = new GameClass();

        // Set the number of players
        game.setNumberOfPlayers(4);

        // Simulate a player winning the game
        int winningPlayerId = 2;
        GameClass.CheckWinner(winningPlayerId);

        // Assert that the game is marked as won
        assertTrue(GameClass.gameWon);

        // Assert that the winner number is set correctly
        assertEquals(Integer.valueOf(winningPlayerId), GameClass.winnerNumber);
    }
}