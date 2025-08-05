package com.automation.utils;

import com.aventstack.extentreports.Status;
import org.testng.Assert;

/**
 * Custom assertion utility that captures screenshots on failure
 */
public class AssertUtil {
    
    /**
     * Assert that a condition is true and capture screenshot if it fails
     */
    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            // Log the failure step first
            ExtentReportManager.getTest().log(Status.FAIL, message);
            
            // Capture screenshot immediately after logging the failure
            try {
                String screenshot = ScreenshotUtil.captureScreenshot("assertion_failure");
                // Use info log with media attachment instead of addScreenCaptureFromPath
                ExtentReportManager.getTest().log(Status.INFO, "Screenshot captured at failure point",
                    com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
            } catch (Exception e) {
                ExtentReportManager.getTest().log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
            }
            
            // Now throw the assertion error
            Assert.fail(message);
        } else {
            // Log success
            ExtentReportManager.getTest().log(Status.PASS, message);
        }
    }
    
    /**
     * Assert that a condition is false and capture screenshot if it fails
     */
    public static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }
    
    /**
     * Assert that an object is not null and capture screenshot if it fails
     */
    public static void assertNotNull(Object object, String message) {
        assertTrue(object != null, message);
    }
    
    /**
     * Assert that two objects are equal and capture screenshot if they are not
     */
    public static void assertEquals(Object actual, Object expected, String message) {
        boolean isEqual = (actual == null && expected == null) || 
                         (actual != null && actual.equals(expected));
        
        if (!isEqual) {
            String failMessage = message + " - Expected: '" + expected + "' but was: '" + actual + "'";
            assertTrue(false, failMessage);
        } else {
            ExtentReportManager.getTest().log(Status.PASS, message);
        }
    }
}
