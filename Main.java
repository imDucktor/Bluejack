import java.util.Scanner;

/**
 * Main class for the Bluejack card game.
 * Entry point for the application.
 */
public class Main {
    /**
     * Main method to start the game.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        String isNewGame = "y";
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        
        while (!isNewGame.equals("n")) {
            game.gameLoop();
            System.out.println("Do you want to play a new game? [y]es / [n]o");
            System.out.println("(If you enter something other than \"y\" or \"n\", a new game will start automatically)");
            isNewGame = scanner.next().toLowerCase();
        }
        
        System.out.println("Thank you for playing Bluejack!");
        scanner.close();
    }
}