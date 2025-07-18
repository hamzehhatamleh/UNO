package AbstractCard;

public interface Card {
    String getCardName();
    boolean isValidCard(Card card);
    int getCardScore();
}