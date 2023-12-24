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
    private Card[] extraDeck;
    private Player player;
    private Player computer;
    private Scanner sc = new Scanner(System.in);
    private int setNo = 1;
    private String[][] Scores;

    private void create_mainDeck() {
        String[] cardColors = new String[]{"Red", "Blue", "Green", "Yellow"};
        final int TOTAL_NUMBER_OF_CARDS = 40;
        mainDeck = new Card[TOTAL_NUMBER_OF_CARDS];

        for (int i = 1, k = 0; i <= 10; i++) {
            for (String color : cardColors) {
                mainDeck[k] = new Card(i, color, false, false, false, false);
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
        boolean isExtra = false;
        if (mainDeck.length == 1) {
            createExtraDeck();
            isExtra = true;
        }
        Card temp = mainDeck[0];
        if (isExtra) {
            shuffle_mainDeck();
        }
        Card[] tempDeck = new Card[mainDeck.length - 1];
        for (int i = 1; i < mainDeck.length; i++) {
            tempDeck[i - 1] = mainDeck[i];
        }
        mainDeck = tempDeck;
        return temp;
    }

    private void createExtraDeck() {
        int counter = 1;
        for (int i = 4; i < playerDeck.length; i++) {
            mainDeck[counter] = playerDeck[i];
        }
        for (int i = 4; i < computerDeck.length; i++) {
            mainDeck[counter] = computerDeck[i];
        }
        for (int i = 0; i < extraDeck.length; i++) {
            mainDeck[counter] = extraDeck[i];
        }
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
            Card tempPlayerCard = new Card(removeCardWithIndexFromMain(mainDeck.length - i - 1));
            playerDeck[i] = tempPlayerCard;
        }
    }

    private void createThreeCards(Card[] deck) {
        for (int i = 5; i < 8; i++) {
            int value, k = 0;
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
            Card card = new Card(0, "null", false, true, false, false);
            deck[8] = card;
        } else {
            int value, k = 0;
            String color;
            do {
                value = mainDeck[k].getValue();
                color = mainDeck[k].getColor();
                k++;
            } while (value > 6);
            Card cardEight = new Card(removeCardWithIndexFromMain(k));
            boolean sign = random.nextBoolean();
            deck[8] = cardEight;
            deck[8].setSign(sign);
        }
        if (random.nextInt(5) == 0) {
            Card card = new Card(0, "null", false, false, true, false);
            deck[9] = card;
        } else {
            int value, k = 0;
            String color;
            do {
                value = mainDeck[k].getValue();
                color = mainDeck[k].getColor();
                k++;
            } while (value > 6);
            Card cardNine = new Card(removeCardWithIndexFromMain(k));
            boolean sign = random.nextBoolean();
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
        for (int i = 0; i < 4; i++) {
            Card tempCard = useCardFromPlayerDeck();
            player.getHand()[i] = tempCard;
        }
        for (int i = 0; i < 50; i++) {
            int m = random.nextInt(computerDeck.length - 1);
            int n = random.nextInt(computerDeck.length - 1);
            Card temp = computerDeck[n];
            computerDeck[n] = computerDeck[m];
            computerDeck[m] = temp;
        }
        for (int i = 0; i < 4; i++) {
            Card tempCard = useCardFromComputerDeck();
            computer.getHand()[i] = tempCard;
        }
    }

    private void writeStatus() {
        System.out.println("———————————————————————————————————");
        computer.writeHand(true);
        computer.writeBoard();
        System.out.println("———————————————————————————————————");
        player.writeBoard();
        player.writeHand(false);
        System.out.println("———————————————————————————————————");
    }

    private void playCardFromPlayerHand() {
        int action = 0;
        while (true) {
            System.out.println("Choose your card to play:");
            player.writeHand(false);
            try {
                action = Integer.parseInt(sc.nextLine());
                if (action >= 1 && action <= 4 && !player.getHand()[action - 1].isUsed()) {
                    break;
                } else if (player.getHand()[action - 1].isUsed()) {
                    System.out.println("Please choose a not used card.");
                } else {
                    System.out.println("Please choose a card.");
                }
            } catch (Exception e) {
                System.out.println("Please choose a card.");
                continue;
            }
        }
        if (player.getHand()[action - 1].isFlip()) {
            player.getLocationOfLastPlayedCard().flipValue();
            player.addToBoard(player.getHand()[action - 1], player.getAndIncreaseCardNumber());
            player.getHand()[action - 1].usedCard();
        } else if (player.getHand()[action - 1].isDouble()) {
            player.getLocationOfLastPlayedCard().doubleValue();
            player.addToBoard(player.getHand()[action - 1], player.getAndIncreaseCardNumber());
            player.getHand()[action - 1].usedCard();
        } else {
            player.addToBoard(player.getHand()[action - 1], player.getAndIncreaseCardNumber());
            player.setLocationOfLastPlayedCard(player.getHand()[action - 1]);
            player.getHand()[action - 1].usedCard();
        }
        writeStatus();
    }

    private void playForPlayer() {
        writeStatus();
        System.out.println("\n" + player.getName() + "'s turn\n");
        if (player.getCardNumber() < 8) {
            int actionForCard;
            while (true) {
                try {
                    System.out.println("Would you like a new card to be placed on the board?\n1. Yes\n2. No");
                    actionForCard = Integer.parseInt(sc.nextLine());
                    if (actionForCard >= 1 && actionForCard <= 2) {
                        break;
                    } else {
                        System.out.println("Please choose an action.");
                    }
                } catch (Exception e) {
                    System.out.println("Please choose an action.");
                    continue;
                }

            }
            if (actionForCard == 1) {
                player.addToBoard(useCardFromMain(), player.getAndIncreaseCardNumber());
            }
        } else {
            System.out.println("You can't request more cards.");
        }

        writeStatus();

        int action = 0;
        while (true) {
            System.out.println("Choose your action:\n1. Play\n2. End\n3. Stand");
            try {
                action = Integer.parseInt(sc.nextLine());
                if (action >= 1 && action <= 3) {
                    break;
                } else {
                    System.out.println("Please choose an action.");
                }
            } catch (Exception e) {
                System.out.println("Please choose an action.");
                continue;
            }
        }
        switch (action) {
            case 1:
                System.out.println(player.getName() + " is playing...");
                playCardFromPlayerHand();
                break;
            case 2:
                System.out.println("End of this turn for " + player.getName() + ".");
                break;
            case 3:
                System.out.println(player.getName() + " chose to stand.");
                player.set_isCont(false);
                break;
        }
    }

    private void playForComputer() {
        System.out.println("Computer's turn");
        writeStatus();
        computer.setNoAction(true);
        int sum = computer.getSumOfBoard();
        if (sum <= 15) {
            computer.addToBoard(useCardFromMain(), computer.getAndIncreaseCardNumber());
            computer.setNoAction(false);
        }
        sum = computer.getSumOfBoard();
        int max = sum;
        int maxIndex = -1;
        if (sum > 14) {
            for (int i = 0; i <= 3; i++) {
                if (computer.getHand()[i].isUsed() == true) {
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
        System.out.println("Computer played.");
    }

    private void playerCount() {
        Scanner reader = null;
        String thisLine = "";
        int playerCount = 0;
        try {
            reader = new Scanner(Paths.get("C:\\Users\\13sam\\Desktop\\Bluejack-Game\\history.txt"));
            while (reader.hasNextLine()) {
                playerCount++;
                reader.nextLine();
            }
            if (playerCount >= 10) {
                playerCount = 10;
                Scores = new String[playerCount][3];
            } else {
                Scores = new String[playerCount + 1][3];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    private void history() {
        playerCount();
        Formatter formatter = null;
        FileWriter fileWriter = null;
        Scanner reader = null;
        String thisLine = "";
        int counter = 0;
        try {
            reader = new Scanner(Paths.get("C:\\Users\\13sam\\Desktop\\Bluejack-Game\\history.txt"));
            while (reader.hasNextLine()) {

                thisLine = reader.nextLine();
                String[] temp = thisLine.split(",");
                Scores[counter][0] = temp[0];
                Scores[counter][1] = temp[1];
                Scores[counter][2] = temp[2];
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }
        if (Scores.length == 10) {
            String[][] tempScores = new String[Scores.length][3];
            for (int i = 1; i < Scores.length; i++) {
                for (int j = 0; j < 3; j++) {
                    tempScores[i - 1][j] = Scores[i][j];
                }
            }
            Scores = tempScores;
            counter = 9;
        }
        Scores[counter][0] = player.getName();
        Scores[counter][1] = Integer.toString(player.getScore());
        Scores[counter][2] = Integer.toString(computer.getScore());
        try {
            fileWriter = new FileWriter("history.txt", false);
            formatter = new Formatter(fileWriter);
            for (int i = 0; i < Scores.length; i++) {
                formatter.format("%s,%s,%s\n", Scores[i][0], Scores[i][1], Scores[i][2]);
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
            if (!(Scores[i][0] == null)) {
                System.out.println(Scores[i][0] + ": " + Scores[i][1] + " | Computer: " + Scores[i][2]);
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
        extraDeck = mainDeck;
    }

    private void playSet() {
        boolean isBusted = false;
        System.out.println("Press any key to start.");
        sc.next();
        System.out.println("\nSet " + setNo + " started!\n");
        while (computer.get_isCont() || player.get_isCont()) {
            if (player.get_isCont()) {
                playForPlayer();
                if (player.getSumOfBoard() > 20) {
                    computer.incScore();
                    isBusted = true;
                    System.out.println("\n" + player.getName() + "busted!");
                    break;
                }
            }
            if (computer.get_isCont()) {
                playForComputer();
                if (computer.getSumOfBoard() == 20 || computer.isNoAction()) {
                    computer.set_isCont(false);
                } else if (computer.getSumOfBoard() > 20) {
                    System.out.println("\nComputer busted!");
                    isBusted = true;
                    player.incScore();
                    break;
                }
            }
        }
        if (!isBusted) {
            if (player.getSumOfBoard() > computer.getSumOfBoard()) {
                player.incScore();
                System.out.println("\n" + player.getName() + " won this set");
            } else if (player.getSumOfBoard() < computer.getSumOfBoard()) {
                computer.incScore();
                System.out.println("\nComputer won this set");
            } else if (player.getSumOfBoard() == computer.getSumOfBoard()) {
                System.out.println("\nDraw. Nobody won this set.");
            }
        }
        writeStatus();
        System.out.println("\nEND OF SET " + setNo);
        System.out.println("\nYour score: " + player.getScore());
        System.out.println("Computer's score: " + computer.getScore() + "\n");
        setNo++;
        player.reset();
        computer.reset();
    }

    public void gameLoop() {
        startGame();
        while (!(computer.getScore() == 3 || player.getScore() == 3)) {
            playSet();
        }
        if (computer.getScore() == 3) {
            System.out.println("END OF GAME\nWinner: Computer");
        } else if (player.getScore() == 3) {

            System.out.println("END OF GAME\nWinner: " + player.getName());
        }
        System.out.println("Do you want game history? [y]es [n]o\nIf you enter something other than \"y\" or \"n\", it will be printed automatically.");
        String isOkay = sc.next();
        if (isOkay == "n") {
            history();
        } else {
            printHistoryFile();
        }
    }
}