import java.util.Arrays;
import java.util.Random;

public class Game {
    String[] cardColors = {"Red", "Blue", "Yellow", "Green"};
    Random random = new Random();
    Card[] gameDeck = new Card[40];
    Card[] playerDeck = new Card[10];
    Card[] computerDeck = new Card[10];

    public Game(){
        initDecks();
//        System.out.println(Arrays.toString(computerDeck));
        for (int i=0;i<=39;i++) {
            System.out.println(gameDeck[i].getCardInfo());
        }
        System.out.println("########");
        for (int i=0;i<=9;i++) {
            System.out.println(playerDeck[i].getCardInfo());
        }
        System.out.println("########");
        for (int i=0;i<=9;i++) {
            System.out.println(computerDeck[i].getCardInfo());
        }
//        System.out.println(Arrays.toString(playerDeck));
//        System.out.println(Arrays.toString(gameDeck));

    }

    private void arrayCopy(Card[] deck,Card[] newDeck){
        for(int i=0; i<deck.length;i++){
            if(i<newDeck.length) {deck[i] = newDeck[i];} else {deck[i].freeCard();}
        }
    }

    private void removeCard(Card[] deck, int index) {
        Card[] newDeck = new Card[deck.length - 1];
        for (int i = 0, k = 0; i < deck.length; i++) {
            if (i != index) {
                newDeck[k] = deck[i];
                k++;
            }
        }
        arrayCopy(gameDeck, newDeck);
    }
    private Card useAndRemoveCard(Card[] deck) {
        Card tempValue = deck[0];
        Card[] newDeck = new Card[deck.length - 1];
        for(int i = 0; i < deck.length-1; i++) {newDeck[i] = deck[i+1];}
        arrayCopy(gameDeck, newDeck);
        return tempValue;
    }
    private void create_gameDeck() {
        for (int i = 1, k = 0; i <= 10; i++) {
            for (String color : cardColors) {
                gameDeck[k] = new Card(i, color, false, false, false);
                k++;
            }
        }

    }
    private void shuffle(Card[] deck) {
        for (int i = 0; i < 150; i++) {
            int f = random.nextInt(deck.length);
            int k = random.nextInt(deck.length);
            Card temp = deck[k];
            deck[k] = deck[f];
            deck[f] = temp;
        }
    }
    private void createFirstFiveCards() {
        for (int i = 0; i < 5; i++) {
            computerDeck[i] = useAndRemoveCard(gameDeck);
            playerDeck[i] = gameDeck[gameDeck.length - 1];
            removeCard(gameDeck, gameDeck.length - 1);
        }
    }
    private void createThreeCards(Card[] deck) {
        for (int i = 5; i < 8; i++) {
            int value, index;
            String color;
            do {
                index = random.nextInt(deck.length-i);
                value = gameDeck[index].getValue();
                color = gameDeck[index].getColor();
            } while (value > 6);
            removeCard(gameDeck, index);
            boolean sign = random.nextBoolean();
            deck[i] = new Card(value, color, sign, false, false);
        }
    }
    private void createLastTwoCard(Card[] deck) {
        if (random.nextInt(5) == 0) {
            Card card = new Card(0, null, false, true, false);
            deck[8] = card;
        }else {
            boolean sign = random.nextBoolean();
            deck[8] = useAndRemoveCard(gameDeck);
            deck[8].setSign(sign);
        }
        if (random.nextInt(5) == 0) {
            Card card = new Card(0, null, false, false, true);
            deck[9] = card;
        }else {
            boolean sign = random.nextBoolean();
            deck[9] = useAndRemoveCard(gameDeck);
            deck[9].setSign(sign);
        }
    }
    public void initDecks(){
        for (int i = 1, k =0; i <= 10; i++) {
            for (String color : cardColors) {
                gameDeck[k] = new Card(i, color, false, false, false);
                k++;
            }
        }
        create_gameDeck();
        shuffle(gameDeck);
        createFirstFiveCards();
        createThreeCards(computerDeck);
        createThreeCards(playerDeck);
        createLastTwoCard(computerDeck);
        createLastTwoCard(playerDeck);
    }
}