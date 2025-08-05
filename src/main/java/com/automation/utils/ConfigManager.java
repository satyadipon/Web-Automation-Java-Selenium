package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration Manager to handle properties file
 */
public class ConfigManager {
    private static ConfigManager instance;
    private Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    private ConfigManager() {
        loadProperties();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE_PATH, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBaseUrl() {
        String env = getProperty("env");
        return getProperty(env + ".base.url");
    }

    public String getAdminUsername() {
        return getProperty("admin.username");
    }

    public String getAdminPassword() {
        return getProperty("admin.password");
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }

    public String getExtentReportPath() {
        return getProperty("extent.report.path");
    }

    public String getExtentReportTitle() {
        return getProperty("extent.report.title");
    }

    public String getExtentReportName() {
        return getProperty("extent.report.name");
    }
}
