/**
 * Player Class - Class for the players used in the game
 * @author Jayant Chawla and Pui Kin Chan
 * */


import java.io.FileWriter;
import java.util.*;


public class Player extends Thread {
    // create variables needed such as Player ID, decks needed and the cards used
    private final Integer playerID;
    private Random random = new Random();
    private CardDeck leftDeck;
    private CardDeck rightDeck;

    private ArrayList<Card> playerCards;
    private final Object deckLock;

    public synchronized void run() {
        try (FileWriter fileWriter = new FileWriter("player" + playerID + "_output.txt")) {
            // loop to continue the player's actions until the game is won
            fileWriter.write("Player " + playerID + " initial hand " + playerCards + "\n");
            fileWriter.write("ID: " + playerID + " preferred Denomination = " + playerID + "\n");
            while (!Game.gameWon) {
                synchronized (playerCards) {
                    synchronized (deckLock) {
                        // draw a card from the left deck
                        Card drawnCard;
                        drawnCard = leftDeck.drawCard();
                        if (drawnCard != null) {
                            // choose a card to discard
                            fileWriter.write("Player " + playerID + " draws a " + drawnCard.ReturnCardFaceValue() + " from deck " + leftDeck.returnCardDeckID() + "\n");
                            Card cardToDiscard = chooseCardToDiscard();
                                    playerCards.add(drawnCard);
                                    // discard to the right deck
                                    if (cardToDiscard != null) {
                                        playerCards.remove(cardToDiscard);
                                        rightDeck.addCardToDeck(cardToDiscard);
                                        fileWriter.write("Player " + playerID + " discards a " + cardToDiscard.ReturnCardFaceValue() + " to deck " + rightDeck.returnCardDeckID() + "\n");
                                        fileWriter.write("Player " + playerID + " hand is now " + playerCards + "\n");

                                        // check if the hand can win
                                        if (checkWinningCondition()) {
                                            Game.CheckWinner(playerID);
                                            fileWriter.write("Player " + playerID + " wins with hand " + handToString() + "\n");
                                            break;
                                }
                            }
                        }
                    }
                }
            }

            // print the final deck after the game is won
            synchronized (deckLock) {
                fileWriter.write("leftdeck is " + leftDeck.returnCardsInDeck() + " rightdeck is " + rightDeck.returnCardsInDeck() + "\n");
                rightDeck.writeDeckHand();
            }
        } catch (Exception e) {
            System.out.println("PROBLEM");
            e.printStackTrace();
        }
    }





    public synchronized Card chooseCardToDiscard() {
        ArrayList<Card> possibleDiscardCards = new ArrayList<>(playerCards);

        // remove the cards that should not be discarded
        possibleDiscardCards.removeIf(card -> Objects.equals(card.ReturnCardFaceValue(), playerID));

        // check if possibleDiscardCards is empty
        if (possibleDiscardCards.isEmpty()) {
            return null;
        }

        // select a random card from possibleDiscardCards to return
        int randomIndex = random.nextInt(possibleDiscardCards.size());
        return possibleDiscardCards.get(randomIndex);
    }

    private synchronized boolean checkWinningCondition() {
        // ensure that the player has at least one card
        if (playerCards.isEmpty()) {
            return false;  // if empty then return false
        }
        // check if player cards are all the same
        int firstCardValue = playerCards.get(0).ReturnCardFaceValue();
        return playerCards.stream().allMatch(card -> card.ReturnCardFaceValue() == firstCardValue);
    }



    private String handToString() {
        // convert the hand to a string representation
        StringBuilder handStr = new StringBuilder();
        for (Card card : playerCards) {
            handStr.append(card.ReturnCardFaceValue()).append(" ");
        }
        return handStr.toString().trim();
    }


    // create the players that are used to play the game
    public Player(Integer playerID, CardDeck leftDeck, ArrayList<Card> playerCards, CardDeck rightDeck, Object deckLock) {
        this.playerID = playerID;
        this.leftDeck = leftDeck;
        this.playerCards = playerCards;
        this.rightDeck = rightDeck;
        this.deckLock = deckLock;
    }


    // multiple methods used to return anything that player or game needs
    public Integer returnPlayerID() {
        return playerID;
    }

    public CardDeck returnLeftDeck() {return leftDeck;}

    public CardDeck returnRightDeck() {
        return rightDeck;
    }


    public synchronized ArrayList<Card> returnPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<Card> newCards) {
        this.playerCards = newCards;
    }

    public synchronized void addCardToPlayer(Card card) {
        this.playerCards.add(card);
    }

    // change the left and right decks to the cards used
    public void setLeftDeck(CardDeck cardDeck) {this.leftDeck = cardDeck;}

    public void setRightDeck(CardDeck cardDeck) {
        this.rightDeck = cardDeck;
    }


}
