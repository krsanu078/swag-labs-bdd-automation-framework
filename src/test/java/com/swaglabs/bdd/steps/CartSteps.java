package com.swaglabs.bdd.steps;

import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CartSteps {

    @When("I add {string} to the cart")
    public void iAddToTheCart(String productName) {
        new HomePage().addProductToCartByName(productName);
    }

    @When("I open the cart")
    public void iOpenTheCart() {
        new HomePage().goToCart();
    }

    @Then("the cart should contain {string}")
    public void theCartShouldContain(String productName) {
        Assert.assertTrue(new CartPage().getCartProductNames().contains(productName),
                "Cart should contain " + productName);
    }

    @When("I remove {string} from the cart")
    public void iRemoveFromTheCart(String productName) {
        new CartPage().removeProductByName(productName);
    }

    @Then("the cart should not contain {string}")
    public void theCartShouldNotContain(String productName) {
        Assert.assertFalse(new CartPage().getCartProductNames().contains(productName),
                "Cart should not contain " + productName);
    }
}
