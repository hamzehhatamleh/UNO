package Utility;

import AbstractCard.ActionCard;
import AbstractCard.Card;
import AbstractCard.Color;
import AbstractCard.WildCard;
import Card.NumberedCard;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

import Utility.*;

import static Utility.Utility.*;

public class Display {
    private Display(){
        throw new AssertionError("This class should not be instantiated.");
    }

    public static void printTopDiscardedCard(Card card){
        System.out.println("Discard Pile");
        List<Card> cardList = new ArrayList<>();
        cardList.add(card);
        String discardPile = drawEmptyParts(cardList) + drawNamePart(cardList) + drawEmptyParts(cardList);
        System.out.println(discardPile);
    }
    public static void printPlayerCards(Player player){
        System.out.println(player.getName()+"'s turn");
        List<Card> cardList = player.getCardList();
        String playerCards = drawEmptyParts(cardList) + drawNamePart(cardList) + drawEmptyParts(cardList) + drawNumbers(cardList);
        System.out.println(playerCards);
    }

    public static void printColorCards(){
        List<Card> cardList = new ArrayList<>();
        for(Color color: Color.values()){
            NumberedCard card = new NumberedCard(0,color);
            cardList.add(card);
        }
        String colorCards = drawEmptyParts(cardList) + drawEmptyParts(cardList) + drawNumbers(cardList);
        System.out.println(colorCards);
    }

    private static String drawEmptyParts(List<Card> cardList){
        StringBuilder c = new StringBuilder();
        for(int i=0;i<2;i++){
            for (Card card : cardList) {
                String color = "";
                if(card instanceof WildCard wildCard){
                    color = wildCard.getChosenColor() == null ? WHITE : getColor(wildCard.getChosenColor());
                }
                if(card instanceof ActionCard actionCard){
                    color = getColor(actionCard.getColor());
                }
                if(card instanceof NumberedCard numberedCard){
                    color = getColor(numberedCard.getColor());
                }
                String fill = color + space(cardWidth) + STOP + space(spaceBetweenCards);
                c.append(fill);
            }
            c.append("\n");
        }
        return c.toString();
    }

    private static String drawNamePart(List<Card> cardList) {
        StringBuilder c = new StringBuilder();
        for (Card card : cardList) {
            String name = card.getCardName();
            String color = "";
            if(card instanceof WildCard wildCard){
                color = wildCard.getChosenColor() == null ? WHITE : getColor(wildCard.getChosenColor());
            }
            if(card instanceof ActionCard actionCard){
                color = getColor(actionCard.getColor());
            }
            if(card instanceof NumberedCard numberedCard){
                name=Integer.toString(numberedCard.getNumber());
                color = getColor(numberedCard.getColor());
            }
            int spaceCount = (cardWidth - name.length()) / 2;
            String fill = color + space(spaceCount) + BLACK_FONT + name + space(cardWidth - (name.length() + spaceCount)) + STOP + space(spaceBetweenCards);
            c.append(fill);
        }
        c.append("\n");
        return c.toString();
    }

    private static String drawNumbers(List<Card> cardList){
        StringBuilder c = new StringBuilder();
        c.append(space((cardWidth-1)/2));
        for(int i=1;i<=cardList.size();i++){
            c.append(i).append(space(cardWidth + (i < 10 ? 1 : 0)));
        }
        c.append("\n");
        return c.toString();
    }

}