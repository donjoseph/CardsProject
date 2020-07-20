package common;

import config.ApiStatus;
import io.restassured.response.Response;
import org.testng.Assert;
import tests.BaseClass;

public class ApiValidation extends BaseClass {

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// methods /////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * method to validate response status code is 200
     * @param rs - RestAssured response
     * */
    public void statusValidation(Response rs){

        reportLog("Expected status code: " + ApiStatus.SUCCESS.getStatus());
        reportLog("Actual status code: " + rs.getStatusCode());
        Assert.assertEquals(rs.getStatusCode(),
                ApiStatus.SUCCESS.getStatus().intValue(),
                "Validation of status code");
    }

    /**
     * method to validate response status code with expected status code
     * @param rs - RestAssured response
     * @param expectedStatus - expected status code for the Response
     * */
    public void statusValidation(Response rs, int expectedStatus){

        reportLog("Expected status code: " + expectedStatus);
        reportLog("Actual status code: " + rs.getStatusCode());
        Assert.assertEquals(rs.getStatusCode(), expectedStatus,
                "Validation of status code");
    }
}
