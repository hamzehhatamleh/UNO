package Card;

import AbstractCard.WildCard;
import Player.PlayersQueue;

public class WildColorCard extends WildCard {
    @Override
    public String getCardName() {
        return "Wild";
    }

    @Override
    // it's all about choose a color and go to the next player
    public void performAction() {
        chooseColor();
        PlayersQueue.getInstance().nextPlayer();
    }
}