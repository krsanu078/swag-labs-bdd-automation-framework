package com.seleniumforge.factories;

import com.seleniumforge.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;

/**
 * BrowserFactory is responsible for creating WebDriver instances for supported browsers.
 */
public final class BrowserFactory {

    private BrowserFactory() {
        // Utility class
    }

    /**
     * Create a WebDriver instance based on browser type and headless flag.
     *
     * @param browserType browser type enum
     * @param headless    true to enable headless mode (if supported)
     * @return WebDriver instance
     */
    public static WebDriver createDriver(BrowserType browserType, boolean headless) {
        switch (browserType) {
            case CHROME:
                return createChrome(headless);
            case FIREFOX:
                return createFirefox(headless);
            case EDGE:
                return createEdge(headless);
            case HEADLESS_CHROME:
                return createChrome(true);
            default:
                return createChrome(headless);
        }
    }

    private static WebDriver createChrome(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--incognito");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordCheck,PasswordManagerOnboarding");
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false,
                "profile.default_content_setting_values.notifications", 2
        ));
        options.setHeadless(headless);
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefox(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(headless);
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdge(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        options.setHeadless(headless);
        return new EdgeDriver(options);
    }
}
