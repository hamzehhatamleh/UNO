package Player;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import Exceptions.*;

// Manages the queue of players in the game
public class PlayersQueue {
    // Singleton instance of PlayersQueue
    private static PlayersQueue queueInstance;

    // Queue to store players
    private final Queue<Player> queue;

    // Private constructor initializes the queue and starts player input process
    private PlayersQueue(){
        queue = new LinkedList<>();
        initializeQueue();
    }

    // Singleton method to get the instance of PlayersQueue
    public static PlayersQueue getInstance(){
        if(queueInstance == null)  // If instance doesn't exist, create a new one
            queueInstance = new PlayersQueue();
        return queueInstance;
    }

    // Initializes the player queue based on user input
    private void initializeQueue(){
        System.out.println("Welcome to UNO!");
        try {
            // Ask for player names separated by spaces
            System.out.println("Enter your names separated by spaces:");
            Scanner input = new Scanner(System.in);
            String players = input.nextLine();

            // Split the input into an array of player names
            String[] playersArray = players.split(" ");

            // Validate the number of players (must be between 2 and 10)
            if(playersArray.length < 2){
                throw new InvalidInputException("You need at least 2 players to play UNO. Try again.");
            }
            if(playersArray.length > 10){
                throw new InvalidInputException("Maximum number of players is 10. Try again.");
            }

            // Add each player to the queue
            for (String player : playersArray) {
                Player p = new Player(player);
                queue.add(p);
            }
        } catch(InvalidInputException e){
            // Handle invalid input and prompt the user again
            System.out.println(e.getMessage());
            initializeQueue(); // Retry initializing the queue
        }
    }

    // Get the current queue of players
    public Queue<Player> getQueue(){
        return queue;
    }



    // Move the current player to the back of the queue (next player's turn)
    public void nextPlayer(){
        Player currentPlayer = queue.remove(); // Remove the current player
        queue.add(currentPlayer); // Add the player to the back of the queue
    }

    // Reverse the order of players in the queue using a stack
    public static <T> void reverseQueue(Queue<T> queue){
        Stack<T> stack = new Stack<>();

        // Move all players to the stack
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }

        // Move all players back from the stack to the queue, reversing the order
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }
}
