package DefaultGame;

import Exceptions.*;
import Game.Game;
import Game.Options;
import Player.Player;
import Player.PlayersQueue;
import java.util.Scanner;

public class DefaultGame extends Game{
    public DefaultGame(){
        builderOptions = new Options.Builder().build(); // default options
        playerQueue = PlayersQueue.getInstance().getQueue();
    }

    public void play(){
        while (!isGameOver()) {
            round = new DefaultRound(playerQueue, builderOptions);
            round.playRound();
            if(isGameOver() || playMore().equals("n")){
                break;
            }
        }
        displayWinner();
    }

    @Override
    public boolean isGameOver(){
        int maxScore = 0;
        for (Player player : playerQueue){
            if(player.getScore() >= maxScore){
                maxScore = player.getScore();
                gameWinner = player;
            }
        }
        return gameWinner.getScore() >= builderOptions.getScoreToWin();
    }

    @Override
    public String playMore(){
        String playMore = "";
        boolean validInput = false;
        while (!validInput){
            try {
                System.out.println("Play another round? (y/n)");
                Scanner input = new Scanner(System.in);
                playMore = input.next();
                if (!(playMore.equalsIgnoreCase("y") || playMore.equalsIgnoreCase("n"))){
                    throw new InvalidInputException("You must enter y or n.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return playMore.toLowerCase();
    }

    @Override
    public void displayWinner(){
        System.out.println();
        System.out.println("🎉🎉🎉🎉🎉");
        System.out.println(gameWinner.getName().toUpperCase() + " HAS WON WITH A SCORE OF " + gameWinner.getScore() + "!!!!!");
    }
}