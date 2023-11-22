
/**
 * Card Game Class - Executable
 * @author Jayant Chawla and Pui Kin Chan
 *
 * FOR TESTING:
 * CARDGAME FILE - EXECUTABLE
 * game file - for game
 * CARD FILE - THREAD SAFE
 * PLAYER FILE - THREAD SAFE
 * CARDDECK FILE
 *
 *
 * */


import java.io.FileNotFoundException;
import java.util.Scanner; // import the Scanner class
import java.io.FileWriter;

public class CardGame {

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        GameClass game = new GameClass();

        //this will be used to get the user inputs in order to start the game
        game.UserInputs();

        //create the cards
        game.createCards();

        //create the decks
        game.createDecks();

        //create the players
        game.createPlayers();

        //distribute the cards
        game.DistributeCards();

        game.PlayGame();



    }

}

/**
 * Runnable class to get cards for multithreading.
 */
class cardRunnable implements Runnable {
    public void run () {
        for (int i = 0; i < 1; i ++) {
            //System.out.println ("Card with face value: " + Thread.currentThread().getName() + " is running.");
            try {
                Thread.sleep (2000);
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
            //System.out.println ("Deck with card values: " + Thread.currentThread().getName() + " is running.");
            try {
                Thread.sleep (2000);
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
            //System.out.println ("Player with value: " + Thread.currentThread().getName() + " is running.");
            try {
                Thread.sleep (2000);
            } catch ( InterruptedException e) {
                System.out.println("Interupted exception");
            }
        }
    }
}
