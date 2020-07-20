package common;

import io.restassured.response.Response;
import models.Card;
import models.Deck;
import models.DrawCards;
import org.testng.Assert;
import tests.BaseClass;

import java.util.List;

public class DeckValidation extends BaseClass {

    DeckOfCards deckOfCards;
    List<Card> expectedCards;
    List<Card> actualCards;
    Deck deck;
    DrawCards drawCards;
    DeckActions deckActions;
    ApiValidation apiValidation;

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Constructors ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    public DeckValidation(){
        deckOfCards = new DeckOfCards();
    }

    public DeckValidation(boolean joker_status){
        if(joker_status)
            deckOfCards = new DeckOfCards(joker_status);
        else
            deckOfCards = new DeckOfCards();
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// methods /////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * method to validate the list of cards against test deck
     * @param cards - list of cards to be validated
     * @param expected_cards_count - expected no of cards in list of cards
     * */
    public void validateCards(List<Card> cards, int expected_cards_count){

        actualCards = cards;
        expectedCards = deckOfCards.getTestDeck();
        reportLog("******Validation started for cards*****");
        Assert.assertEquals(actualCards.size(), expected_cards_count, "Validation of no of cards drawn");

        for(int i = 0; i < actualCards.size(); i++){
            Card actualCard = actualCards.get(i);
            Card expectedCard = expectedCards.get(i);
            Assert.assertEquals(actualCard.getCode(),
                    expectedCard.getCode(),
                    "Validation of card code");
            Assert.assertEquals(actualCard.getSuit(),
                    expectedCard.getSuit(),
                    "Validation of card suit");
            Assert.assertEquals(actualCard.getValue(),
                    expectedCard.getValue(),
                    "Validation of card value");

            /*commented now as the image validations slows the execution
            deckActions = new DeckActions();
            apiValidation = new ApiValidation();
            validateImage(actualCard.getImage());
            validateImage(actualCard.getImages().getPng());
            validateImage(actualCard.getImages().getSvg());
             */
        }
        reportLog("******Validation finished for cards*****");
    }

    /**
     * method to validate the response body of new deck API
     * @param rs - RestAssured response of the new deck API
     * @param expected_deck_size - expected size of the new deck
     **/
    public void validateNewDeck(Response rs, int expected_deck_size){

        deck = rs.then().extract().body().as(Deck.class);
        reportLog("******Validation started for deck*****");
        Assert.assertEquals(deck.getSuccess(), true, "Validation of success");
        Assert.assertEquals(deck.getRemaining(), expected_deck_size, "Validation of remaining cards");
        Assert.assertTrue(deck.getDeck_id().isBlank() == false, "Checking deck id is present");
        Assert.assertTrue(deck.isShuffled() == false, "Checking deck is not shuffled");
        reportLog("******Validation finished for deck*****");

    }

    /**
     * method to remove cards from test deck
     * @param noOfCards - no of cards to be removed from the top of test deck
     **/
    public void removeCardsFromTestDeck(int noOfCards){

        if(noOfCards <= deckOfCards.getTestDeck().size())
            deckOfCards.removeCardsFromTestDeck(noOfCards);
        else if (noOfCards > 0)
            deckOfCards.removeCardsFromTestDeck(deckOfCards.getTestDeck().size());
        else
            reportLog("end of deck");
    }

    /**
     * method to validate response body of draw card API
     * @param rs - RestAssured response of the draw card API
     * @param card_count - no of cards drawn from the deck
     * @param expected_deck_id - deck id of the deck from which cards are drawn
     **/
    public void validateCardsDrawn(Response rs, Integer card_count, String expected_deck_id) {

        drawCards = rs.then().extract().body().as(DrawCards.class);
        int testDeckCount = deckOfCards.getTestDeck().size();
        reportLog("******Validation started for drawing " + card_count + " cards from deck " + expected_deck_id + "*****");

        if(card_count <= testDeckCount || card_count <= 0)
            {
            int expectedRemaining;
            int expectedCardCount;
            if(card_count > 0) {

                Assert.assertEquals(drawCards.isSuccess(), true, "Validation of success");
                expectedRemaining = testDeckCount - card_count;
                expectedCardCount = card_count;
            }
            else {

                if(testDeckCount > 0)
                    Assert.assertEquals(drawCards.isSuccess(), true, "Validation of success");
                else
                    Assert.assertEquals(drawCards.isSuccess(), false, "Validation of success");

                expectedRemaining = testDeckCount;
                expectedCardCount = 0;
            }
            Assert.assertEquals(drawCards.getRemaining(), expectedRemaining,
                    "Validation of remaining cards");
            validateCards(drawCards.getCards(), expectedCardCount);
            }
        else
            {
            Assert.assertEquals(drawCards.isSuccess(), false, "Validation of success");
            Assert.assertEquals(drawCards.getError().toString(),
                    "Not enough cards remaining to draw " +card_count+" additional",
                    "Validation of error message");
            Assert.assertEquals(drawCards.getRemaining(), 0,
                    "Validation of remaining cards");
            validateCards(drawCards.getCards(), testDeckCount);
            }

        Assert.assertEquals(drawCards.getDeck_id(), expected_deck_id, "Checking deck id is present");
        reportLog("******Validation completed for drawing " + card_count + "cards from deck " + expected_deck_id + "*****");
    }

    public void validateImage(String imageUrl){
        reportLog("***********validating image url : " +imageUrl + "*************");
        Response rs = deckActions.getService(imageUrl);
        apiValidation.statusValidation(rs);
    }
}
