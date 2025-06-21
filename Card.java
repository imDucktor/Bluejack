/**
 * Represents a card in the Bluejack game.
 * Each card has a value, color, and special properties.
 */
public class Card {
    private int value;
    private String color;
    private boolean sign;
    private boolean isFlip;
    private boolean isDouble;
    private boolean isUsed;

    /**
     * Constructor for creating a new card with specific properties.
     * 
     * @param value The numerical value of the card
     * @param color The color of the card (Red, Blue, Green, Yellow)
     * @param sign Whether the card has a sign modifier
     * @param isFlip Whether the card has flip functionality
     * @param isDouble Whether the card has double functionality
     * @param isUsed Whether the card has been used
     */
    public Card(int value, String color, boolean sign, boolean isFlip, boolean isDouble, boolean isUsed) {
        this.value = value;
        this.color = color;
        this.sign = sign;
        this.isFlip = isFlip;
        this.isDouble = isDouble;
        this.isUsed = isUsed;
    }

    /**
     * Copy constructor for creating a new card from an existing one.
     * 
     * @param newCard The card to copy
     */
    public Card(Card newCard) {
        this.value = newCard.getValue();
        this.color = newCard.getColor();
        this.sign = newCard.getSign();
        this.isFlip = newCard.isFlip();
        this.isDouble = newCard.isDouble();
    }    /**
     * Marks this card as used.
     */
    public void usedCard() {
        this.isUsed = true;
    }

    /**
     * Checks if the card has been used.
     * 
     * @return true if the card is used, false otherwise
     */
    public boolean isUsed() {
        return this.isUsed;
    }

    /**
     * Gets a string representation of the card.
     * 
     * @return A string representing the card's information
     */
    public String getCardInfo() {
        if (!isFlip && !isDouble) {
            if (this.value != 0) {
                return this.getColor() + " " + this.getValue();
            } else {
                return this.getColor();
            }
        } else if (isFlip) {
            return "-/+";
        } else {
            return "x2";
        }
    }    /**
     * Gets the value of the card, considering its sign.
     * 
     * @return The value of the card (negative if sign is true)
     */
    public int getValue() {
        if (sign) {
            return -1 * value;
        } else {
            return value;
        }
    }

    /**
     * Gets the color of the card.
     * 
     * @return The card's color
     */
    public String getColor() {
        return color;
    }

    /**
     * Checks if the card has a negative sign.
     * 
     * @return true if the card is negative, false otherwise
     */
    public boolean getSign() {
        return sign;
    }

    /**
     * Sets the sign of the card.
     * 
     * @param sign true for negative, false for positive
     */
    public void setSign(boolean sign) {
        this.sign = sign;
    }

    /**
     * Checks if the card has flip functionality.
     * 
     * @return true if the card can flip, false otherwise
     */
    public boolean isFlip() {
        return isFlip;
    }

    /**
     * Checks if the card has double functionality.
     * 
     * @return true if the card can double, false otherwise
     */
    public boolean isDouble() {
        return isDouble;
    }

    /**
     * Doubles the value of the card.
     */
    public void doubleValue() {
        this.value *= 2;
    }

    /**
     * Flips the sign of the card's value.
     */
    public void flipValue() {
        this.value *= -1;
    }
}
