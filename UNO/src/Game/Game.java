package Game;

import Player.Player;

import java.util.Queue;

public abstract class Game {
    public Options builderOptions;
    public Queue<Player> playerQueue;
    public Player gameWinner;
    public Round round;



    public abstract void play(); // play the game
    public abstract boolean isGameOver(); // check if the game ended
    public abstract String playMore(); // another game
    public abstract void displayWinner(); // who is the winner in the game

}
