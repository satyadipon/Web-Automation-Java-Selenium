package com.automation.tests;

import com.automation.pages.DashboardPage;
import com.automation.pages.LoginPage;
import com.automation.utils.AssertUtil;
import com.automation.utils.ExtentReportListener;
import com.automation.utils.ExtentReportManager;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Login Test class
 */
@Listeners(ExtentReportListener.class)
public class LoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        ExtentReportManager.getTest().log(Status.INFO, "Starting valid login test");
        
        // Navigate to the application
        String baseUrl = config.getBaseUrl();
        ExtentReportManager.getTest().log(Status.INFO, "Navigating to: " + baseUrl);
        
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(baseUrl);
        loginPage.waitForLoginPage();
        
        ExtentReportManager.getTest().log(Status.INFO, "Login page loaded successfully");
        
        // Verify login page is displayed
        AssertUtil.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        
        // Perform login
        String username = config.getAdminUsername();
        String password = config.getAdminPassword();
        
        ExtentReportManager.getTest().log(Status.INFO, "Entering credentials - Username: " + username);
        loginPage.login(username, password);
        
        // Verify dashboard is displayed
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.waitForDashboard();
        
        AssertUtil.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed after login");
        ExtentReportManager.getTest().log(Status.PASS, "Successfully logged in and dashboard is displayed");
        
        // Verify URL contains dashboard
        String currentUrl = dashboardPage.getCurrentUrl();
        AssertUtil.assertTrue(currentUrl.contains("dashboard"), "URL should contain 'dashboard' after login");
        ExtentReportManager.getTest().log(Status.PASS, "Current URL is: " + currentUrl);
    }

    @Test(description = "Verify login failure with invalid credentials")
    public void testInvalidLogin() {
        ExtentReportManager.getTest().log(Status.INFO, "Starting invalid login test");
        
        // Navigate to the application
        String baseUrl = config.getBaseUrl();
        ExtentReportManager.getTest().log(Status.INFO, "Navigating to: " + baseUrl);
        
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(baseUrl);
        loginPage.waitForLoginPage();
        
        // Verify login page is displayed
        AssertUtil.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        ExtentReportManager.getTest().log(Status.PASS, "Login page is displayed correctly");
        
        // Attempt login with invalid credentials
        String invalidUsername = "InvalidUser";
        String invalidPassword = "InvalidPassword";
        
        ExtentReportManager.getTest().log(Status.INFO, "Attempting login with invalid credentials");
        loginPage.login(invalidUsername, invalidPassword);
        
        // Verify error message is displayed
        AssertUtil.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid login");
        String errorMessage = loginPage.getErrorMessage();
        ExtentReportManager.getTest().log(Status.PASS, "Error message displayed: " + errorMessage);
        
        // Verify still on login page
        AssertUtil.assertTrue(loginPage.isLoginPageDisplayed(), "Should remain on login page after invalid login");
        ExtentReportManager.getTest().log(Status.PASS, "Remained on login page as expected");
    }

    @Test(description = "Verify login with empty credentials")
    public void testEmptyCredentials() {
        ExtentReportManager.getTest().log(Status.INFO, "Starting empty credentials test");
        
        // Navigate to the application
        String baseUrl = config.getBaseUrl();
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(baseUrl);
        loginPage.waitForLoginPage();
        
        // Verify login page is displayed
        AssertUtil.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        
        // Attempt login with empty credentials
        ExtentReportManager.getTest().log(Status.INFO, "Attempting login with empty credentials");
        loginPage.login("", "");
        
        // Verify still on login page
        AssertUtil.assertTrue(loginPage.isLoginPageDisplayed(), "Should remain on login page with empty credentials");
        ExtentReportManager.getTest().log(Status.PASS, "Remained on login page as expected with empty credentials");
    }

    @Test(description = "Verify login page elements are displayed")
    public void testLoginPageElements() {
        ExtentReportManager.getTest().log(Status.INFO, "Starting login page elements test");
        
        // Navigate to the application
        String baseUrl = config.getBaseUrl();
        LoginPage loginPage = new LoginPage();
        loginPage.navigateTo(baseUrl);
        loginPage.waitForLoginPage();
        
        // Verify all login page elements
        AssertUtil.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        ExtentReportManager.getTest().log(Status.PASS, "Login page is displayed");
        
        String loginTitle = loginPage.getLoginTitle();
        AssertUtil.assertFalse(loginTitle.isEmpty(), "Login title should not be empty");
        ExtentReportManager.getTest().log(Status.PASS, "Login title: " + loginTitle);
        
        // Verify page title
        String pageTitle = loginPage.getTitle();
        AssertUtil.assertTrue(pageTitle.contains("OrangeHRM"), "Page title should contain 'OrangeHRM'");
        ExtentReportManager.getTest().log(Status.PASS, "Page title: " + pageTitle);
    }
}
