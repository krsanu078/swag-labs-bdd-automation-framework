package com.swaglabs.bdd.steps;

import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.ProductPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProductSteps {

    @When("I open the product {string}")
    public void iOpenTheProduct(String productName) {
        new HomePage().openProductByName(productName);
    }

    @Then("the product details should match {string}")
    public void theProductDetailsShouldMatch(String productName) {
        ProductPage productPage = new ProductPage();
        Assert.assertEquals(productPage.getProductName(), productName, "Product name should match");
        Assert.assertFalse(productPage.getProductDescription().isBlank(),
                "Product description should be displayed");
    }
}
