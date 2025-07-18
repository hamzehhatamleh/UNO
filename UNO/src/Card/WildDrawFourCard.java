package Card;

import AbstractCard.WildCard;
import Player.PlayersQueue;

public class WildDrawFourCard extends WildCard {
    @Override
    public String getCardName() {
        return "Draw Four";
    }

    @Override
    // choose a color , go to the next player , he should draw 4 cards , skip his turn
    public void performAction() {
        chooseColor();
        PlayersQueue playerQueue = PlayersQueue.getInstance();
        playerQueue.nextPlayer();
        playerQueue.getQueue().peek().drawCard(4);
        System.out.println(playerQueue.getQueue().peek().getName()+" has drawn four cards!");
        playerQueue.nextPlayer();
    }
}