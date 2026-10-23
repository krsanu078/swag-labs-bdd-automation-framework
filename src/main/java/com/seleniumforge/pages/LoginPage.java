package com.seleniumforge.pages;

import com.seleniumforge.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginPage for SauceDemo - provides business methods to perform login and read errors.
 */
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorContainer;

    /**
     * Initialize LoginPage and its elements.
     */
    public LoginPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    /**
     * Perform login with username and password. Caller should navigate or verify resulting page.
     *
     * @param username username to use
     * @param password password to use
     */
    public void login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        dismissPasswordChangeAlertIfPresent();
    }

    public boolean isLoaded() {
        waitForVisibility(usernameField);
        waitForVisibility(passwordField);
        return true;
    }

    /**
     * Return the visible error message (if any) after a failed login attempt.
     *
     * @return error text or empty string when none
     */
    public String getErrorMessage() {
        waitForVisibility(errorContainer);
        return getText(errorContainer);
    }
}
