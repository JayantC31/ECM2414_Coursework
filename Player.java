/*
 * Player Class - Class for the players used in the game
 * @author Jayant Chawla and Pui Kin Chan
 * */


import java.awt.print.PrinterGraphics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Player extends Thread {

    private final Integer playerID;

    private CardDeck leftDeck;
    private CardDeck rightDeck;

    private ArrayList<Card> playerCards;

    public void run() {
        try (FileWriter fileWriter = new FileWriter("player" + playerID + "_output.txt")) {
            // Loop to continue the player's actions until the game is won
            while (!GameClass.gameWon) {
                // 1. Draw a card from the left deck
                Card drawnCard = leftDeck.drawCard();
                playerCards.add(drawnCard);
                fileWriter.write("Player " + playerID + " draws a " + drawnCard.ReturnCardFaceValue() + " from deck " + leftDeck.returnCardDeckID() + "\n");

                // 2. Choose a card to discard
                Card cardToDiscard = chooseCardToDiscard();
                playerCards.remove(cardToDiscard);

                // 3. Discard to the right deck
                rightDeck.addCardToDeck(cardToDiscard);
                fileWriter.write("Player " + playerID + " discards a " + cardToDiscard.ReturnCardFaceValue() + " to deck " + rightDeck.returnCardDeckID() + "\n");

                // 4. Check for a winning hand
                if (checkWinningCondition()) {
                    GameClass.CheckWinner(playerID);
                    fileWriter.write("Player " + playerID + " wins with hand " + handToString() + "\n");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private Card chooseCardToDiscard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + playerID + ", choose a card to discard (1-" + playerCards.size() + "):");
        for (int i = 0; i < playerCards.size(); i++) {
            System.out.println((i + 1) + ": Card " + playerCards.get(i).ReturnCardFaceValue());
        }

        int cardIndex = -1;
        while (cardIndex < 1 || cardIndex > playerCards.size()) {
            try {
                System.out.print("Enter the number of the card to discard: ");
                cardIndex = Integer.parseInt(scanner.nextLine());
                if (cardIndex < 1 || cardIndex > playerCards.size()) {
                    System.out.println("Invalid input. Please enter a number between 1 and " + playerCards.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Adjust for 0-based index
        return playerCards.get(cardIndex - 1);
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
