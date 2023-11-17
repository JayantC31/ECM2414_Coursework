import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner; // import the Scanner class

/*
 * Game Class - Class for the Game
 * @author Jayant Chawla and Pui Kin Chan
 * */





public class GameClass {

    public static boolean gameWon;

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

    private int numberOfDecks;
    private String packLocation;
    //this is a check to see whether the game has ended or not
    //private Boolean gameWon = false;
    //this is the number of the player who has won
    private static Integer winnerNumber;

    public Integer getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public Integer getNumberOfDecks(){ return numberOfDecks;}

    public void setNumberOfDecks(Integer num) { this.numberOfDecks = num;}

    public void setNumberOfPlayers(Integer num){
        this.numberOfPlayers = num;
    }
    private ArrayList<Card> pack = new ArrayList<Card>();

    ArrayList<CardDeck> decks = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();


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




    /*
     * This is to create the decks used to play the cards
     */
    public void createDecks() {
        // The amount of players has already been checked and is confirmed
        setNumberOfDecks(getNumberOfPlayers());
        System.out.println("Number of decks: " + getNumberOfDecks());
        //System.out.println(pack);
        // Create 4 empty CardDecks
        for (int i = 1; i <= getNumberOfDecks(); i++) {
            CardDeck cardDeck = new CardDeck(i, new ArrayList<>());

            decks.add(cardDeck);
            deckRunnable newDeckThread = new deckRunnable();
            Thread DeckThread = new Thread(newDeckThread , String.valueOf(cardDeck.returnCardDeckID()));
            DeckThread.start ();
        }
        for (CardDeck cardDeck : decks) {
            //System.out.println("Deck created with id " + cardDeck.returnCardDeckID());
        }
    }

    /*
     * This is to create the players for the game
     */
    public void createPlayers() {
        // The amount of players has already been checked and is confirmed
        System.out.println("Number of players: " + getNumberOfPlayers());
        // Create 4 empty Players
        int numberOfPlayers = getNumberOfPlayers();
        int totalPlayers = decks.size();


        for (int i = 1; i <= numberOfPlayers; i++) {
            // Create CardDeck with the current player ID
            CardDeck tempLeftDeck = new CardDeck(-1, new ArrayList<>());
            CardDeck tempRightDeck = new CardDeck(-1, new ArrayList<>());

            Player player = new Player(i, tempLeftDeck, new ArrayList<>(), tempRightDeck);

            players.add(player);
            playerRunnable newPlayerThread = new playerRunnable();

            Thread PlayerThread = new Thread(newPlayerThread , String.valueOf(player.returnPlayerID()));
            //System.out.println(PlayerThread);
            PlayerThread.start ();

        }

        for (Player player : players) {
            //System.out.println("Deck created with id " + player.returnPlayerID());
        }

    }


    /*
     * This is to distribute the cards, to each deck and player
     */
    public void DistributeCards() {
        int numPlayers = players.size();
        int playerIndex = 0;
        for (int i = 0; i < pack.size() / 2; i++) {
            Card card = pack.get(i);
            Player player = players.get(playerIndex);
            // Add the card to the current CardDeck
            player.addCardToPlayer(card);
            // Move to the next deck in a round-robin fashion
            playerIndex = (playerIndex + 1) % numPlayers;
        }
        // Print the results for each CardDeck after decks are filled
        for (Player player : players) {
            //System.out.println("Player ID: " + player.returnPlayerID());
            //System.out.print("Cards in Player: ");
            for (Card cardInDeck : player.returnPlayerCards()) {
                //System.out.print(cardInDeck.ReturnCardFaceValue() + " ");
            }
            //System.out.println();
        }
        int numDecks = decks.size();
        int deckIndex = 0;
        // Iterate through the cards
        for (int i = pack.size() / 2; i < pack.size(); i++) {
            Card card = pack.get(i);
            // Find the CardDeck with the current index
            CardDeck cardDeck = decks.get(deckIndex);
            // Add the card to the current CardDeck
            cardDeck.addCardToDeck(card);
            // Move to the next deck in a round-robin fashion
            deckIndex = (deckIndex + 1) % numDecks;
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            // Determine the actual decks based on player's ID
            int leftDeckId = i + 1;
            int rightDeckId = (i == numberOfPlayers - 1) ? 1 : i + 2;
            // Find the actual left deck
            CardDeck leftDeck = null;
            for (CardDeck deck : decks) {
                if (deck.returnCardDeckID() == leftDeckId) {
                    leftDeck = deck;
                    break;
                }
            }
            // Find the actual right deck
            CardDeck rightDeck = null;
            for (CardDeck deck : decks) {
                if (deck.returnCardDeckID() == rightDeckId) {
                    rightDeck = deck;
                    break;
                }
            }
            // Update the player with the actual decks
            Player player = players.get(i);
            player.setLeftDeck(leftDeck);
            player.setRightDeck(rightDeck);
            // Print player information
            System.out.print("Player " + player.returnPlayerID() + " has Deck: ");
            for (Card cardInDeck : player.returnPlayerCards()) {
                System.out.print(cardInDeck.ReturnCardFaceValue() + " ");
            }
            System.out.println();
            //System.out.println(" Left Deck: " + player.returnLeftDeck().returnCardDeckID() + " Right Deck: " + player.returnRightDeck().returnCardDeckID());
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


    public synchronized void PlayGame() {
        // Start player threads
        List<Thread> playerThreads = new ArrayList<>();
        for (Player player : players) {
            Thread thread = new Thread(player);
            thread.start(); // Start the player thread
            playerThreads.add(thread);
            //System.out.println(thread);
        }

        // Monitor for the end of the game
        boolean gameEnd = false;
        while (!gameEnd) {
            synchronized (this) {
                if (gameWon) {
                    gameEnd = true;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        // Wait for all player threads to finish
        for (Thread thread : playerThreads) {
            try {
                thread.join(); // Wait for the thread to terminate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static synchronized void CheckWinner(int playerId) {
        if (!gameWon) {
            gameWon = true;
            winnerNumber = playerId;
            System.out.println("Player " + playerId + " wins!");

        }
    }





























}
