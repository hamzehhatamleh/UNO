package Game;

import AbstractCard.Card;
import Game.Options;
import Piles.DiscardPile;
import Piles.DrawPile;
import Player.Player;
import Player.PlayersQueue;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static Utility.Display.*;
import static Utility.Utility.*;


public abstract class Round {
    protected final DrawPile drawPile;
    protected final DiscardPile discardPile;
    protected final Queue<Player> playerQueue;
    protected final Options options;  // for the builder pattern
    protected Player roundWinner;

    public Round(Queue<Player> queue, Options o){
        playerQueue = queue;
        options = o;
        drawPile = DrawPile.getInstance();
        discardPile = DiscardPile.getInstance();
    }

    // play the round and apply the rules
    public void playRound(){
        initializeRound();
        while (!isRoundOver()) {
            Player currentPlayer = playerQueue.peek();
            printPlayerCards(currentPlayer);
            Card topDiscardedCard = discardPile.getTopCard();
            printTopDiscardedCard(topDiscardedCard);
            if(!hasPlayableCard(currentPlayer)){
                System.out.println("You don't have a card to play, " + currentPlayer.getName() + "! Drawing a card.");
                Card drawnCard = currentPlayer.drawCard();
                System.out.println("You drew a " + drawnCard.toString());
                if (canBePlayed(drawnCard)){ // play the drawn card if valid
                    System.out.println("You can play this card.");
                    playCard(currentPlayer, currentPlayer.getCardList().size()-1);
                } else if (options.getDrawOnlyOneCardIfCantPlay()){ // if only one card move to the next player
                    PlayersQueue.getInstance().nextPlayer();
                }
            } else {
                int cardNumber = chooseCard(currentPlayer);
                playCard(currentPlayer, cardNumber);
            }
            System.out.println("-------------------------------------------------");
            try {
                TimeUnit.MILLISECONDS.sleep(timeBetweenTurns); // pause for some time after each turn
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        displayRoundWinner();
        calculateScore();
        displayScores();
        endRound();
    }

    private boolean isRoundOver(){
        for (Player player : playerQueue){
            if(player.getCardList().size() == 0){
                roundWinner = player;
                return true;
            }
        }
        return false;
    }

    private Boolean hasPlayableCard(Player player){
        List<Card> cardList = player.getCardList();
        for(Card card : cardList){
            if (canBePlayed(card)){
                return true;
            }
        }
        return false;
    }

    protected boolean canBePlayed(Card playerCard){
        Card topCard = discardPile.getTopCard();
        return playerCard.isValidCard(topCard);
    }

    private void calculateScore(){
        for (Player player : playerQueue){
            for (Card card : player.getCardList()){
                roundWinner.incrementScore(card.getCardScore());
            }
        }
    }

    protected abstract void initializeRound();
    protected abstract void playCard(Player player, int cardNumber);
    protected abstract int chooseCard(Player player);
    protected abstract void displayRoundWinner();
    protected abstract void displayScores();
    protected abstract void endRound();

}