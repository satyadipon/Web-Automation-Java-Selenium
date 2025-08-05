package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Extent Report Manager for test reporting
 */
public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initializeExtentReport() {
        ConfigManager config = ConfigManager.getInstance();
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(config.getExtentReportPath());
        sparkReporter.config().setDocumentTitle(config.getExtentReportTitle());
        sparkReporter.config().setReportName(config.getExtentReportName());
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Browser", config.getBrowser());
        extent.setSystemInfo("Environment", config.getProperty("env"));
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void removeTest() {
        test.remove();
    }
}
