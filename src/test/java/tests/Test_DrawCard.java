package tests;

import common.DeckValidation;
import io.restassured.response.Response;
import models.Deck;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;

//@Listeners(config.TestListener.class) //uncomment to generate report only for this class file
public class Test_DrawCard extends BaseClass{

    String deck_id;
    Response response;
    DeckValidation deckValidation;


    /**
     * Test to validate draw card API with happy_paths
     * steps
     * 1. create a new deck and get deck_id
     * 2. draw cards from the deck with parameterized card_count
     * 3. validate status of the draw card API response
     * 4. validate body of the draw card API response
     **/
    @Test(dataProvider = "draw_card_happypaths")
    public void test_drawCard(int card_count){

        reportLog("executing for card_count : " + card_count);
        deck_id = deckActions.createDeck().then().extract().body().as(Deck.class).getDeck_id();
        response = deckActions.drawCards(deck_id, card_count);
        apiValidation.statusValidation(response);

        deckValidation = new DeckValidation();
        deckValidation.validateCardsDrawn(response, card_count, deck_id);
        reportLog("execution completed for card_count : " + card_count);
    }

    /**
     * Test to validate draw card API with jokers
     * steps
     * 1. create a new deck with jokers and get deck_id
     * 2. draw cards from the deck with parameterized card_count
     * 3. validate status of the draw card API response
     * 4. validate body of the draw card API response
     **/
    @Test(dataProvider = "draw_card_with_joker")
    public void test_drawCardWithJoker(int card_count){

        reportLog("executing for card_count : " + card_count);
        deck_id = deckActions.createDeckWithJoker(true).then().extract().body().as(Deck.class).getDeck_id();
        response = deckActions.drawCards(deck_id, card_count);
        apiValidation.statusValidation(response);

        deckValidation = new DeckValidation(true);
        deckValidation.validateCardsDrawn(response, card_count, deck_id);
        reportLog("execution completed for card_count : " + card_count);
    }

    /**
     * Data Provider for test_drawCard
     **/
    @DataProvider(name = "draw_card_happypaths")
    private Object[][] getCardCount() {

        ArrayList<Object []> obj = new ArrayList<>();
        for(int i = 1; i <= 52; i++) {
            obj.add(new Object[] {i});
        }
        return obj.toArray(new Object[0][]);
    }

    /**
     * Data Provider for test_drawCardWithJoker
     **/
    @DataProvider(name = "draw_card_with_joker")
    private Object[][] getCardCountWithJoker() {

        ArrayList<Object []> obj = new ArrayList<>();
        for(int i = 1; i <= 54; i++) {
            obj.add(new Object[] {i});
        }
        return obj.toArray(new Object[0][]);
    }

}
