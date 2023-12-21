
public class Player {
    private String name;
    private Card[] hand = new Card[4];
    private Card[] board = new Card[9];
    private int cardNumber = -1;
    private int score = 0;
    private boolean isCont = true;
    private boolean noAction = true;
    private Card LocationOfLastPlayedCard = null;

    public void setName(String name) { this.name = name;}
    public String getName() { return name;}
    public Card[] getHand() {return hand;}
    public int getCardNumber() {return cardNumber;}
    public int getAndIncreaseCardNumber() {
        cardNumber++;
        return cardNumber;
    }
    public Card removeCardWithIndexFromHand(int index) {
        Card temp = hand[index];
        Card[] tempHand = new Card[hand.length - 1];
        for (int i = 0, k = 0; i < hand.length; i++) {
            if (i != index) {
                tempHand[k] = hand[i];
                k++;
            }
        }
        hand = tempHand;
        return temp;
}
        public void writeHand(boolean isComp){
            if(isComp){
                for (int i=0; i<4; i++){
                    if(i==0) {System.out.print(name + "'s hand: ");}
                    if(this.hand[i].isUsed()){
                        System.out.print((i+1) + ") " + "X");
                    }else{
                        System.out.print((i+1) + ") " + "( )");
                    }
                    if(i!=3) {System.out.print(" | ");}
                    else {System.out.print("\n");}
                }
            }else {
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        System.out.print(name + "'s hand: ");
                    }
                    if(this.hand[i].isUsed()){
                        System.out.print((i+1) + ") " + "X");
                    }else{
                        System.out.print((i + 1) + ") " + this.hand[i].getCardInfo());
                    }
                    if (i != 3) {
                        System.out.print(" | ");
                    } else {
                        System.out.print("\n");
                    }
                }
            }
        }
        public void writeBoard(){

            for (int i=0; i<9; i++){
                if(i==0) {System.out.print(name + "'s board: ");}
                if(this.board[i]!= null){
                    System.out.print(this.board[i].getCardInfo());
                }
                if(i!=8&&this.board[i]!= null ) {System.out.print(" | ");}
                if(i==8){System.out.print("\n");}
            }
        }
        public int getSumOfBoard(){
            int sum = 0;
            for (int i=0; i<cardNumber+1; i++){
                sum=+board[i].getValue();
            }
            return sum;
        }
        public Card getCard(int index) {return hand[index-1];}
        public Card[] getBoard() {return board;}
        public void addToBoard(Card card, int index) {this.board[index] = card;}
        public void reset(){
            hand = new Card[4];
            board = new Card[9];
        }
        public void set_isCont(boolean isCont) {this.isCont=isCont;}
        public boolean get_isCont() {return isCont;}

    public Card getLocationOfLastPlayedCard() {return LocationOfLastPlayedCard;}
    public void setLocationOfLastPlayedCard(Card locationOfLastPlayedCard) {LocationOfLastPlayedCard = locationOfLastPlayedCard;}

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

    public void setNoAction(boolean noAction) {this.noAction = noAction;}
    public boolean isNoAction() {return noAction;}
}