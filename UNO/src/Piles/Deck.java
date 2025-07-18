package Piles;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Deck {
    private static Deck deckInstance; // Singleton instance of the deck
    private final HashMap<String, Integer> deck; // Stores card names and their counts

    private Deck() {
        deck = new HashMap<>();
        DeckInfo defaultDeckOptions = new DeckInfo();
        initializeDeck(defaultDeckOptions); // Initialize with default deck settings
    }

    public static Deck getInstance() { // Returns the singleton instance of the deck
        if (deckInstance == null)
            deckInstance = new Deck();
        return deckInstance;
    }

    public void setDeckOptions(DeckInfo deckOptions) { // Updates the deck with new options
        initializeDeck(deckOptions);
    }

    private void initializeDeck(DeckInfo deckOptions) { // Populates the deck using DeckInfo getters
        deck.clear();
        Class<?> deckOptionsClass = deckOptions.getClass();
        Method[] methods = deckOptionsClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get")) { // Identifies getter methods
                try {
                    int result = (Integer) method.invoke(deckOptions); // Calls getter method
                    deck.put(method.getName().substring(3), result); // Stores card name and count
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public HashMap<String, Integer> getDeck() { // Returns the deck hashmap
        return deck;
    }
}
