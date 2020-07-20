package tests;

import common.DeckValidation;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(config.TestListener.class) //uncomment to generate report only for this class file
public class Test_NewDeckNegativeCases extends BaseClass {

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
     * Test to validate new deck API with jokers
     * steps
     * 1. create a new deck with joker_enabled = false using new deck API
     * 2. validate status of the new deck API response
     * 3. validate body of the new deck API response
     **/
    @Test
    public void test_newDeckWithJokerEnabledFalse(){

        response = deckActions.createDeckWithJoker(false);
        apiValidation.statusValidation(response);
        deckValidation.validateNewDeck(response, 52);
    }

    /**
     * Test to validate new deck API with jokers
     * steps
     * 1. create a new deck with joker_enabled = null using new deck API, here null is considered as false
     * 2. validate status of the new deck API response
     * 3. validate body of the new deck API response
     * **/
    @Test
    public void test_newDeckWithJokerEnabledNull(){

        response = deckActions.createDeckWithJoker(null);
        apiValidation.statusValidation(response);
        deckValidation.validateNewDeck(response, 52);
    }

}
