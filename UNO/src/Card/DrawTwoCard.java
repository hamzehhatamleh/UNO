package Card;

import AbstractCard.ActionCard;
import AbstractCard.Color;
import Player.PlayersQueue;
public class DrawTwoCard extends ActionCard {
    protected DrawTwoCard(Color color) {
        super(color);
    }

    @Override
    public String getCardName() {
        return "DrawTwoCard";
    }

    @Override
    public void performAction() {

        PlayersQueue playerQueue = PlayersQueue.getInstance(); // queue of players
        playerQueue.nextPlayer();  // select the next player
        playerQueue.getQueue().peek().drawCard(2);  // make him draw 2 cards
        System.out.println(playerQueue.getQueue().peek().getName()+" has drawn two cards!"); // print the action
        playerQueue.nextPlayer(); // skip to the next player


    }
}
