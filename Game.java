import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Game Class - Class for the Game
 * @author Jayant Chawla and Pui Kin Chan
 * */


public class Game {
    // boolean to check if game is over
    public static boolean gameWon;

    /*
     * Attributes
     */
    public Game(){ /* Nothing needed in this to instantiate, will be used in the executable class to start the game */ }

    //this is the number of players in game
    private int numberOfPlayers;

    //this is the number of decks used in the game
    private int numberOfDecks;
    //this string is the location of the pack
    private String packLocation;


    //this is the number of the player who has won
    public static Integer winnerNumber;


    // These are the Methods used to return each variable that we are holding that could be useful in the game or to test
    public Integer getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public Integer getNumberOfDecks(){
        return numberOfDecks;
    }

    public void setNumberOfDecks(Integer num) {
        this.numberOfDecks = num;
    }

    public void setNumberOfPlayers(Integer num){
        this.numberOfPlayers = num;
    }

    public void setPackLocation(String packLocation) {
        this.packLocation = packLocation;
    }

    public String getPackLocation() {
        return this.packLocation;
    }


    // This is the arrays that are used to store the cards and then store in a pack
    private ArrayList<Card> pack = new ArrayList<Card>();

    // This is the arrays that are used to store the decks
    ArrayList<CardDeck> decks = new ArrayList<>();

    // This is the arrays that are used to store the players
    ArrayList<Player> players = new ArrayList<>();

    // This allows the deck to be locked and created
    private static final Object deckLock = new Object();


    // return the packs to the user
    public ArrayList<Card> returnPack(){
        return pack;
    }

