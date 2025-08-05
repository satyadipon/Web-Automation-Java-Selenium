package com.automation.utils;

import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG Listener for Extent Reports
 */
public class ExtentReportListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        ExtentReportManager.createTest(testName, description != null ? description : testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String errorMessage = result.getThrowable().getMessage();
        ExtentReportManager.getTest().log(Status.FAIL, "Test execution failed: " + errorMessage);
        // Note: Screenshots are now captured at the specific failure point in AssertUtil
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable().getMessage());
    }
}
