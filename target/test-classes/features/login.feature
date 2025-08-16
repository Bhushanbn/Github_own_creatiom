Feature: Login

  Scenario Outline: Valid login should land on dashboard
    Given launch the application
    When login with "standard_user" and "secret_sauce"
    Then I should be on the dashboard
    Examples:
      | standard_user |secret_sauce

