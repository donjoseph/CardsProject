package tests;

import common.DeckValidation;
import config.DeckSize;
import config.JokerStatus;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(config.TestListener.class) //uncomment to generate report only for this class file
public class Test_NewDeck extends BaseClass {

    Response response;
    DeckValidation deckValidation;

    /**
     * Common steps to be executed before each test
     * steps
     * 1. initialize DeckValidation object
     **/
    @BeforeMethod
    public void init(){
        deckValidation = new DeckValidation();
    }

    /**
     * Test to validate new deck API
     * steps
     * 1. create a new deck with new deck API
     * 2. validate status of the new deck API response
     * 3. validate body of the new deck API response
     * **/
    @Test
    public void test_newDeck(){

        response = deckActions.createDeck();
        apiValidation.statusValidation(response);
        deckValidation.validateNewDeck(response, DeckSize.REGULAR_DECK_SIZE.getSize());
    }

    /**
     * Test to validate new deck API with jokers
     * steps
     * 1. create a new deck with jokers using new deck API
     * 2. validate status of the new deck API response
     * 3. validate body of the new deck API response
     * **/
    @Test
    public void test_newDeckWithJoker(){

        response = deckActions.createDeckWithJoker(JokerStatus.JOKER_STATUS_TRUE.getStatus());
        apiValidation.statusValidation(response);
        deckValidation.validateNewDeck(response, DeckSize.JOKER_DECK_SIZE.getSize());
    }

    /**
     * Test to validate new deck API with jokers
     * steps
     * 1. create a new deck with jokers using new deck API Post
     * 2. validate status of the new deck API response
     * 3. validate body of the new deck API response
     * **/
    //This test case is failing because POST is returning 403.
    @Test
    public void test_newDeckWithJokerPost(){

        response = deckActions.createDeckWithJokerPost(JokerStatus.JOKER_STATUS_TRUE.getStatus());
        apiValidation.statusValidation(response);
        deckValidation.validateNewDeck(response, DeckSize.JOKER_DECK_SIZE.getSize());
    }

}
