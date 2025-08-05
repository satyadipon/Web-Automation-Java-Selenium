package com.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Browser Manager to handle Selenium WebDriver instances
 */
public class BrowserManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    public static void initializeBrowser() {
        ConfigManager config = ConfigManager.getInstance();
        
        try {
            System.out.println("Starting browser initialization...");
            WebDriver webDriver = null;
            
            System.out.println("Browser selected: " + config.getBrowser());
            System.out.println("Headless mode: " + config.isHeadless());
            
            switch (config.getBrowser().toLowerCase()) {
                case "chrome":
                case "chromium":
                    System.out.println("Setting up ChromeDriver with Selenium 4 built-in management...");
                    
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (config.isHeadless()) {
                        chromeOptions.addArguments("--headless=new");
                    }
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--disable-web-security");
                    chromeOptions.addArguments("--disable-features=VizDisplayCompositor");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--disable-plugins");
                    chromeOptions.addArguments("--remote-debugging-port=9222");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36");
                    
                    // Try to use installed Chrome explicitly

                    System.out.println("Creating ChromeDriver instance...");
                    
                    try {
                        webDriver = new ChromeDriver(chromeOptions);
                        System.out.println("ChromeDriver instance created successfully");
                    } catch (Exception e) {
                        System.err.println("Failed to create ChromeDriver: " + e.getMessage());
                        // Try with local chromedriver if available
                        System.out.println("Attempting to use local chromedriver...");
                        throw e;
                    }
                    break;
                    
                case "firefox":
                    System.out.println("Setting up FirefoxDriver with Selenium 4 built-in management...");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (config.isHeadless()) {
                        firefoxOptions.addArguments("--headless");
                    }
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                    webDriver = new FirefoxDriver(firefoxOptions);
                    break;
                    
                case "edge":
                    System.out.println("Setting up EdgeDriver with Selenium 4 built-in management...");
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (config.isHeadless()) {
                        edgeOptions.addArguments("--headless");
                    }
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-dev-shm-usage");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--window-size=1920,1080");
                    webDriver = new EdgeDriver(edgeOptions);
                    break;
                    
                default:
                    throw new IllegalArgumentException("Browser not supported: " + config.getBrowser());
            }

            driver.set(webDriver);
            System.out.println("WebDriver set in ThreadLocal");
            
            // Configure timeouts
            System.out.println("Configuring timeouts...");
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config.getTimeout() / 1000));
            webDriver.manage().window().maximize();
            System.out.println("Timeouts configured");
            
            // Initialize WebDriverWait
            System.out.println("Initializing WebDriverWait...");
            wait.set(new WebDriverWait(webDriver, Duration.ofSeconds(config.getTimeout() / 1000)));
            System.out.println("Browser initialization completed successfully");
            
        } catch (Exception e) {
            System.err.println("Error during browser initialization: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize browser: " + e.getMessage(), e);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriverWait getWait() {
        return wait.get();
    }

    public static void closeBrowser() {
        try {
            if (driver.get() != null) {
                driver.get().quit();
                driver.remove();
            }
            
            if (wait.get() != null) {
                wait.remove();
            }
        } catch (Exception e) {
            // Log error but don't throw to avoid masking test failures
            System.err.println("Error closing browser: " + e.getMessage());
        }
    }
}
