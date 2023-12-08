//imports
import java.util.Random;
public class Game{
    String[] cardColors = {"Red", "Blue", "Yellow", "Green"};
    Random random = new Random();
    Card[] gameDeck = new Card[40];
    Card[] playerDeck = new Card[10];
    Card[] computerDeck = new Card[10];
    private Card[] remove(Card[] deck, int index){

        Card[] newDeck = new Card[deck.length-1];
        for(int i=0, k=0;i<deck.length;i++) {
            if (i != index) {
                newDeck[k] = deck[i];
                k++;
            }
        }
        deck = newDeck;
    }

    public Card RemoveCard() {
        Card tempValue = deck[0];
        Card[] newDeck = new Card[deck.length - 1];
        for (int i = 1; i < deck.length; i++) {
            newDeck[i - 1] = deck[i];
        }
        deck = newDeck;
        return temp;
    }
    public static Card[] shuffle(Card[] Deck) {
        for (int i = Deck.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Card tempValue = Deck[index];
            Deck[index] = Deck[i];
            Deck[i] = tempValue;
        }
        return Deck;
    }
    private void initializeDecks() {
        for (int i = 1; i <= 10; i++) {
            for (String color : cardColors) {
                gameDeck.add(new Card(i, color, PLUS, false, false));
            }
        }

        shuffle(gameDeck);

        // Fill player and computer decks with 10 cards each
        for (int i = 0; i < 5; i++) {
            computerDeck[i] = gameDeck[i];
            playerDeck[i] = gameDeck[39 - i];
        }

        // Add three cards with random colors and values between 1 to 6, with an additional sign
        for (int i = 5; i < 8; i++) {
            int value;
            String color;
            do {
                index = random.nextInt(40);
                value = gameDeck[index].getValue();
                color = gameDeck[index].getColor();
            } while (value >= 6);
            boolean sign = random.nextBoolean();
            computerDeck[index] = new Card(value, color, sign, false, false);

            do {
                index = random.nextInt(40);
                value = gameDeck[index].getValue();
                color = gameDeck[index].getColor();
            } while (value >= 6);
            sign = random.nextBoolean();
            playerDeck[index] = new Card(value, color, sign, false, false);
        }

        // Add the remaining two cards
        if (random.nextInt(5) == 0) {
            Card card1 = new Card(0, null, false, true, false);
            Card card2 = new Card(0, null, false, false, true);
            gameDeck.add(card1);
            gameDeck.add(card2);
        } else {
            String color = cardColors[random.nextInt(cardColors.length)];
            int value = random.nextInt(6) + 1;
            boolean isSigned = random.nextBoolean();
            gameDeck.add(new Card(value, color, isSigned, false, false));
            gameDeck.add(new Card(value, color, isSigned, false, false));
        }
    }