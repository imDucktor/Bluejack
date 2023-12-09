public class Player {
    private Card[] hand = new Card[4];
    private Card[] board = new Card[9];
    int score =0;

    public Card[] getHand() {return hand;}
    public Card getCard(int index) {return hand[index-1];}
}

