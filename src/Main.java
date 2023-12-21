import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String isNewGame = "";
        Scanner sc = new Scanner(System.in);
        while(!isNewGame.equals("n")){
            Game game = new Game();
            System.out.println("Do you want play a new game? [y]es [n]o\nIf you enter something other than \"y\" or \"n\", it will be started automatically.");
            isNewGame = sc.next();
        }
    }
}