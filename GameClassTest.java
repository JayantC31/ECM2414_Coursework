import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class GameClassTest {
    // create stream for inputs used for game
    private final InputStream originalSystemIn = System.in;


    @BeforeEach
    protected void setUp() {
        // create a new game to test with 4 people and test pack
        GameClass game = new GameClass();
        game.setNumberOfPlayers(4);
        String simulatedInput = "4\n" + // Simulated input for number of players
                "test.txt\n"; // Simulated input for pack location
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void UserInputsANDPackLocationTest() {
        GameClass game = new GameClass();
        String simulatedInput = "4\n" + // simulated input for number of players
                "test.txt\n"; // simulated input for pack location
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        game.UserInputs();
        assertEquals("test.txt", game.getPackLocation());
    }



    @Test
    public void testSetNumberOfPlayers() {
        GameClass game = new GameClass();
        // set the number of players
        game.setNumberOfPlayers(4);
        // get the number of players
        assertEquals(Integer.valueOf(4), game.getNumberOfPlayers());
    }


    @Test
    public void testValidatePackValid() {
        GameClass game = new GameClass();
        game.setNumberOfPlayers(4);
        // test that pack is valid
        assertTrue(game.validatePack("test.txt"));
    }

    @Test
    public void testValidatePackInvalid() {
        GameClass game = new GameClass();
        String packtest = "test.txt";
        String packtest2 = "test2";
        String packtest3 = "test3.txt";
        String packtest4 = "test4.txt";
        game.setNumberOfPlayers(4);
        game.setPackLocation(packtest2);
        if (!game.validatePack(packtest2)){
            assertFalse(false);
            // test that the pack is not valid
        }
    }

    @Test
    public void testUserInputsValid() {
        GameClass game = new GameClass();
        // assume 4 players for test
        String[] input = {"4", "test.txt"};
        Scanner userInputMock = new Scanner(Arrays.toString(input));
        game.UserInputs();
        //test for both user inputs
        assertEquals(Integer.valueOf(4), game.getNumberOfPlayers());
        assertEquals("test.txt", game.getPackLocation());
    }


    @Test
    void createCardsTest() throws FileNotFoundException {
        // set pack location
        GameClass game = new GameClass();
        game.setPackLocation("test.txt"); // ensure this is the correct path to your test file
        // create cards
        game.createCards();
        // verify each card's face value is an integer
        for (Card card : game.returnPack()) {
            assertTrue(card.ReturnCardFaceValue() instanceof Integer, "Card face value is not an integer");
        }
    }

    @Test
    public void createDeckTest()  {
        // create the card deck
        GameClass game = new GameClass();
        game.setNumberOfDecks(4);
        assertEquals(4, game.getNumberOfDecks());

    }

    @Test
    public void PlayersTest() {
        // create the player tests
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
        // find the actual left deck
        for (Player player : game.players) {
            if (player.returnPlayerID() == 1) {
                assertEquals(1, player.returnLeftDeck());
            }
        }

    }


    @Test
    void checkWinnerTest() {
        // create an instance of GameClass
        GameClass game = new GameClass();

        // set the number of players
        game.setNumberOfPlayers(4);

        // check for winner assuming player ID 2 has won
        int winningPlayerId = 2;
        GameClass.CheckWinner(winningPlayerId);

        // assert that the game is marked as won
        assertTrue(GameClass.gameWon);

        // assert that the winner number is set correctly
        assertEquals(Integer.valueOf(winningPlayerId), GameClass.winnerNumber);
    }
}
