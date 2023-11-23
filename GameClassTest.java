import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameClassTest {

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



}