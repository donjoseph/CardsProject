package tests;

import common.DeckValidation;
import io.restassured.response.Response;
import models.Deck;
import org.testng.annotations.*;

import java.util.ArrayList;

//@Listeners(config.TestListener.class) //uncomment to generate report only for this class file
public class Test_DrawCardSameDeck extends BaseClass{

    String deck_id;
    Response response;
    DeckValidation deckValidation;

    /**
     * steps to be executed before the class
     * steps
     * 1. create a new deck and get deck_id
     * 2. initialize DeckValidation object
     **/
    @BeforeClass
    public void getDeckId(){

        deck_id = deckActions.createDeck()
                .then()
                .extract()
                .body()
                .as(Deck.class)
                .getDeck_id();

        deckValidation = new DeckValidation();
    }

    /**
     * Test to validate draw card API for consecutive draws of cards from same deck
     * steps
     * 1. draw cards from the deck with parameterized card_count
     * 2. validate status of the draw card API response
     * 3. validate body of the draw card API response
     * 4. remove drawn cards from the test deck to validate next draw
     * **/
    @Test(dataProvider = "draw_card_sameDeck")
    public void test_darwCardSameDeck(int card_count){

        reportLog("executing for card_count : " + card_count);
        response = deckActions.drawCards(deck_id, card_count);
        apiValidation.statusValidation(response);
        deckValidation.validateCardsDrawn(response, card_count, deck_id);
        deckValidation.removeCardsFromTestDeck(card_count);
        reportLog("execution completed for card_count : " + card_count);
    }

    /**
     * Data Provider for test_darwCardSameDeck
     **/
    @DataProvider(name = "draw_card_sameDeck")
    private Object[][] getCardCount() {

        ArrayList<Object []> obj = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            obj.add(new Object[] {i});
        }
        return obj.toArray(new Object[0][]);
    }

}
