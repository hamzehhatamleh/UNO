package Card;

import AbstractCard.ActionCard;
import AbstractCard.Color;
import Player.PlayersQueue;


public class SkipCard extends ActionCard {
    protected SkipCard(Color color) {
        super(color);
    }

    @Override
    public String getCardName() {
        return "Skip";
    }

    @Override
    // skip the next player
    public void performAction() {
        PlayersQueue playerQueue = PlayersQueue.getInstance();
        playerQueue.nextPlayer();
        System.out.println(playerQueue.getQueue().peek().getName()+" has been skipped!");
        playerQueue.nextPlayer();

    }
}
