package com.seleniumforge.pages;

import com.seleniumforge.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CartPage models the shopping cart page and its actions.
 */
public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    private static final By CART_LIST = By.cssSelector(".cart_list");
    private static final By CART_ITEMS = By.cssSelector(".cart_item");
    private static final By ITEM_NAME = By.cssSelector(".inventory_item_name");

    public CartPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    /**
     * Get names of products present in the cart.
     *
     * @return list of product names
     */
    public List<String> getCartProductNames() {
        waitForVisibility(CART_LIST);
        return driver.findElements(CART_ITEMS).stream()
                .map(item -> item.findElement(ITEM_NAME).getText())
                .collect(Collectors.toList());
    }

    /**
     * Remove a product from the cart by its name.
     *
     * @param name product name to remove
     */
    public void removeProductByName(String name) {
        waitForVisibility(CART_LIST);
        WebElement item = driver.findElements(CART_ITEMS).stream()
                .filter(cartItem -> cartItem.findElement(ITEM_NAME).getText().trim().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElseThrow(() -> new org.openqa.selenium.NoSuchElementException(
                        "Product not found in cart: " + name));
        click(item.findElement(By.cssSelector("button.cart_button")));
        waitForInvisibility(item);
    }

    /**
     * Proceed to the checkout information page.
     */
    public void proceedToCheckout() {
        click(checkoutButton);
    }
}
