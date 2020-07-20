package tests;

import common.DeckValidation;
import io.restassured.response.Response;
import models.Deck;
import org.testng.annotations.*;


//@Listeners(config.TestListener.class) //uncomment to generate report only for this class file
public class Test_DrawCardNegativeCases extends BaseClass{

    String deck_id;
    Response response;
    DeckValidation deckValidation;

    /**
     * Common steps to be executed before each test
     * steps
     * 1. create a new deck and get deck_id
     * 2. initialize DeckValidation object
     **/
    @BeforeMethod
    public void getDeckId(){

        deck_id = deckActions.createDeck().then().extract().body().as(Deck.class).getDeck_id();
        deckValidation = new DeckValidation();
    }

    /**
     * Test to validate null count for draw card API
     * steps
     * 1. draw cards from the deck with card_count = null
     * 2. validate status of the draw card API response with 500
     **/
    @Test
    public void testNullCount(){

        response = deckActions.drawCards(deck_id, null);
        apiValidation.statusValidation(response, 500);
    }

    /**
     * Test to validate zero count for draw card API
     * steps
     * 1. draw cards from the deck with card_count = 0
     * 2. validate status of the draw card API response
     * 3. validate body of the draw card API response
     **/
    @Test
    public void testZeroCount(){

        response = deckActions.drawCards(deck_id, 0);
        apiValidation.statusValidation(response);
        deckValidation.validateCardsDrawn(response, 0, deck_id);
    }

    /**
     * Test to validate negative count for draw card API
     * steps
     * 1. draw cards from the deck with card_count < 0
     * 2. validate status of the draw card API response
     * 3. validate body of the draw card API response
     **/
    @Test
    public void testNegativeCount(){

        response = deckActions.drawCards(deck_id, -100);
        apiValidation.statusValidation(response);
        deckValidation.validateCardsDrawn(response, -100, deck_id);
    }

    /**
     * Test to validate exceeding count for draw card API
     * steps
     * 1. draw cards from the deck with card_count > 54
     * 2. validate status of the draw card API response
     * 3. validate body of the draw card API response
     **/
    @Test
    public void testExceedingCount(){

        response = deckActions.drawCards(deck_id, 100);
        apiValidation.statusValidation(response);
        deckValidation.validateCardsDrawn(response, 100, deck_id);
    }

    /**
     * Test to validate zero remaining deck for draw card API after drawing all the cards
     * steps
     * 1. draw cards from the deck with card_count = size of the deck
     * 2. validate status of the draw card API response
     * 3. remove drawn cards from the test deck
     * 4. draw cards from the same deck again with card_count > 0
     * 5. validate status of the draw card API response
     * 6. validate body of the draw card API response
     **/
    @Test
    public void testZeroRemainingDeck(){

        response = deckActions.drawCards(deck_id, 52);
        apiValidation.statusValidation(response);
        deckValidation.removeCardsFromTestDeck(52);
        response = deckActions.drawCards(deck_id, 10);
        apiValidation.statusValidation(response);
        deckValidation.validateCardsDrawn(response, 0, deck_id);
    }

}

