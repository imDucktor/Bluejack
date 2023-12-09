public class Card {
    private int value;
    private String color;
    private boolean sign;
    private boolean isFlip;
    private boolean isDouble;

    public Card(int value, String color, boolean sign, boolean isFlip, boolean isDouble) {
        this.value = value;
        this.color = color;
        this.sign = sign;
        this.isFlip = isFlip;
        this.isDouble = isDouble;
    }

    public void setCard(int value, String color, boolean sign, boolean isFlip, boolean isDouble) {
        this.value = value;
        this.color = color;
        this.sign = sign;
        this.isFlip = isFlip;
        this.isDouble = isDouble;
    }

    public void freeCard(){
        this.value = 0;
        this.color = null;
        this.sign = false;
        this.isFlip = false;
        this.isDouble = false;
    }

    public String getCardInfo(){
        if(!isFlip && !isDouble) {
            return this.getColor() + " " + this.getValue();
        }else if(isFlip){
            return "-/+";
        }else{
            return "x2";
        }
    }

    public int getValue() {
        if(sign){ return -1*value;}
        else {return value;}
    }
    public void doubleValue(){
        this.value *= 2;
    }

    public String getColor() {return color;}

    public boolean getSign() {return sign;}
    public void setSign(boolean sign) {this.sign = sign;}

    public boolean isFlip() {return isFlip;}

    public boolean isDouble() {return isDouble;}
}
