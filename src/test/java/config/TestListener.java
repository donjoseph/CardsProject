package config;

import managers.ExtentManager;
import managers.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import tests.BaseClass;

public class TestListener extends BaseClass implements ITestListener {

    public void onStart(ITestContext context) {

        reportLog("\n" +
                "//////////////////////////////////////////////////////////////////////////////////\n"
                + "///////////////// Test Suite " + context.getName() + " started ///////////////////\n"
                + "//////////////////////////////////////////////////////////////////////////////////");
    }

    public void onFinish(ITestContext context) {

        ExtentTestManager.endTest();
        reportLog("\n" +
                "//////////////////////////////////////////////////////////////////////////////////\n"
                + "///////////////// Test Suite " + context.getName() + " completed ///////////////////\n"
                + "//////////////////////////////////////////////////////////////////////////////////");
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {

        ExtentTestManager.startTest(result.getMethod().getMethodName());
        reportLog(("*** Running test method " + result.getMethod().getMethodName() + "........"));
    }

    public void onTestSuccess(ITestResult result) {

        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
        reportLog("*** Executed " + result.getMethod().getMethodName() + " test successfully.......");
    }

    public void onTestFailure(ITestResult result) {

        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
        reportLog("*** Test execution " + result.getMethod().getMethodName() + " failed......");
        ExtentTestManager.getTest().log(Status.ERROR, "StackTrace Result: " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {

        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
        reportLog("*** Test " + result.getMethod().getMethodName() + " skipped......");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

        reportLog("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

}
