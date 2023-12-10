import javax.xml.namespace.QName;

public class Player {
    private String name;
    private Card[] hand = new Card[4];
    private Card[] board = new Card[9];
    int score =0;

    public void setName(String name) { this.name = name;}
    public String getName() { return name;}
    public Card[] getHand() {return hand;}
    public Card getCard(int index) {return hand[index-1];}
}

