/**
 * Card Game Class - Executable
 * @author Jayant Chawla and Pui Kin Chan
 *
 * */

import java.io.FileNotFoundException;


public class CardGame {

    /**
     * @param args - used to start the game and make a new argument for the program
     * @throws FileNotFoundException - checks if file user inputs is found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Start the game
        Game game = new Game();

        //this will be used to get the user inputs in order to start the game
        game.UserInputs();

        //create the cards for the game
        game.createCards();

        //create the decks to store cards
        game.createDecks();

        //create the players needed for the game
        game.createPlayers();

        //distribute the cards, in even fashion to player and deck
        game.DistributeCards();

        // start the game
        game.PlayGame();

    }
}

/**
 * Runnable class to get cards for multithreading.
 */
class cardRunnable implements Runnable {
    public void run () {
        for (int i = 0; i < 1; i ++) {
            // start the threads for the cards
            try {
                Thread.sleep (2000);
                // every 2 seconds check on thread
            } catch ( InterruptedException e) {
                System.out.println("Interupted exception");
            }
        }
    }
}

/**
 * Runnable class to get decks for multithreading.
 */
class deckRunnable implements Runnable {
    public void run () {
        for (int i = 0; i < 1; i ++) {
            // start the threads for the deck
            try {
                Thread.sleep (2000);
                // every 2 seconds check on thread
            } catch ( InterruptedException e) {
                System.out.println("Interupted exception");
            }
        }
    }
}


/**
 * Runnable class to get players for multithreading.
 */
class playerRunnable implements Runnable {
    public void run () {
        for (int i = 0; i < 1; i ++) {
            // start the threads for the players
            try {
                Thread.sleep (2000);
                // every 2 seconds check on thread
            } catch ( InterruptedException e) {
                System.out.println("Interupted exception");
            }
        }
    }
}
