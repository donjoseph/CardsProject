package common;

import config.JokerStatus;
import models.Card;

import java.util.ArrayList;
import java.util.List;

public class DeckOfCards {

    private String[] suits = {"SPADES", "DIAMONDS", "CLUBS", "HEARTS"};
    private String[] ranks = {"ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"};
    private List<Card> testDeck;

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Constructors ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    public DeckOfCards(){
        generateTestDeck(JokerStatus.JOKER_STATUS_FALSE.getStatus());
    }

    public DeckOfCards(boolean joker_status){
        generateTestDeck(joker_status);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// methods /////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * method to generate a test deck to validate against
     * @param joker_status -  joker status to add jokers to the test deck
     * */
    public void generateTestDeck(boolean joker_status){

        testDeck = new ArrayList<Card>();

        for (int i = 0; i < suits.length; i++){

            for(int j = 0; j < ranks.length; j++) {

                String code;
                code = ranks[j].substring(0,1) + suits[i].substring(0,1);

                if(ranks[j].equalsIgnoreCase("10")){
                    code = 0 + suits[i].substring(0,1);
                }

                Card card = new Card(null,
                        ranks[j],
                        suits[i],
                        code,
                        null
                        );
                testDeck.add(card);
            }
        }

        if(joker_status)
            addJokersInTestDeck();
    }

    /**
     * method to add jokers to the test deck
     * */
    public void addJokersInTestDeck(){

        Card joker_black = new Card(null,
                "JOKER",
                "BLACK",
                "X1",
                null
        );

        Card joker_red = new Card(null,
                "JOKER",
                "RED",
                "X2",
                null
        );

        testDeck.add(joker_black);
        testDeck.add(joker_red);
    }

    /**
     * method to remove cards from test deck
     * @param noOfCards - no of cards to be removed from the top of the deck
     * */
    public void removeCardsFromTestDeck(int noOfCards){

        for(int i = 0; i < noOfCards; i++){
            testDeck.remove(0);
        }
    }

    /**
     * method to get test deck
     * @return test deck (List of cards)
     * */
    public List<Card> getTestDeck(){
       return testDeck;
    }
}
