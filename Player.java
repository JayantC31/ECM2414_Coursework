/*
 * Player Class - Class for the players used in the game
 * @author Jayant Chawla and Pui Kin Chan
 * */


import java.awt.print.PrinterGraphics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Player extends Thread {

    private final Integer playerID;
    private Random random = new Random();
    private CardDeck leftDeck;
    private CardDeck rightDeck;

    private ArrayList<Card> playerCards;
    private final Object deckLock;

    public void run() {
        try (FileWriter fileWriter = new FileWriter("player" + playerID + "_output.txt")) {
            // Loop to continue the player's actions until the game is won
            fileWriter.write("Player " + playerID + " initial hand " + playerCards + "\n");
            fileWriter.write("ID: " + playerID + " preferred Denomination = " + playerID + "\n");
            while (!GameClass.gameWon) {
                // 1. Draw a card from the left deck
                Card drawnCard;
                drawnCard = leftDeck.drawCard();
                if (drawnCard != null) {
                    // 2. Choose a card to discard
                    fileWriter.write("Player " + playerID + " draws a " + drawnCard.ReturnCardFaceValue() + " from deck " + leftDeck.returnCardDeckID() + "\n");
                    Card cardToDiscard = chooseCardToDiscard();
                    synchronized (playerCards) {
                        synchronized (deckLock) {
                            playerCards.add(drawnCard);
                            // 3. Discard to the right deck
                            if (cardToDiscard != null) {
                                playerCards.remove(cardToDiscard);
                                rightDeck.addCardToDeck(cardToDiscard);
                                fileWriter.write("Player " + playerID + " discards a " + cardToDiscard.ReturnCardFaceValue() + " to deck " + rightDeck.returnCardDeckID() + "\n");
                                fileWriter.write("Player " + playerID + " hand is now " + playerCards + "\n");

                                // 4. Check for a winning hand
                                if (checkWinningCondition()) {
                                    GameClass.CheckWinner(playerID);
                                    fileWriter.write("Player " + playerID + " wins with hand " + handToString() + "\n");
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            // Print the final deck after the game is won
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

        // Remove cards that should not be discarded
        possibleDiscardCards.removeIf(card -> Objects.equals(card.ReturnCardFaceValue(), playerID));

        // Check if possibleDiscardCards is empty
        if (possibleDiscardCards.isEmpty()) {
            return null;
        }

        // Select a random card from possibleDiscardCards
        int randomIndex = random.nextInt(possibleDiscardCards.size());
        return possibleDiscardCards.get(randomIndex);
    }

    private synchronized boolean checkWinningCondition() {
        // Ensure that the player has at least one card
        if (playerCards.isEmpty()) {
            return false;  // Or handle this case according to your game logic
        }

        int firstCardValue = playerCards.get(0).ReturnCardFaceValue();
        return playerCards.stream().allMatch(card -> card.ReturnCardFaceValue() == firstCardValue);
    }



    private String handToString() {
        // Convert the hand to a string representation
        StringBuilder handStr = new StringBuilder();
        for (Card card : playerCards) {
            handStr.append(card.ReturnCardFaceValue()).append(" ");
        }
        return handStr.toString().trim();
    }



    public Player(Integer playerID, CardDeck leftDeck, ArrayList<Card> playerCards, CardDeck rightDeck, Object deckLock) {
        this.playerID = playerID;
        this.leftDeck = leftDeck;
        this.playerCards = playerCards;
        this.rightDeck = rightDeck;
        this.deckLock = deckLock;
    }

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


    public void setLeftDeck(CardDeck cardDeck) {this.leftDeck = cardDeck;}

    public void setRightDeck(CardDeck cardDeck) {
        this.rightDeck = cardDeck;
    }


}
