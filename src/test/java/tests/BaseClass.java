package tests;

import com.aventstack.extentreports.Status;
import common.ApiValidation;
import common.DeckValidation;
import managers.ExtentTestManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import common.DeckActions;

public class BaseClass {

    final static Logger logger = Logger.getLogger(BaseClass.class.getName());
    DeckActions deckActions;
    ApiValidation apiValidation;

    @BeforeClass
    public void beforeClass() {

        deckActions = new DeckActions();
        apiValidation = new ApiValidation();
    }

    @BeforeMethod
    public void testStart() {
    }

    @AfterMethod
    public void testEnd() {
    }

    public void reportLog(String message) {

        if(ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.INFO, message);//For extentTest HTML report
        }
        logger.info("Message: " + message);
    }
}
