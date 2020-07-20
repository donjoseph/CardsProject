package common;

import config.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.filters;

import tests.BaseClass;

public class DeckActions extends BaseClass {

    public static String BASE_URL = Constants.BASE_URL;
    public static String CREATE_DECK_ENDPOINT = BASE_URL + "/new/";
    private RequestSpecification requestSpecification;

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// Constructor /////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    public DeckActions() {

        filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// methods /////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * method to create a new deck with API
     * @return  Response of API call
     * */
    public Response createDeck() {

        return given(requestSpecification)
                .get(CREATE_DECK_ENDPOINT);
    }

    /**
     * method to create a deck having Jokers with API
     * @return  Response of API call
     * @param jocker_enabled*/
    public Response createDeckWithJoker(Boolean jocker_enabled) {

        return given(requestSpecification)
                .queryParam("jokers_enabled", String.valueOf(jocker_enabled))
                .get(CREATE_DECK_ENDPOINT);
    }

    /**
     * method to create a deck having Jokers with API POST
     * @return  Response of API call
     * @param jocker_enabled*/
    public Response createDeckWithJokerPost(Boolean jocker_enabled) {

        return given(requestSpecification)
                .queryParam("jokers_enabled", String.valueOf(jocker_enabled))
                .body("{}")
                .post(CREATE_DECK_ENDPOINT);
    }

    /**
     * method to draw cards from a deck with API
     * @param deck_id - the deck id from which cards need to be drawn
     * @param card_count - no of cards to be drawn from the deck
     * @return  Response of API call
     * */
    public Response drawCards(String deck_id, Integer card_count) {

        reportLog("******sending request to draw " + card_count + " cards from deck : " + deck_id + "*****");
        return given(requestSpecification)
                .queryParam("count", card_count)
                .get(BASE_URL + deck_id + "/draw/");
    }

    /**
     * generic method to call a get service
     * @param url - url of the service
     * @return  Response of API call
     * */
    public Response getService(String url) {

        reportLog("***********sending request to " + url + "***********");
        return given(requestSpecification)
                .get(url);
    }

}
