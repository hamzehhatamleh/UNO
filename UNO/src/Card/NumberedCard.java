package Card;

import AbstractCard.ActionCard;
import AbstractCard.Card;
import AbstractCard.Color;
import AbstractCard.WildCard;
import Exceptions.*;


public class NumberedCard implements Card {
    private final int number;
    private final Color color;

    public NumberedCard(int number, Color color) {
        this.number = number;
        this.color = color;
    }
    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String getCardName() {
        return "Numbered";
    }

    @Override

    public boolean isValidCard(Card topCard) {
        // If the top card is an ActionCard, it can be played if it matches the color
        if (topCard instanceof ActionCard card2) {
            return getColor() == card2.getColor();
        }

        // If the top card is a WildCard, it can be played if it matches the chosen color
        if (topCard instanceof WildCard card2) {
            return getColor() == card2.getChosenColor();
        }

        // If the top card is a NumberedCard, it can be played if it matches either the number or color
        if (topCard instanceof NumberedCard card2) {
            return (getNumber() == card2.getNumber() || getColor() == card2.getColor());
        }

        // If the card type is not recognized, throw an exception
        throw new IllegalCardException("Invalid card type: " + topCard);
    }


    @Override
    public int getCardScore() {
        return number;
    }

    @Override
    public String toString() {
        return color.toString().toLowerCase() + " " + getNumber() + " card.";
    }
}