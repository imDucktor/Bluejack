import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Random random = new Random();
    private Card[] mainDeck;
    private Card[] computerDeck;
    private Card[] playerDeck;
    private Player player;
    private Player computer;

    public Game() {
        startGame();
        for (int i=0;i<=20;i++) {
            System.out.println(mainDeck[i].getCardInfo());
        }
        System.out.println("########");
        for (int i=0;i<=9;i++) {
            System.out.println(playerDeck[i].getCardInfo());
        }
        System.out.println("########");
        for (int i=0;i<=9;i++) {
            System.out.println(computerDeck[i].getCardInfo());
        }
    }

    private void create_mainDeck() {
        String[] cardColors = new String[]{"Red", "Blue", "Green", "Yellow"};
        final int TOTAL_NUMBER_OF_CARDS = 40;
        mainDeck = new Card[TOTAL_NUMBER_OF_CARDS];

        for (int i = 1, k = 0; i <= 10; i++) {
            for (String color : cardColors) {
                mainDeck[k] = new Card(i, color, false, false, false);
                k++;
            }
        }
    }
    private void shuffle_mainDeck() {
        for (int i = 0; i < 150; i++) {
            int m = random.nextInt(mainDeck.length);
            int n = random.nextInt(mainDeck.length);
            Card temp = mainDeck[n];
            mainDeck[n] = mainDeck[m];
            mainDeck[m] = temp;
        }
    }
    private Card useCardFromMain() {
        Card temp = mainDeck[0];
        Card[] tempDeck = new Card[mainDeck.length - 1];
        for (int i = 1; i < mainDeck.length; i++) {
            tempDeck[i - 1] = mainDeck[i];
        }
        mainDeck = tempDeck;
        return temp;
    }
    private Card removeCardWithIndexFromMain(int index) {
        Card temp = mainDeck[index];
        Card[] tempDeck = new Card[mainDeck.length - 1];
        for (int i = 0, k = 0; i < mainDeck.length; i++) {
            if (i != index) {
                tempDeck[k] = mainDeck[i];
                k++;
            }
        }
        mainDeck = tempDeck;
        return temp;
    }
    private void createFirstFiveCards() {
        for (int i = 0; i < 5; i++) {
            Card tempComputerCard = new Card(useCardFromMain());
            computerDeck[i] = tempComputerCard;
            Card tempPlayerCard = new Card(removeCardWithIndexFromMain(mainDeck.length-i-1));
            playerDeck[i] = tempPlayerCard;
        }
    }
    private void createThreeCards(Card[] deck) {
        for (int i = 5; i < 8; i++) {
            int value,k = 0;
            String color;
            do {
                value = mainDeck[k].getValue();
                color = mainDeck[k].getColor();
                k++;
            } while (value > 6);
            Card tempCard = new Card(removeCardWithIndexFromMain(k));
            boolean sign = random.nextBoolean();
            deck[i] = tempCard;
            deck[i].setSign(sign);
        }
    }
    private void createLastTwoCard(Card[] deck) {
        if (random.nextInt(5) == 0) {
            Card card = new Card(0, null, false, true, false);
            deck[8] = card;
        }else {
            boolean sign = random.nextBoolean();
            Card cardEight = useCardFromMain();
            deck[8] = cardEight;
            deck[8].setSign(sign);
        }
        if (random.nextInt(5) == 0) {
            Card card = new Card(0, null, false, false, true);
            deck[9] = card;
        }else {
            boolean sign = random.nextBoolean();
            Card cardNine = useCardFromMain();
            deck[9] = cardNine;
            deck[9].setSign(sign);
        }
    }
    private Card useCardFromComputer() {
        Card temp = computerDeck[0];
        Card[] tempDeck = new Card[computerDeck.length - 1];
        for (int i = 1; i < computerDeck.length; i++) {
            tempDeck[i - 1] = computerDeck[i];
        }
        computerDeck = tempDeck;
        return temp;
    }
    private Card useCardFromPlayer() {
        Card temp = playerDeck[0];
        Card[] tempDeck = new Card[playerDeck.length - 1];
        for (int i = 1; i < playerDeck.length; i++) {
            tempDeck[i - 1] = playerDeck[i];
        }
        playerDeck = tempDeck;
        return temp;
    }
    private void chooseFourCards() {
        for (int i = 0; i < 50; i++) {
            int m = random.nextInt(playerDeck.length);
            int n = random.nextInt(playerDeck.length);
            Card temp = playerDeck[n];
            playerDeck[n] = playerDeck[m];
            playerDeck[m] = temp;
        }
        for (int i = 0; i < 4; i++){
            Card tempCard = useCardFromPlayer();
            player.getHand()[i] = tempCard;
        }
        for (int i = 0; i < 50; i++) {
            int m = random.nextInt(computerDeck.length);
            int n = random.nextInt(computerDeck.length);
            Card temp = computerDeck[n];
            playerDeck[n] = computerDeck[m];
            playerDeck[m] = temp;
        }
        for (int i = 0; i < 4; i++){
            Card tempCard = useCardFromComputer();
            computer.getHand()[i] = tempCard;
        }
    }

    private void startGame() {
        player = new Player();
        computer = new Player();
        final int TOTAL_NUMBER_OF_CARDS_ON_PLAYERS_DECK = 10;
        computerDeck = new Card[TOTAL_NUMBER_OF_CARDS_ON_PLAYERS_DECK];
        playerDeck = new Card[TOTAL_NUMBER_OF_CARDS_ON_PLAYERS_DECK];
        create_mainDeck();
        shuffle_mainDeck();
        createFirstFiveCards();
        createThreeCards(computerDeck);
        createThreeCards(playerDeck);
        createLastTwoCard(computerDeck);
        createLastTwoCard(playerDeck);
        chooseFourCards();

    }
}