/*
 * Player Class - Class for the players used in the game
 * @author Jayant Chawla and Pui Kin Chan
 * */


import java.awt.print.PrinterGraphics;
import java.util.ArrayList;

public class Player extends Thread{

    private final Integer playerID;

    private CardDeck leftDeck;
    private CardDeck rightDeck;

    private ArrayList<Card> playerCards;

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
