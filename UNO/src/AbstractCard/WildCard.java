package AbstractCard;

import Exceptions.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Utility.Display.printColorCards;

public abstract class WildCard implements Card{
    private Color chosenColor;
    public abstract String getCardName();

    @Override
    // always valid
    public boolean isValidCard(Card topCard) {
        return true;
    }

    @Override
    public int getCardScore() {
        return 50;
    }

    // choose of of the valid colors (RED,GREEN,YELLOW,BLUE)
    public void chooseColor(){
        System.out.println("Choose a color: (RED) (GREEN) (YELLOW) (BLUE) ");

        boolean validInput = false;
        while(!validInput) {
            try {
                Scanner input = new Scanner(System.in);
                int chosenColor = input.nextInt();
                if (chosenColor <= 0 || chosenColor > Color.values().length) {
                    throw new InvalidInputException("You chose an invalid card number.");
                }
                this.chosenColor = Color.values()[chosenColor - 1];
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage() + " Choose a valid color: ");
            } catch (InputMismatchException e){
                System.out.println("You need to enter a number. Choose a valid color: ");
            }
        }
    }

    public Color getChosenColor(){
        return chosenColor;
    }

    @Override
    public String toString() {
        return getCardName() + " card.";
    }

    public abstract void performAction(); // each wild card performs different action
}