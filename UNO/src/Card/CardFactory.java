package Card;
import AbstractCard.Card;
import AbstractCard.Color;
import Exceptions.*;

// so this will give a great code extendability , by deciding which card to create based on the provided arguments
public class CardFactory {

    // create a Numbered card
    public static Card createCard(int number, Color color){
        return new NumberedCard(number,color);
    }

    // create an Action card
    public static Card createCard(String cardType, Color color){
        return switch (cardType) {
            case "Skip" -> new SkipCard(color);
            case "Reverse" -> new ReverseCard(color);
            case "DrawTwo" -> new DrawTwoCard(color);
            default -> throw new IllegalCardException("Invalid card type: " + cardType);
        };
    }

    // create a Wild Card
    public static Card createCard(String cardType){
        return switch (cardType) {
            case "Wild" -> new WildColorCard();
            case "WildDrawFour" -> new WildDrawFourCard();
            default -> throw new IllegalCardException("Invalid card type: " + cardType);
        };
    }
}
