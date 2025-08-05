package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Dashboard Page Object class
 */
public class DashboardPage extends BasePage {
    
    // Locators using PageFactory
    @FindBy(css = ".oxd-text--h6")
    private WebElement dashboardTitle;
    
    @FindBy(css = ".oxd-userdropdown")
    private WebElement userDropdown;
    
    @FindBy(linkText = "Logout")
    private WebElement logoutButton;
    
    @FindBy(css = ".oxd-sidepanel")
    private WebElement sideMenu;
    
    @FindBy(css = ".oxd-text--h6:nth-child(2)")
    private WebElement welcomeMessage;

    public DashboardPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        return isVisible(dashboardTitle) && isVisible(sideMenu);
    }

    public String getDashboardTitle() {
        return getText(dashboardTitle);
    }

    public void clickUserDropdown() {
        click(userDropdown);
    }

    public void logout() {
        clickUserDropdown();
        click(logoutButton);
    }

    public boolean isUserDropdownDisplayed() {
        return isVisible(userDropdown);
    }

    public boolean isSideMenuDisplayed() {
        return isVisible(sideMenu);
    }

    public String getWelcomeMessage() {
        if (isVisible(welcomeMessage)) {
            return getText(welcomeMessage);
        }
        return "";
    }

    public void waitForDashboard() {
        wait.until(ExpectedConditions.visibilityOf(dashboardTitle));
        wait.until(ExpectedConditions.visibilityOf(sideMenu));
    }

    public void navigateToModule(String moduleName) {
        WebElement moduleLink = driver.findElement(By.linkText(moduleName));
        click(moduleLink);
    }
}
