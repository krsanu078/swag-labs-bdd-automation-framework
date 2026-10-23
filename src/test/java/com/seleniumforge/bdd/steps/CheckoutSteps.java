package com.seleniumforge.bdd.steps;

import com.seleniumforge.pages.CartPage;
import com.seleniumforge.pages.CheckoutCompletePage;
import com.seleniumforge.pages.CheckoutOverviewPage;
import com.seleniumforge.pages.CheckoutPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutSteps {

    @When("I check out the cart with customer details {string}, {string}, and {string}")
    public void iCheckOutTheCartWithCustomerDetails(String firstName, String lastName, String postalCode) {
        new CartPage().proceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.enterCustomerInformation(firstName, lastName, postalCode);
        checkoutPage.continueToOverview();
        new CheckoutOverviewPage().finishCheckout();
    }

    @Then("the checkout confirmation should contain {string}")
    public void theCheckoutConfirmationShouldContain(String expectedText) {
        String header = new CheckoutCompletePage().getCompleteHeaderText();
        Assert.assertTrue(header.toUpperCase().contains(expectedText.toUpperCase()),
                "Checkout confirmation should contain " + expectedText);
    }
}
