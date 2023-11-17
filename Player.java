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

    public void run() {
        try (FileWriter fileWriter = new FileWriter("player" + playerID + "_output.txt")) {
            // Loop to continue the player's actions until the game is won
            fileWriter.write("Player " + playerID + " initial hand " + playerCards + "\n");
            fileWriter.write("ID: " + playerID + " preferred Denomination = " + (playerID) + "\n");
            while (!GameClass.gameWon) {
                // 1. Draw a card from the left deck
                Card drawnCard = leftDeck.drawCard();
                playerCards.add(drawnCard);
                fileWriter.write("left deck: " + leftDeck.returnCardsInDeck()   + "\n");
                fileWriter.write("Player " + playerID + " draws a " + drawnCard.ReturnCardFaceValue() + " from deck " + leftDeck.returnCardDeckID() + "\n");

                // 2. Choose a card to discard
                Card cardToDiscard = chooseCardToDiscard();
                playerCards.remove(cardToDiscard);

                // 3. Discard to the right deck
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
        } catch (Exception e) {
            System.out.println("PROBLEM");
            e.printStackTrace();
        }
    }



    public Card chooseCardToDiscard(){
        ArrayList<Card> possibleDiscardCards = new ArrayList<>();
        //creates an ArrayList filled with all the cards that aren't the player's preferred card
        for (Card card : playerCards) {
            if (!(Objects.equals(card.ReturnCardFaceValue(), playerID))) {
                possibleDiscardCards.add(card);
            }
        }
        //selects a random integer which is the index for the card that we will remove and returns the card
        int randomIndex = random.nextInt(possibleDiscardCards.size());
        Card cardToDiscard = possibleDiscardCards.get(randomIndex);
        playerCards.remove(cardToDiscard);
        return cardToDiscard;
    }





    private boolean checkWinningCondition() {

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



    public Player(Integer playerID, CardDeck leftDeck, ArrayList<Card> playerCards, CardDeck rightDeck) {
        this.playerID = playerID;
        this.leftDeck = leftDeck;
        this.playerCards = playerCards;
        this.rightDeck = rightDeck;
    }

    public Integer returnPlayerID() {
        return playerID;
    }

    public CardDeck returnLeftDeck() {
        return leftDeck;
    }

    public CardDeck returnRightDeck() {
        return rightDeck;
    }


    public ArrayList<Card> returnPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<Card> newCards) {
        this.playerCards = newCards;
    }

    public void addCardToPlayer(Card card) {
        this.playerCards.add(card);
    }


    public void setLeftDeck(CardDeck cardDeck) {
        this.leftDeck = cardDeck;
    }

    public void setRightDeck(CardDeck cardDeck) {
        this.rightDeck = cardDeck;
    }


}
