package customGame;

import DefaultGame.DefaultRound;
import Exceptions.InvalidInputException;
import Game.Game;
import Game.Options;
import Piles.DeckInfo;
import Player.Player;
import Player.PlayersQueue;

import java.util.Queue;
import java.util.Scanner;

public class CustomGame extends Game {
    public  Queue<Player> playersQueue;
    public Options options;

    //this is an example of a variation
    public CustomGame(){
        DeckInfo deckOptions = new DeckInfo();
        deckOptions.setWildDrawFourCards(2);
        deckOptions.setWildCards(2);
        options = new Options.Builder().deckOptions(deckOptions).drawOnlyOneCard(false).sayUno(false).numOfCardsPerPlayer(5).scoreToWin(100).build(); // customized options
        playersQueue = PlayersQueue.getInstance().getQueue();
    }

    public void play(){
        while (!isGameOver()) {
            CustomRound gameRound = new CustomRound(playersQueue, options);
            gameRound.playRound();
            if(isGameOver() || playMore().equals("n")){
                break;
            }
        }
        displayWinner();
    }

    @Override
    public boolean isGameOver(){
        int maxScore = 0;
        for (Player player : playersQueue){
            if(player.getScore() >= maxScore){
                maxScore = player.getScore();
                gameWinner = player;
            }
        }
        return gameWinner.getScore() >= options.getScoreToWin();
    }

    @Override
    public String playMore(){
        String playMore = "";
        boolean validInput = false;
        while (!validInput){
            try {
                System.out.println("Play another round? (yes / no)");
                Scanner input = new Scanner(System.in);
                playMore = input.next();
                if (!(playMore.equalsIgnoreCase("yes") || playMore.equalsIgnoreCase("no"))){
                    throw new InvalidInputException("You must enter yes or no.");
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
        System.out.println("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉");
        System.out.println(gameWinner.getName() + " won with a score of " + gameWinner.getScore() + "!!!!!");
        System.out.println("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉");
    }
}