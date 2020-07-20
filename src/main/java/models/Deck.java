package models;

public class Deck {

    private boolean success = false;
    private String deck_id = null;
    private boolean shuffled = false;
    private int remaining = 52;

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Constructors ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public Deck(boolean success,
                String deck_id,
                boolean shuffled,
                int remaining) {
        this.success = success;
        this.deck_id = deck_id;
        this.remaining = remaining;
        this.shuffled = shuffled;
    }

    public Deck(){}

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Getters and Setters//////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public boolean getSuccess() {
        return success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
