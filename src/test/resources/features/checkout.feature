Feature: Checkout
  Customers should be able to complete an order.

  Background:
    Given I am on the login page
    And I am logged in as "standard_user" with password "secret_sauce"

  @regression
  Scenario Outline: Complete checkout
    When I add "<product>" to the cart
    And I open the cart
    And I check out the cart with customer details "John", "Doe", and "12345"
    Then the checkout confirmation should contain "Thank you"

    Examples:
      | product                  |
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
