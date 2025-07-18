package AbstractCard;

import Card.NumberedCard;

import Exceptions.IllegalCardException;
// Action cars (Reverse , DrawTwo , Skip ) implementation
public abstract class ActionCard implements Card{
    private final Color color;

    protected ActionCard(Color color){
        this.color=color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean isValidCard(Card topCard) {
        if(topCard instanceof NumberedCard card2){ // if the top card is numbered card , then i should throw the same color
            return (getColor() == card2.getColor());
        }
        // if the top card is one of (Reverse,Skip,DrawTwo) , then i should even throw the same color or the same card
        if(topCard instanceof ActionCard card2){
            return (getColor() == card2.getColor() || getCardName().equals(card2.getCardName()));
        }
        //if the top card is wild color , then the plaer should throw a color the same as chosen.
        if(topCard instanceof WildCard card2){
            return getColor() == card2.getChosenColor();
        }
        throw new IllegalCardException("Invalid card type: " + topCard);
    }

    @Override
    //  the score for action cards is always 20
    public int getCardScore() {
        return 20;
    }

    @Override
    public String toString() {
        return color.toString().toLowerCase() + " " + getCardName() + " card.";
    }

    public abstract String getCardName();
    public abstract void performAction(); // each action card perform a different action
}