
/*
 * Card Game Class - Executable
 * @author Jayant Chawla and Pui Kin Chan
 *
 * FOR TESTING:
 * CARDGAME FILE - EXECUTABLE
 * CARD FILE - THREAD SAFE
 * PLAYER FILE - THREAD SAFE
 * CARDDECK FILE
 *
 *
 * */


import java.io.FileNotFoundException;
import java.util.Scanner; // import the Scanner class

public class CardGame {
    /*
     * Attributes
     */

    //this contains all players and creates a new player
    //private ArrayList<Player> players = new ArrayList<Player>();
    //this holds all the decks with a new deck for every two players
    //private ArrayList<CardDeck> decks = new ArrayList<CardDeck>();
    //holds all the cards used in the game
    //private ArrayList<Card> pack = new ArrayList<Card>();
    //this int shows the number of players in the game
    private static int numberOfPlayers;
    //this string is the location of the pack
    private static String packLocation;
    //this is a check to see whether the game has ended or not
    private Boolean gameWon = false;
    //this is the number of the player who has won
    private Integer winnerNumber;

    public static Integer getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer num){
        this.numberOfPlayers = num;
    }

    public CardGame(){ /* Nothing to instantiate */ }

    public static void main(String[] args) throws FileNotFoundException {
        UserInputs();


    }
    /*
     * In this method, we will check for what the user will input in the terminal
     */
    public static void UserInputs() {
        System.out.println("UserInput test");
        // Allow user to input variables needed for game
        Scanner UserInput = new Scanner(System.in);
        // this boolean allows us to check whether player count is allowed for game to continue
        boolean NumOfPlayersValidity = false;
        System.out.println("Please enter the number of players: ");
        while (!NumOfPlayersValidity) {
            try {
                String PlayerCountInputString = UserInput.nextLine();
                int PlayerCountInput = Integer.parseInt(PlayerCountInputString);
                System.out.println("Username is: " + PlayerCountInput);
                if (PlayerCountInput <= 1) {
                    System.out.println("Please enter non negative number that is able to play the game: ");
                } else {
                    numberOfPlayers = PlayerCountInput;
                    NumOfPlayersValidity = true;

                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Integer input");
            }
        }

        System.out.println("Please enter location of pack to be loaded: ");
        boolean CardValidity = false;
        String CardLocationString = null;
        while (!CardValidity) {
            try {
                CardLocationString = UserInput.nextLine();
                System.out.println("Location is: " + CardLocationString);
                CardValidity = validatePack(CardLocationString);

            } catch (Exception e) {
                System.out.println("Invalid Location input");
            }

        }
        UserInput.close();
        packLocation = CardLocationString;
    }


    public static boolean validatePack(String packLocation){

        return true;
    }














}
