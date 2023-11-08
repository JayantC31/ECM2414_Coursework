import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {

    public CardGame game = new CardGame();

    @Test
    public void userInputs() {
        // Test the User Inputs
        game.setNumberOfPlayers(4);
        System.out.println(CardGame.getNumberOfPlayers());
        System.out.println("Testing New Test");

    }
}