package com.automation.pages;

import com.automation.utils.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Base Page class with common page operations
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = BrowserManager.getDriver();
        this.wait = BrowserManager.getWait();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void waitForPageLoad() {
        wait.until(webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public void click(String selector) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
        element.click();
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void fill(String selector, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
        element.clear();
        element.sendKeys(text);
    }

    public void fill(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public String getText(String selector) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
        return element.getText();
    }

    public String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public boolean isVisible(String selector) {
        try {
            WebElement element = driver.findElement(By.cssSelector(selector));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForSelector(String selector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
