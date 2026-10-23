package com.seleniumforge.bdd.steps;

import com.seleniumforge.pages.HomePage;
import com.seleniumforge.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        Assert.assertTrue(new LoginPage().isLoaded(), "Login page should be displayed");
    }

    @Given("I am logged in as {string} with password {string}")
    @When("I log in as {string} with password {string}")
    public void iLogInAsWithPassword(String username, String password) {
        new LoginPage().login(username, password);
    }

    @Then("the products page should be displayed")
    public void theProductsPageShouldBeDisplayed() {
        Assert.assertTrue(new HomePage().isLoaded(), "Products page should be displayed");
    }

    @Then("a login error should be displayed")
    public void aLoginErrorShouldBeDisplayed() {
        Assert.assertFalse(new LoginPage().getErrorMessage().isBlank(), "A login error should be displayed");
    }
}
