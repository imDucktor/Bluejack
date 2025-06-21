
/**
 * Represents a player in the Bluejack game.
 * Each player has a hand of cards, a board, and a score.
 */
public class Player {
    private String name = "";
    private final Card[] hand = new Card[4];
    private Card[] board = new Card[9];
    private int cardNumber = -1;
    private int score = 0;
    private boolean isCont = true;
    private boolean noAction = false;
    private Card LocationOfLastPlayedCard = null;

    /**
     * Sets the player's name.
     * 
     * @param name The player's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the player's name.
     * 
     * @return The player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand.
     * 
     * @return The player's hand as an array of Cards
     */
    public Card[] getHand() {
        return hand;
    }    /**
     * Gets the current card number.
     * 
     * @return The current card number
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * Increases the card number by one and returns the new value.
     * 
     * @return The increased card number
     */
    public int getAndIncreaseCardNumber() {
        cardNumber++;
        return cardNumber;
    }

    /**
     * Displays the player's hand to the console.
     * For computer player, cards are hidden.
     * 
     * @param isComp true if this is the computer's hand, false otherwise
     */
    public void writeHand(boolean isComp) {
        if (isComp) {
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    System.out.print(name + "'s hand: ");
                }
                if (this.hand[i].isUsed()) {
                    System.out.print((i + 1) + ") " + "X");
                } else {
                    System.out.print((i + 1) + ") " + "( )");
                }
                if (i != 3) {
                    System.out.print(" | ");
                } else {
                    System.out.println();
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    System.out.print(name + "'s hand: ");
                }
                if (this.hand[i].isUsed()) {
                    System.out.print((i + 1) + ") " + "X");
                } else {
                    System.out.print((i + 1) + ") " + this.hand[i].getCardInfo());
                }
                if (i != 3) {
                    System.out.print(" | ");
                } else {
                    System.out.println();
                }
            }
        }
    }    /**
     * Displays the player's board to the console.
     */
    public void writeBoard() {
        for (int i = 0; i < 9; i++) {
            if (i == 0) {
                System.out.print(name + "'s board: ");
            }
            if (this.board[i] != null) {
                System.out.print(this.board[i].getCardInfo());
            }
            if (i != 8 && this.board[i] != null) {
                System.out.print(" | ");
            }
            if (i == 8) {
                System.out.println();
            }
        }
    }

    /**
     * Calculates the sum of all cards on the board.
     * 
     * @return The sum of all card values on the board
     */
    public int getSumOfBoard() {
        int sum = 0;
        for (int i = 0; i < cardNumber + 1; i++) {
            sum += board[i].getValue();
        }
        return sum;
    }

    /**
     * Adds a card to the board at a specific index.
     * 
     * @param card The card to add
     * @param index The index on the board where the card will be placed
     */
    public void addToBoard(Card card, int index) {
        this.board[index] = card;
    }

    /**
     * Resets the player's board to a new empty board.
     */
    public void reset() {
        board = new Card[9];
        cardNumber = -1;
        isCont = true;
        noAction = true;
        LocationOfLastPlayedCard = null;
    }

    public void set_isCont(boolean isCont) {
        this.isCont = isCont;
    }

    public boolean get_isCont() {
        return isCont;
    }

    public Card getLocationOfLastPlayedCard() {
        return LocationOfLastPlayedCard;
    }

    public void setLocationOfLastPlayedCard(Card locationOfLastPlayedCard) {
        LocationOfLastPlayedCard = locationOfLastPlayedCard;
    }

    public int getScore() {
        return score;
    }

    public void incScore() {
        this.score += 1;
    }

    public void setNoAction(boolean noAction) {
        this.noAction = noAction;
    }

    public boolean isNoAction() {
        return noAction;
    }
}