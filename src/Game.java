import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;

public class Game {
    private final Random random = new Random();
    private Card[] mainDeck;
    private Card[] computerDeck;
    private Card[] playerDeck;
    private Player player;
    private Player computer;
    private Scanner sc = new Scanner(System.in);
    private int setNo = 1;
    private String Scores[][];
    public Game() {
        startGame();
        gameLoop();
    }

    private void create_mainDeck() {
        String[] cardColors = new String[]{"Red", "Blue", "Green", "Yellow"};
        final int TOTAL_NUMBER_OF_CARDS = 40;
        mainDeck = new Card[TOTAL_NUMBER_OF_CARDS];

        for (int i = 1, k = 0; i <= 10; i++) {
            for (String color : cardColors) {
                mainDeck[k] = new Card(i, color, false, false, false,false);
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
            Card card = new Card(0, "null", false, true, false,false);
            deck[8] = card;
        }else {
            boolean sign = random.nextBoolean();
            Card cardEight = useCardFromMain();
            deck[8] = cardEight;
            deck[8].setSign(sign);
        }
        if (random.nextInt(5) == 0) {
            Card card = new Card(0, "null", false, false, true,false);
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
            int m = random.nextInt(computerDeck.length-1);
            int n = random.nextInt(computerDeck.length-1);
            Card temp = computerDeck[n];
            computerDeck[n] = computerDeck[m];
            computerDeck[m] = temp;
        }
        for (int i = 0; i < 4; i++){
            Card tempCard = useCardFromComputerDeck();
            computer.getHand()[i] = tempCard;
        }
    }
    private void writeStatus(){
        System.out.println("———————————————————————————————————");
        computer.writeHand(true);
        computer.writeBoard();
        System.out.println("———————————————————————————————————");
        player.writeBoard();
        player.writeHand(false);
        System.out.println("———————————————————————————————————");
    }
    private void playCardFromPlayerHand() {
        boolean isntChoose = true;
        int action = 0;
        while (isntChoose){
            System.out.println("Choose your card to play:");
            player.writeHand(false);
            try{
                action = sc.nextInt();
            }catch (Exception e){
                System.out.println("Please choose a card.");
            }
            if(action>=1 && action<=4) {isntChoose = false;}
            else {System.out.println("Please choose a card.");}
        }
        if(player.getHand()[action-1].isFlip()){
            player.getLocationOfLastPlayedCard().flipValue();
            player.addToBoard(player.getHand()[action-1],player.getAndIncreaseCardNumber());
            player.getHand()[action-1].usedCard();
        }else if(player.getHand()[action-1].isDouble()){
            player.getLocationOfLastPlayedCard().doubleValue();
            player.addToBoard(player.getHand()[action-1],player.getAndIncreaseCardNumber());
            player.getHand()[action-1].usedCard();
        }else {
            player.addToBoard(player.getHand()[action - 1], player.getAndIncreaseCardNumber());
            player.getHand()[action-1].usedCard();
        }
        writeStatus();
    }
    private void playForPlayer(){
        writeStatus();
        System.out.println("\n" + player.getName() + "'s turn\n");
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
    private void playForComputer() {
        writeStatus();
        System.out.println("Computer's turn");
        int sum = computer.getSumOfBoard();
        System.out.println(sum);
        if (sum <= 15) {
            computer.addToBoard(useCardFromMain(), computer.getAndIncreaseCardNumber());
            computer.setNoAction(false);
        }
        sum = computer.getSumOfBoard();
        System.out.println(sum);
        int max = sum;
        int maxIndex = -1;
        for (int i = 0; i <= 3; i++) {
            if (computer.getHand()[i].getColor() == "X") {
                break;
            } else if (max == 20) {
                break;
            } else if (max <= 20) {
                if (player.getHand()[i].isFlip()) {
                    if (computer.getLocationOfLastPlayedCard() != null && computer.getLocationOfLastPlayedCard().isFlip() && computer.getLocationOfLastPlayedCard().isDouble()) {
                        sum += computer.getLocationOfLastPlayedCard().getValue() * -1;
                        if (sum > max && sum <= 20) {
                            max = sum;
                            maxIndex = i;
                        }
                    }
                } else if (player.getHand()[i].isDouble()) {
                    if (computer.getLocationOfLastPlayedCard() != null && computer.getLocationOfLastPlayedCard().isFlip() && computer.getLocationOfLastPlayedCard().isDouble()) {
                        sum += computer.getLocationOfLastPlayedCard().getValue();
                        if (sum > max && sum <= 20) {
                            max = sum;
                            maxIndex = i;
                        }
                    }
                } else {
                    sum += computer.getHand()[i].getValue();
                    if (sum > max && sum <= 20) {
                        max = sum;
                        maxIndex = i;
                    }
                }
            } else if (max > 20) {
                if (player.getHand()[i].isFlip()) {
                    if (computer.getLocationOfLastPlayedCard() != null && computer.getLocationOfLastPlayedCard().isFlip() && computer.getLocationOfLastPlayedCard().isDouble()) {
                        sum += computer.getLocationOfLastPlayedCard().getValue() * -1;
                        if (sum <= 20) {
                            max = sum;
                            maxIndex = i;
                        }
                    }
                } else if (player.getHand()[i].isDouble()) {
                    if (computer.getLocationOfLastPlayedCard() != null && computer.getLocationOfLastPlayedCard().isFlip() && computer.getLocationOfLastPlayedCard().isDouble()) {
                        sum += computer.getLocationOfLastPlayedCard().getValue();
                        if (sum <= 20) {
                            max = sum;
                            maxIndex = i;
                        }
                    }
                } else {
                    sum += computer.getHand()[i].getValue();
                    if (sum <= 20) {
                        max = sum;
                        maxIndex = i;
                    }
                }
            }
        }
        if (maxIndex != -1) {
            if (computer.getHand()[maxIndex].isFlip()) {
                computer.getLocationOfLastPlayedCard().flipValue();
                computer.addToBoard(computer.getHand()[maxIndex], computer.getAndIncreaseCardNumber());
                computer.getHand()[maxIndex].usedCard();
                computer.setNoAction(false);
            } else if (computer.getHand()[maxIndex].isDouble()) {
                computer.getLocationOfLastPlayedCard().doubleValue();
                computer.addToBoard(computer.getHand()[maxIndex], computer.getAndIncreaseCardNumber());
                computer.getHand()[maxIndex].usedCard();
                computer.setNoAction(false);
            } else {
                computer.addToBoard(computer.getHand()[maxIndex], computer.getAndIncreaseCardNumber());
                computer.getHand()[maxIndex].usedCard();
                computer.setNoAction(false);
            }
        }
    }
    private void history() {
        Formatter formatter = null;
        FileWriter fileWriter = null;
        Scanner reader = null;
        String thisLine = "";
        int counter = 0;
        try {
            reader = new Scanner(Paths.get("C:\\Users\\13sam\\Desktop\\Bluejack-Game\\history.txt"));
            while (reader.hasNextLine()) {
                thisLine = reader.nextLine();
                String[] info = thisLine.split(",");
                Scores[counter][0] = info[0];
                Scores[counter][1] = info[1];
                Scores[counter][2] = info[2];
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }
        Scores[counter][0] = player.getName();
        Scores[counter][1] = Integer.toString(player.getScore());
        Scores[counter][2] = Integer.toString(computer.getScore());
        try {
            fileWriter = new FileWriter("history.txt", false);
            formatter = new Formatter(fileWriter);
            for (int i = 0; i < Scores.length; i++) {
                formatter.format("%s,%s\n", Scores[i][0], Scores[i][1]);
            }
            fileWriter.close();
        } catch (Exception e) {
            System.err.println("error");
        } finally {
            if (formatter != null) {
                formatter.close();
            }
        }
    }
    private void printHistoryFile() {
        history();
        System.out.println("Game history:");
        for (int i = 0; i < Scores.length; i++) {
            if (Scores[i][1] != null&&Scores[i][2] != null) {
                System.out.println(Scores[i][0] + ": " + Scores[i][1] + "Computer: " + Scores[i][2]);
            }
        }
    }
    private void startGame() {
        player = new Player();
        System.out.print("Please write your name or nickname: ");
        player.setName(sc.next());
        System.out.print("\n");
        computer = new Player();
        computer.setName("Computer");
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
    private void playSet(){
        System.out.println("Set " + setNo + "started!");
        setNo++;
        while(computer.get_isCont()||player.get_isCont()){
            if(player.get_isCont()){
                playForPlayer();
                if (player.getSumOfBoard() > 20) {
                    computer.incScore();
                    System.out.println(player.getName() + "busted!");
                    break;
                }
            }
            if(computer.get_isCont()) {
                playForComputer();
                if(computer.getSumOfBoard() == 20 || computer.isNoAction()){
                   computer.set_isCont(false);
                }else if (computer.getSumOfBoard() > 20) {
                    System.out.println("Computer busted!");
                    player.incScore();
                    break;
                }
            }
        }
        System.out.println("END OF SET " + setNo);
        System.out.println("Your score: " + player.getScore());
        System.out.println("Computer's score: " + computer.getScore());
    }
    private void gameLoop(){
        while(!(computer.getScore()==3||player.getScore()==3)){
            playSet();
        }
        if(computer.getScore()==3){
            System.out.println("END OF GAME\nWinner: Computer");
        }else if(player.getScore()==3){

            System.out.println("END OF GAME\nWinner: " + player.getName());
        }
        System.out.println("Do you want game history? [y]es [n]o\nIf you enter something other than \"y\" or \"n\", it will be printed automatically.");
        String isOkay = sc.next();
        if (isOkay=="n"){
            history();
        }else{
            printHistoryFile();
        }
    }
}