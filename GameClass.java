import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner; // import the Scanner class

/*
 * Game Class - Class for the Game
 * @author Jayant Chawla and Pui Kin Chan
 * */





public class GameClass {

    /*
     * Attributes
     */
    public GameClass(){ /* Nothing needed in this to instantiate, will be used in the executable class to start the game */ }

    //this contains all players and creates a new player
    //private ArrayList<Player> players = new ArrayList<Player>();
    //this holds all the decks with a new deck for every two players
    //private ArrayList<CardDeck> decks = new ArrayList<CardDeck>();
    //holds all the cards used in the game

    private int numberOfPlayers;
    //this string is the location of the pack
    private String packLocation;
    //this is a check to see whether the game has ended or not
    private Boolean gameWon = false;
    //this is the number of the player who has won
    private Integer winnerNumber;

    public Integer getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer num){
        this.numberOfPlayers = num;
    }
    private ArrayList<Card> pack = new ArrayList<Card>();
    //this int shows the number of players in the game

    //this allows pack to be returned
    public ArrayList<Card> returnPack(){
        return pack;
    }
    /*
     * In this method, we will check for what the user will input in the terminal
     */
    public void UserInputs() {
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
                // make sure the input is an integer
                System.out.println("Username is: " + PlayerCountInput);
                if (PlayerCountInput <= 1) {
                    System.out.println("Please enter non negative number that is able to play the game: ");
                    // Don't allow game to start if user count is less then 2
                } else {
                    numberOfPlayers = PlayerCountInput;
                    NumOfPlayersValidity = true;
                    // if the count is valid then stop this while loop
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Integer input");
                // check for any exceptions for the integer
            }
        }
        System.out.println("Please enter location of pack to be loaded: ");
        boolean CardValidity = false;
        String CardLocationString = null;
        // check if the card pack is valid by checking the location of pack first
        while (!CardValidity) {
            try {
                CardLocationString = UserInput.nextLine();
                System.out.println("Location is: " + CardLocationString);
                CardValidity = validatePack(CardLocationString);
                // go to the next method to validate the pack
            } catch (Exception e) {
                System.out.println("Invalid Location input");
                // catch any exceptions if any problems when location is inputted
            }
        }
    // stop the user from inputting into the terminal to allow game to start checking packs and users
    UserInput.close();
    packLocation = CardLocationString;
    }

    /*
     * This is to check if the pack is valid
     */
    public boolean validatePack(String packLocation){

        boolean validityOfPack = false;
        try {
            // try to open the file
            File GivenPack = new File(packLocation);
            Scanner PackScan = new Scanner(GivenPack);
            Integer LineCounter = 0;
            while (PackScan.hasNextLine()) {
                String EachLine = PackScan.nextLine();
                LineCounter = LineCounter + 1;
                // check for each line given in the file
                // to check if the pack is correct, all values in list must be a single not-negative integer
                // It must also be 8n ( 8 x getnumberofplayers )
                int LineIntegerCheck = Integer.parseInt(EachLine);
                if (LineIntegerCheck < 0 || LineIntegerCheck > 10) {
                    validityOfPack = false;
                    System.out.println("The pack is not valid: all values in list must be a single not-negative integer");
                    System.exit(0);
                }
            }
            try {
                Integer TotalLineCount = LineCounter;
                // To test: System.out.println("Total line count: " + TotalLineCount + " / num of players: " + getNumberOfPlayers());
                // this is to test the total count of the lines used in the file given
                if (TotalLineCount / getNumberOfPlayers() != 8) {
                    validityOfPack = false;
                    System.out.println("The pack is not valid: total count of values in list must be 8n");
                    System.exit(0);
                } else {
                    validityOfPack = true;
                    System.out.println("Pack is valid");
                }
            } catch (NumberFormatException e) {
                System.out.println("Pack not valid");
                System.exit(0);
                // catch any exceptions if any problems when pack is located
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. ");
            validityOfPack = false;
        }
    // return the boolean and check whether the pack is valid
    return validityOfPack;
    }

    /*
     * This is to start the game by creating the cards
     */
    public void createCards() throws FileNotFoundException {
        // For this to work the game needs the cards to be created
        // do not need an if statement to check if pack is valid as that will be checked before
        File PackOfNumbers = new File(packLocation);
        Scanner PackScan = new Scanner(PackOfNumbers);
        while (PackScan.hasNextLine()) {
            String EachLine = PackScan.nextLine();
            // System.out.println(EachLine);
            int EachLineInteger = Integer.parseInt(EachLine);
            Card card = new Card(EachLineInteger);
            pack.add(card);
            // Create a thread for each card
            cardRunnable newCardThread = new cardRunnable ();
            Thread CardThread = new Thread (newCardThread , String.valueOf(card.ReturnCardFaceValue()));
            CardThread.start ();

        }
        PackScan.close();
        //System.out.println(returnPack());
    }



    public void createPlayers() {
        // The amount of players has already been checked and is confirmed
        System.out.println("Number of players: " + getNumberOfPlayers());
        for (int i = 1; i < getNumberOfPlayers() + 1; i ++) {
            // System.out.println (Thread.currentThread().getName() + "is running.");
            try {
                System.out.println("Player created with id " + i);
                //Player player = new Player();
                //playerArray.add(player)
                // Create a thread for each card
                //playerRunnable newPlayerThread = new playerRunnable ();
                //Thread PlayerThread = new Thread (newPlayerThread , String.valueOf(player.ReturnPlayerID()));
                //PlayerThread.start ();


            } catch ( Exception e) {
                System.out.println("Exception caught when creating a new player");
            }
        }





    }

    public void createDecks() {
        //System.out.println(returnPack());
    }

    public void DistributeCards() {
        //System.out.println(returnPack());
    }

    public synchronized void PlayGame() {
        //System.out.println(returnPack());
    }

    public void CheckWinner() {
        //System.out.println(returnPack());
    }





























}
