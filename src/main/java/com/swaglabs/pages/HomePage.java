package com.swaglabs.pages;

import com.swaglabs.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Optional;

/**
 * HomePage representing the products listing on SauceDemo.
 */
public class HomePage extends BasePage {

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    private static final By PRODUCTS = By.cssSelector(".inventory_item");
    private static final By PRODUCT_NAME = By.cssSelector(".inventory_item_name");

    /**
     * Initialize HomePage elements.
     */
    public HomePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    /**
     * Check whether the home page has loaded by verifying the presence of products.
     *
     * @return true if products are present
     */
    public boolean isLoaded() {
        return !waitForAllElementsVisible(PRODUCTS).isEmpty();
    }

    /**
     * Open a product by its visible name.
     *
     * @param name product name to open
     */
    public void openProductByName(String name) {
        Optional<WebElement> match = waitForAllElementsVisible(PRODUCTS).stream()
                .filter(p -> {
                    try {
                        WebElement title = p.findElement(PRODUCT_NAME);
                        return title.getText().trim().equalsIgnoreCase(name.trim());
                    } catch (Exception e) {
                        return false;
                    }
                }).findFirst();
        WebElement product = match.orElseThrow(() ->
                new NoSuchElementException("Product not found on products page: " + name));
        click(product.findElement(PRODUCT_NAME));
    }

    /**
     * Add product to cart by visible name.
     *
     * @param name product name
     */
    public void addProductToCartByName(String name) {
        Optional<WebElement> match = waitForAllElementsVisible(PRODUCTS).stream().filter(p -> {
            try {
                WebElement title = p.findElement(PRODUCT_NAME);
                return title.getText().trim().equalsIgnoreCase(name.trim());
            } catch (Exception e) {
                return false;
            }
        }).findFirst();
        WebElement product = match.orElseThrow(() ->
                new NoSuchElementException("Product not found on products page: " + name));
        WebElement addBtn = product.findElement(By.cssSelector("button.btn_inventory"));
        click(addBtn);
    }

    /**
     * Navigate to the Cart page.
     */
    public void goToCart() {
        click(cartLink);
    }

    /**
     * Logout the current user using the burger menu.
     */
    public void logout() {
        click(menuButton);
        waitForVisibility(logoutLink, 5);
        click(logoutLink);
    }
}
