Feature: Login

  Scenario: Valid login should land on dashboard
    Given I launch the application
    When login with "standard_user" and "secret_sauce"
    Then I should be on the dashboard

