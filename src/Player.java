public class Player {
    private Card[] hand = new Card[4];
    private Card[] board = new Card[9];
    int score =0;

    public Card[] getHand()
    {
        return hand;
    }
    public void setScore(int score)
    {
        this.score+=score;
    }
    public int getScore()
    {
        return score;
    }
}

