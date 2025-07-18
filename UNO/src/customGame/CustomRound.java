package customGame;

import AbstractCard.ActionCard;
import AbstractCard.Card;
import AbstractCard.WildCard;
import Card.NumberedCard;
import Exceptions.IllegalMoveException;
import Exceptions.InvalidInputException;
import Game.Round;
import Game.Options;
import Player.Player;
import Player.PlayersQueue;

import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;


// custom round (with changes )(for developers)
public class CustomRound extends Round {
    public CustomRound(Queue<Player> queue, Options o) {
        super(queue, o);
    }

    @Override
    protected void initializeRound(){
        int numOfPlayers = playerQueue.size();
        int numOfCardsPerPlayer = options.getNumOfCardsPerPlayer();
        if (numOfPlayers * numOfCardsPerPlayer > drawPile.getDrawPileSize()){
            throw new IllegalArgumentException("Not enough cards.");
        }
        for(int i=0;i<numOfPlayers;i++){
            Player player= playerQueue.remove();
            player.drawCard(numOfCardsPerPlayer);
            playerQueue.add(player);
        }
    }

    @Override
    protected void playCard(Player player, int cardNumber){
        Card card = player.getCardList().get(cardNumber);
        player.playCard(cardNumber);
        if (card instanceof NumberedCard) {
            PlayersQueue.getInstance().nextPlayer();
        } else if (card instanceof ActionCard actionCard){
            actionCard.performAction();
        } else if (card instanceof WildCard wildCard) {
            wildCard.performAction();
        }
    }

    @Override
    protected int chooseCard(Player player){
        int cardNumber = 0;
        boolean validMove = false;
        while(!validMove) {
            try {
                cardNumber = handleCardNumberInput(player);
                validateCardNumber(player, cardNumber);
                cardNumber--;
                validatePlayableCard(player, cardNumber);
                validMove = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage() + " Choose a valid card number:");
            } catch (InputMismatchException e){
                System.out.println("You need to enter a number. Enter a valid number:");
            } catch (IllegalMoveException e){
                System.out.println(e.getMessage() + " Choose a valid card:");
            }
        }
        return cardNumber;
    }

    private int handleCardNumberInput(Player player){
        System.out.println("Choose a card, " + player.getName());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void validateCardNumber(Player player, int cardNumber) throws InvalidInputException {
        if (cardNumber <= 0 || cardNumber > player.getCardList().size()) {
            throw new InvalidInputException("You chose an invalid card number.");
        }
    }

    private void validatePlayableCard(Player player, int cardNumber) throws IllegalMoveException {
        Card chosenCard = player.getCardList().get(cardNumber);
        if (!canBePlayed(chosenCard)) {
            throw new IllegalMoveException("You can't play this card.");
        }
    }

    @Override
    protected void displayRoundWinner() {
        System.out.println(roundWinner.getName() + " won this round 🎉🎉🎉🎉🎉");
    }

    @Override
    protected void displayScores(){
        for (Player player : playerQueue) {
            System.out.println(player.getName() + "'s score: " + player.getScore());
        }
    }

    @Override
    protected void endRound(){
        for(Player player : playerQueue){
            player.clearCardList();
        }
        drawPile.initializeDrawPile();
        discardPile.initializeDiscardPile();
    }
}