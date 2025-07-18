package Player;

import AbstractCard.Card;
import Piles.DiscardPile;
import Piles.DrawPile;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private List<Card> cardList;
    private int score;

    public Player(String name){
        this.name = name;
        this.cardList = new ArrayList<>();
        this.score = 0;
    }

    // DRAW A ONE CARD
    public Card drawCard(){
        Card card = DrawPile.getInstance().drawCard();
        cardList.add(card);
        return card;
    }


    // DRAW MULTIPLE CARDS
    public void drawCard(int num){
        for (int i=0;i<num;i++){
            drawCard();
        }
    }

    //  play card
    public void playCard(int num){
        Card playedCard = cardList.remove(num);
        DiscardPile.getInstance().addCard(playedCard);
    }

    public int getScore() {
        return score;
    }

    public void incrementScore(int score) {
        this.score += score;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void clearCardList(){
        cardList = new ArrayList<>();
    }

}