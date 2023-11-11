/*
 * Cards Class - Class for the cards used in the game
 * @author Jayant Chawla and Pui Kin Chan
 * */

public class Cards {
    // The only value the card needs
    private final Integer CardFaceValue;
    // create the cards
    public Cards (Integer cardFaceValue) {
        this.CardFaceValue = cardFaceValue;
    }
    // used when returning the cards
    public Integer ReturnCardFaceValue() {
        return CardFaceValue;
    }



}
