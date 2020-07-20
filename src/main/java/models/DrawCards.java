package models;

import java.util.ArrayList;
import java.util.List;

public class DrawCards {

    private boolean success = false;
    private String deck_id = null;
    private List<Card> cards = new ArrayList<Card>();
    private int remaining = 52;
    private String  error = null;

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Constructors ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public DrawCards() {}

    public DrawCards(boolean success,
                     String deck_id,
                     List<Card> cards,
                     int remaining,
                     String error) {
        this.success = success;
        this.deck_id = deck_id;
        this.cards = cards;
        this.remaining = remaining;
        this.error = error;
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Getters and Setters//////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public String getError() {
        return error;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public void setError(String error) {
        this.error = error;
    }
}
