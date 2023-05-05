Feature: As user I want to be able login to the system
  As an administrator or default user
  I want to be able to login to the Report portal
  So that I can work in the system

  @smoke
  Scenario Outline: User is able to login
    Given <user> login to the Report Portal
    Then Dashboard page is opened

    Examples:
      | user          |
      | ADMINISTRATOR |
      | DEFAULT       |
