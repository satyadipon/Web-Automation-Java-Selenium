package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Login Page Object class
 */
public class LoginPage extends BasePage {
    
    // Locators using PageFactory
    @FindBy(css = "input[name='username']")
    private WebElement usernameField;
    
    @FindBy(css = "input[name='password']")
    private WebElement passwordField;
    
    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;
    
    @FindBy(css = ".alert-danger, .error-message, .oxd-alert-content-text")
    private WebElement errorMessage;
    
    @FindBy(css = "h1, h2, h3, .login-title, .oxd-text--h5")
    private WebElement loginTitle;
    
    @FindBy(css = "a[href*='forgot'], .forgot-password, .oxd-text--p")
    private WebElement forgotPasswordLink;

    public LoginPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        fill(usernameField, username);
    }

    public void enterPassword(String password) {
        fill(passwordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isLoginPageDisplayed() {
        return isVisible(loginTitle) && isVisible(usernameField) && isVisible(passwordField);
    }

    public String getErrorMessage() {
        if (isVisible(errorMessage)) {
            return getText(errorMessage);
        }
        return "";
    }

    public boolean isErrorMessageDisplayed() {
        return isVisible(errorMessage);
    }

    public String getLoginTitle() {
        return getText(loginTitle);
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return isVisible(forgotPasswordLink);
    }

    public void waitForLoginPage() {
        // Wait for the essential login elements that we know exist
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        // Don't wait for login button as it might have different selectors on different sites
    }
}
