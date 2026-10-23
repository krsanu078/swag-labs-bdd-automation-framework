Feature: Product details
  Customers should be able to inspect product details.

  Background:
    Given I am on the login page
    And I am logged in as "standard_user" with password "secret_sauce"

  @regression
  Scenario Outline: View product details
    When I open the product "<product>"
    Then the product details should match "<product>"

    Examples:
      | product                  |
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
