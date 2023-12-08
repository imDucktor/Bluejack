public class Card {
    final boolean MINUS = false;
    final boolean PLUS = true;
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

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public boolean getSign() {
        return sign;
    }

    public boolean isFlip() {
        return isFlip;
    }

    public boolean isDouble() {
        return isDouble;
    }
    public void setSign(boolean newSign){
        this.sign = newSign;
    }
}
