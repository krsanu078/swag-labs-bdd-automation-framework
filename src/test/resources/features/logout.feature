Feature: User logout
  Authenticated users should be able to end their session.

  @regression
  Scenario: Log out of the application
    Given I am on the login page
    And I am logged in as "standard_user" with password "secret_sauce"
    When I log out
    Then the login page should be displayed again
