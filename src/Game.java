import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class Game {
    private final Random random = new Random();
    private Card[] mainDeck;
    private Card[] computerDeck;
    private Card[] playerDeck;
    private Player player;
    private Player computer;
    private Scanner sc = new Scanner(System.in);
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
    private Card useCardFromComputerDeck() {
        Card temp = computerDeck[0];
        Card[] tempDeck = new Card[computerDeck.length - 1];
        for (int i = 1; i < computerDeck.length; i++) {
            tempDeck[i - 1] = computerDeck[i];
        }
        computerDeck = tempDeck;
        return temp;
    }
    private Card useCardFromPlayerDeck() {
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
            Card tempCard = useCardFromPlayerDeck();
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
            Card tempCard = useCardFromComputerDeck();
            computer.getHand()[i] = tempCard;
        }
    }
    public void writeStatus(){
        computer.writeHand();
        computer.writeBoard();
        System.out.println("———————————————————————————————————");
        player.writeBoard();
        player.writeHand();
    }
    public void playCardFromPlayerHand() {
        boolean isntChoose = true;
        int action = 0;
        while (isntChoose){
            System.out.println("Choose your card to play:");
            player.writeHand();
            try{
                action = sc.nextInt();
            }catch (Exception e){
                System.out.println("Please choose a card.");
            }
            if(action>=1 && action<=4) {isntChoose = false;}
            else {System.out.println("Please choose a card.");}
        }
        player.addToBoard(player.getHand()[action-1],player.getAndIncreaseCardNumber());
        player.getHand()[action].usedCard();
        writeStatus();
    }
    public void playForPlayer(){
        writeStatus();
        System.out.println(player.getName() + "'s turn");
        if(player.getCardNumber()<8){
            int actionForCard = 0;
            boolean isNo = true;
            while (isNo) {
                System.out.println("Would you like a new card to be placed on the board?\n1. Yes\n2. No");
                try {
                    actionForCard = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Please choose an action.");
                }
                if (actionForCard >= 1 && actionForCard <= 2) {
                    isNo = false;
                } else {
                    System.out.println("Please choose an action.");
                }
            }
            if (actionForCard == 1) {player.addToBoard(useCardFromMain(), player.getAndIncreaseCardNumber());}
        }else {System.out.println("You can't request more cards.");}

        writeStatus();

        boolean isntChoose = true;
        int action = 0;
        while (isntChoose){
            System.out.println("Choose your action:\n1. Play\n2. End\n 3. Stand");
            try{
                action = sc.nextInt();
            }catch (Exception e){
                System.out.println("Please choose an action.");
            }
            if(action>=1 && action<=3) {isntChoose = false;}
            else {System.out.println("Please choose an action.");}
        }
        switch(action){
            case 1:
                System.out.println(player.getName() + "is playing...");
                playCardFromPlayerHand();
                break;
            case 2:
                System.out.println("End of this turn for " + player.getName() + ".");
                break;
            case 3:
                System.out.println(player.getName() + "chose to stand.");
                player.set_isCont(false);
                break;
        }
    }
    public void playForComputer(){
        System.out.println("Computer's turn");
        if(player.getCardNumber()<8){
            int actionForCard = 0;
            boolean isNo = true;
            while (isNo) {
                System.out.println("Would you like a new card to be placed on the board?\n1. Yes\n2. No");
                try {
                    actionForCard = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Please choose an action.");
                }
                if (actionForCard >= 1 && actionForCard <= 2) {
                    isNo = false;
                } else {
                    System.out.println("Please choose an action.");
                }
            }
            if (actionForCard == 1) {player.addToBoard(useCardFromMain(), player.getAndIncreaseCardNumber());}
        }else {System.out.println("You can't request more cards.");}

        writeStatus();

        if(computer.getSumOfBoard())
        switch(action){
            case 1:
                System.out.println(player.getName() + "is playing...");
                playCardFromPlayerHand();
                break;
            case 2:
                System.out.println("End of this turn for " + player.getName() + ".");
                break;
            case 3:
                System.out.println(player.getName() + "chose to stand.");
                player.set_isCont(false);
                break;
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