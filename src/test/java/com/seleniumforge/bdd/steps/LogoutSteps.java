package com.seleniumforge.bdd.steps;

import com.seleniumforge.pages.HomePage;
import com.seleniumforge.pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LogoutSteps {

    @When("I log out")
    public void iLogOut() {
        new HomePage().logout();
    }

    @Then("the login page should be displayed again")
    public void theLoginPageShouldBeDisplayedAgain() {
        Assert.assertTrue(new LoginPage().isLoaded(), "Login page should be displayed after logout");
    }
}
