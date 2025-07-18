package Piles;

import AbstractCard.Card;
import Card.CardFactory;
import AbstractCard.Color;
import Exceptions.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class DrawPile {
    // Singleton instance of the DrawPile
    private static DrawPile drawPileInstance;

    // Stack to hold cards in the draw pile
    private Stack<Card> cardStack;

    // Private constructor to initialize the draw pile (Singleton)
    private DrawPile(){
        initializeDrawPile();
    }

    // Returns the singleton instance of the DrawPile
    public static DrawPile getInstance() {
        if(drawPileInstance == null){
            drawPileInstance = new DrawPile();
        }
        return drawPileInstance;
    }

    // Draws a card from the top of the draw pile, or refills from the discard pile if empty
    public Card drawCard(){
        if (cardStack.empty()){
            handleEmptyDrawPile();  // If the draw pile is empty, refill from the discard pile
        }
        return cardStack.pop();
    }

    // Initializes the draw pile by creating cards and shuffling them
    public void initializeDrawPile(){
        cardStack = new Stack<>();
        HashMap<String, Integer> deck = Deck.getInstance().getDeck();

        // Loop through each card type in the deck and create the appropriate cards
        for (String key : deck.keySet()) {
            createCardType(key, deck);
        }

        // Shuffle the cards in the draw pile
        Collections.shuffle(cardStack);
    }

    // Handles the case when the draw pile is empty: refills it from the discard pile
    private void handleEmptyDrawPile(){
        Stack<Card> discardPile = DiscardPile.getInstance().getCardStack();
        Card topCard = discardPile.pop();

        // Move all remaining cards from discard pile to draw pile
        while (!discardPile.empty()){
            cardStack.push(discardPile.pop());
        }

        // Shuffle the draw pile and push back the top card to the discard pile
        Collections.shuffle(cardStack);
        discardPile.push(topCard);
    }

    // Returns the current size of the draw pile
    public int getDrawPileSize(){
        return cardStack.size();
    }

    // Creates the appropriate cards based on the card type
    private void createCardType(String cardType, HashMap<String, Integer> deck){
        switch (cardType){
            case "Numbered" -> createNumberedCards(deck.get("Zero"), deck.get("Numbered"));
            case "Skip", "Reverse", "DrawTwo" -> createActionCards(cardType, deck.get(cardType));
            case "Wild", "WildDrawFour" -> createWildCards(cardType, deck.get(cardType));
            case "Zero" -> {}  // No action needed for the "Zero" card type
            default -> throw new IllegalCardException("Invalid card type: " + cardType);  // Invalid card type exception
        }
    }

    // Creates numbered cards (0-9) for each color, based on the quantity from the deck
    private void createNumberedCards(int numZeroCards, int numOtherCards){
        Color[] colors = Color.values();  // Get all available colors
        for (int i = 0; i <= 9; i++){
            int count = (i == 0) ? numZeroCards : numOtherCards;
            for (int j = 0; j < count; j++){
                for (Color color : colors){
                    cardStack.push(CardFactory.createCard(i, color));
                }
            }
        }
    }

    // Creates action cards (Skip, Reverse, DrawTwo) for each color, based on the quantity from the deck
    private void createActionCards(String cardType, int numCards){
        Color[] colors = Color.values();  // Get all available colors
        for (int i = 0; i < numCards; i++){
            for (Color color : colors) {
                cardStack.push(CardFactory.createCard(cardType, color));  // Create an action card and add it to the stack
            }
        }
    }

    // Creates wild cards (Wild, WildDrawFour), which have no color restriction, based on the quantity from the deck
    private void createWildCards(String cardType, int numCards){
        int numColors = Color.values().length;
        for (int i = 0; i < numCards; i++){
            for (int j = 0; j < numColors; j++) {
                cardStack.push(CardFactory.createCard(cardType));  // Create a wild card and add it to the stack
            }
        }
    }
}
