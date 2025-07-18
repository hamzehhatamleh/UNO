package Game;


import DefaultGame.DefaultGame;
import customGame.CustomGame;

public class GameDriver {
    public static void main(String[] args){
        Game game = new DefaultGame();
        //Game game = new CustomGame();
        game.play();
    }
}