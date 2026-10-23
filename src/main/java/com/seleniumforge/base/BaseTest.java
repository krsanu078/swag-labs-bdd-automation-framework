package com.seleniumforge.base;

import com.seleniumforge.config.ConfigReader;
import com.seleniumforge.driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * BaseTest contains WebDriver setup and teardown shared by test lifecycle adapters.
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected ConfigReader config;
    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    protected void setUp() {
        LOGGER.info("Test setup starting");
        config = ConfigReader.getInstance();
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        String baseUrl = config.getBaseUrl();
        if (baseUrl != null && !baseUrl.isBlank()) {
            LOGGER.info("Navigating to base URL: {}", baseUrl);
            driver.get(baseUrl);
        }
        LOGGER.info("Test setup completed");
    }

    protected void tearDown() {
        LOGGER.info("Test teardown starting");
        DriverFactory.quitDriver();
        LOGGER.info("Test teardown completed");
    }
}
