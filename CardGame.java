
/*
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

public class CardGame {


    public static void main(String[] args) throws FileNotFoundException {
        GameClass game = new GameClass();

        //this will be used to get the user inputs in order to start the game
        game.UserInputs();

        //create the cards
        game.createCards();

    }

}
