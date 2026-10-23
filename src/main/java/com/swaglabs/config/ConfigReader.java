package com.swaglabs.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Singleton configuration reader for framework properties.
 */
public final class ConfigReader {

    private static final String CONFIG_FILE = "config.properties";
    private static ConfigReader instance;
    private final Properties properties;

    private ConfigReader() {
        properties = new Properties();
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + CONFIG_FILE + " on classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration: " + e.getMessage(), e);
        }
    }

    /**
     * Returns the singleton instance of ConfigReader.
     *
     * @return ConfigReader singleton
     */
    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    /**
     * Get property value by key.
     *
     * @param key property key
     * @return property value or null if not found
     */
    public String getProperty(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    public String getProperty(String key, String defaultValue) {
        return System.getProperty(key, properties.getProperty(key, defaultValue));
    }

    /**
     * Get browser configured in properties.
     *
     * @return browser name
     */
    public String getBrowser() {
        return getProperty("browser", "chrome").trim();
    }

    /**
     * Get base URL configured in properties.
     *
     * @return base url
     */
    public String getBaseUrl() {
        return Objects.toString(getProperty("url"), "");
    }

    /**
     * Returns configured timeout in seconds.
     *
     * @return timeout seconds
     */
    public int getTimeout() {
        String t = getProperty("timeouts", "30");
        try {
            return Integer.parseInt(t);
        } catch (NumberFormatException e) {
            return 30;
        }
    }

    public int getExplicitWaitTimeout() {
        String timeout = getProperty("explicitWaitTimeout", "10");
        try {
            return Integer.parseInt(timeout);
        } catch (NumberFormatException e) {
            return 10;
        }
    }

    /**
     * Returns whether headless mode is enabled.
     *
     * @return true if headless
     */
    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }

    /**
     * Returns whether parallel execution is enabled.
     *
     * @return true if parallel
     */
    public boolean isParallel() {
        return Boolean.parseBoolean(getProperty("parallel", "false"));
    }
}
