package Utility;

import AbstractCard.Color;

import java.util.Queue;
import java.util.Stack;

public class Utility {
    public static String getColor(Color c){
        return switch (c) {
            case RED -> "\u001B[41m";
            case BLUE -> "\u001B[44m";
            case GREEN -> "\u001B[42m";
            case YELLOW -> "\u001B[43m";
            default -> throw new IllegalArgumentException("Illegal card color: " + c);
        };
    }
    public static final String STOP = "\u001B[0m";
    public static final String BLACK_FONT = "\u001B[30m";
    public static final String WHITE = "\u001B[47m";
    public static final int cardWidth = 11;
    public static final int spaceBetweenCards = 2;
    public static final int timeBetweenTurns = 650;

    private Utility(){
        throw new AssertionError("This class should not be instantiated.");
    }

    public static String space(int n) {
        StringBuilder s = new StringBuilder();
        while (n-- > 0) {
            s.append(" ");
        }
        return s.toString();
    }

}