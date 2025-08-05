package com.automation.tests;

import com.automation.utils.BrowserManager;
import com.automation.utils.ConfigManager;
import com.automation.utils.ExtentReportManager;
import org.testng.annotations.*;

/**
 * Base Test class with common test setup and teardown
 */
public class BaseTest {
    protected ConfigManager config;

    @BeforeSuite
    public void suiteSetup() {
        ExtentReportManager.initializeExtentReport();
    }

    @BeforeMethod
    public void setUp() {
        config = ConfigManager.getInstance();
        BrowserManager.initializeBrowser();
    }

    @AfterMethod
    public void tearDown() {
        BrowserManager.closeBrowser();
        ExtentReportManager.removeTest();
    }

    @AfterSuite
    public void suiteTearDown() {
        ExtentReportManager.flushReport();
    }
}
