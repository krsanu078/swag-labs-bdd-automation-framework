Feature: User login
  Users should be able to authenticate and receive useful feedback for invalid credentials.

  Background:
    Given I am on the login page

  @smoke
  Scenario: Log in with valid credentials
    When I log in as "standard_user" with password "secret_sauce"
    Then the products page should be displayed

  @regression
  Scenario Outline: Reject invalid credentials
    When I log in as "<username>" with password "<password>"
    Then a login error should be displayed

    Examples:
      | username        | password     |
      | locked_out_user | secret_sauce |
      | standard_user   | wrong_password |
      |                 |              |
