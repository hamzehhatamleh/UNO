package Card;

import AbstractCard.ActionCard;
import AbstractCard.Color;
import Player.*;
import java.util.Queue;

import static Player.PlayersQueue.reverseQueue;

public class ReverseCard extends ActionCard {
    protected ReverseCard(Color color) {
        super(color);
    }

    @Override
    public String getCardName() {
        return "Reverse";
    }

    @Override
    // this funcion in the reverse card should only reverse the order of playing
    public void performAction() {
        Queue<Player> playerQueue = PlayersQueue.getInstance().getQueue();
        if(playerQueue.size()!=2) { // the queue shouldn't be reversed if there's only two players, it acts as a skip instead
            reverseQueue(playerQueue);
        }
    }
}

