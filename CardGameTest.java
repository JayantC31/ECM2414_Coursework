import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {

    public GameClass game = new GameClass();

    @Test
    public void userInputs() {
        // Test the User Inputs
        game.setNumberOfPlayers(4);
        System.out.println(game.getNumberOfPlayers());
        System.out.println("Testing New Test");

    }
}