/**
 * Card Class - Class for the cards used in the game
 * @author Jayant Chawla and Pui Kin Chan
 * */

public class Card extends Thread {
    // only value the card needs is face value
    private final Integer CardFaceValue;
    // create the cards given the values
    public Card (Integer cardFaceValue) {
        this.CardFaceValue = cardFaceValue;

    }
    // used when returning the cards in game
    public Integer ReturnCardFaceValue() {
        return CardFaceValue;
    }

    @Override
    public String toString() {
        // include the card value in the string representation when returning the cards
        return ""+CardFaceValue;
    }
}