    /**
     * In this method, we will check for what the user will input in the terminal
     */
    public void UserInputs() {
        System.out.println("UserInput test");
        // allow user to input variables needed for game
        Scanner UserInput = new Scanner(System.in);
        // this boolean allows us to check whether player count is allowed for game to continue
        boolean NumOfPlayersValidity = false;
        System.out.println("Please enter the number of players: ");
        // while loop to check for the variables
        while (!NumOfPlayersValidity) {
            try {
                String PlayerCountInputString = UserInput.nextLine();
                int PlayerCountInput = Integer.parseInt(PlayerCountInputString);
                // make sure the input is an integer
                System.out.println("Username is: " + PlayerCountInput);
                if (PlayerCountInput <= 1) {
                    System.out.println("Please enter non negative number that is able to play the game: ");
                    // don't allow game to start if user count is less then 2
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

    /**
     * This is to check if the pack is valid
     * @param packLocation
     * @return  returns the file of the deck
     */
    public boolean validatePack(String packLocation){
        // use boolean to check if pack is valid or not
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
                // this is to test the total count of the lines used in the file given
                if (TotalLineCount / getNumberOfPlayers() != 8) {
                    validityOfPack = false;
                    System.out.println("The pack is not valid: total count of values in list must be 8n");
                } else {
                    validityOfPack = true;
                    System.out.println("Pack is valid");
                }
            } catch (NumberFormatException e) {
                System.out.println("Pack not valid");
                // catch any exceptions if any problems when pack is located
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. ");
            validityOfPack = false;
        }
    // return the boolean and check whether the pack is valid
    return validityOfPack;
    }

    /**
     * This is to start the game by creating the cards
     * @throws FileNotFoundException
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
    }


    /**
     * This is to create the decks used to play the cards
     */
    public void createDecks() {
        // The amount of players has already been checked and is confirmed
        setNumberOfDecks(getNumberOfPlayers());
        System.out.println("Number of decks: " + getNumberOfDecks());
        // Create 4 empty CardDecks
        for (int i = 1; i <= getNumberOfDecks(); i++) {
            CardDeck cardDeck = new CardDeck(i, new ArrayList<>());
            // create the decks and add to the array
            decks.add(cardDeck);
            deckRunnable newDeckThread = new deckRunnable();
            Thread DeckThread = new Thread(newDeckThread , String.valueOf(cardDeck.returnCardDeckID()));
            DeckThread.start ();
        }

    }

    /**
     * This is to create the players for the game
     * @return number of players
     */
    public int createPlayers() {
        // The amount of players has already been checked and is confirmed
        System.out.println("Number of players: " + getNumberOfPlayers());
        // Create 4 empty Players
        int numberOfPlayers = getNumberOfPlayers();
        int totalPlayers = decks.size();
        for (int i = 1; i <= numberOfPlayers; i++) {
            // Create CardDeck with the current player ID
            CardDeck tempLeftDeck = new CardDeck(-1, new ArrayList<>());
            CardDeck tempRightDeck = new CardDeck(-1, new ArrayList<>());
            // create temp decks to connnect to player as they do not have cards yet
            Player player = new Player(i, tempLeftDeck, new ArrayList<>(), tempRightDeck, deckLock);
            players.add(player);
            playerRunnable newPlayerThread = new playerRunnable();
            // create the threads
            Thread PlayerThread = new Thread(newPlayerThread , String.valueOf(player.returnPlayerID()));
            PlayerThread.start ();

        }

        return numberOfPlayers;
    }


    /**
     *  This is to distribute the cards, to each deck and player
     */
    public void DistributeCards() {
        int numPlayers = players.size();
        int playerIndex = 0;
        for (int i = 0; i < pack.size() / 2; i++) {
            Card card = pack.get(i);
            Player player = players.get(playerIndex);
            // add the card to the current deck
            player.addCardToPlayer(card);
            // go to the next deck in a round-robin fashion
            playerIndex = (playerIndex + 1) % numPlayers;
        }
        int numDecks = decks.size();
        int deckIndex = 0;
        // iterate through the cards
        for (int i = pack.size() / 2; i < pack.size(); i++) {
            Card card = pack.get(i);
            // find the deck with the current index
            CardDeck cardDeck = decks.get(deckIndex);
            // add the card to the current deck
            cardDeck.addCardToDeck(card);
            // go to the next deck in a round-robin way
            deckIndex = (deckIndex + 1) % numDecks;
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            // find the actual decks based on player's ID
            int leftDeckId = i + 1;
            int rightDeckId = (i == numberOfPlayers - 1) ? 1 : i + 2;
            // get the actual left deck
            CardDeck leftDeck = null;
            for (CardDeck deck : decks) {
                if (deck.returnCardDeckID() == leftDeckId) {
                    leftDeck = deck;
                    break;
                }
            }
            // find the actual right deck
            CardDeck rightDeck = null;
            for (CardDeck deck : decks) {
                if (deck.returnCardDeckID() == rightDeckId) {
                    rightDeck = deck;
                    break;
                }
            }
            // update the player with the actual decks
            Player player = players.get(i);
            player.setLeftDeck(leftDeck);
            player.setRightDeck(rightDeck);
            // print player information
            System.out.print("Player " + player.returnPlayerID() + " has Deck: ");
            for (Card cardInDeck : player.returnPlayerCards()) {
                System.out.print(cardInDeck.ReturnCardFaceValue() + " ");
            }
            System.out.println();
        }
        // Print the results for each CardDeck after decks are filled
        for (CardDeck cardDeck : decks) {
            System.out.print("Deck " + cardDeck.returnCardDeckID() + " has cards: ");
            for (Card cardInDeck : cardDeck.returnCardsInDeck()) {
                System.out.print(cardInDeck.ReturnCardFaceValue() + " ");
            }
            System.out.println();
        }
    }

    /**
     * This is to start the game and all variables necessary
     */
    public synchronized void PlayGame() {
        // start the player threads
        List<Thread> playerThreads = new ArrayList<>();
        for (Player player : players) {
            Thread thread = new Thread(player);
            thread.start(); // start the threads
            playerThreads.add(thread);
        }
        // monitor for the end of the game
        boolean gameEnd = false;
        while (!gameEnd) {
            synchronized (this) {
                if (gameWon) {
                    gameEnd = true;
                }
            }
            try {
                Thread.sleep(2000); // check if game is won after every 2 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        // wait for all player threads to finish
        for (Thread thread : playerThreads) {
            try {
                thread.join(); // wait for the thread to terminate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This is to end the game and all variables necessary and all checking for a winner
     * @param playerId - check for the ID of player
     */
    public static synchronized void CheckWinner(int playerId) {
        if (!gameWon) {
            gameWon = true;
            // if game has been won then get the player ID and print it out
            winnerNumber = playerId;
            System.out.println("Player " + playerId + " wins!");

        }
    }



}
