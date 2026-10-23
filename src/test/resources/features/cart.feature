Feature: Shopping cart
  Customers should be able to add and remove products from their cart.

  Background:
    Given I am on the login page
    And I am logged in as "standard_user" with password "secret_sauce"

  @regression
  Scenario Outline: Add a product to the cart
    When I add "<product>" to the cart
    And I open the cart
    Then the cart should contain "<product>"

    Examples:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
      | Sauce Labs Fleece Jacket |

  @regression
  Scenario: Remove a product from the cart
    When I add "Sauce Labs Backpack" to the cart
    And I open the cart
    Then the cart should contain "Sauce Labs Backpack"
    When I remove "Sauce Labs Backpack" from the cart
    Then the cart should not contain "Sauce Labs Backpack"
