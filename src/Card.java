public class Card {
    private int value;
    private String color;
    private boolean sign;
    private boolean isFlip;
    private boolean isDouble;
    private boolean isUsed;

    public Card(int value, String color, boolean sign, boolean isFlip, boolean isDouble,boolean isUsed) {
        this.value = value;
        this.color = color;
        this.sign = sign;
        this.isFlip = isFlip;
        this.isDouble = isDouble;
        this.isUsed = isUsed;
    }

    public void setCard(int value, String color, boolean sign, boolean isFlip, boolean isDouble, boolean isUsed){
        this.value = value;
        this.color = color;
        this.sign = sign;
        this.isFlip = isFlip;
        this.isDouble = isDouble;
        this.isUsed = isUsed;
    }

    public Card(Card newCard) {
        this.value = newCard.getValue();;
        this.color = newCard.getColor();
        this.sign = newCard.getSign();
        this.isFlip = newCard.isFlip();
        this.isDouble = newCard.isDouble();
    }

    public void usedCard(){
        this.isUsed = true;
    }

    public boolean isUsed(){return this.isUsed;}

    public String getCardInfo(){
        if(!isFlip && !isDouble) {
            if(this.value!=0) {return this.getColor() + " " + this.getValue();}
            else {return this.getColor();}
        }else if(isFlip){return "-/+";}
        else{return "x2";}
    }

    public int getValue() {
        if(sign){ return -1*value;}
        else {return value;}
    }
    public void doubleValue(){
        this.value *= 2;
    }

    public void flipValue(){
        this.value *= -1;
    }

    public String getColor() {return color;}

    public boolean getSign() {return sign;}
    public void setSign(boolean sign) {this.sign = sign;}

    public boolean isFlip() {return isFlip;}

    public boolean isDouble() {return isDouble;}
}
