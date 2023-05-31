@Api
Feature: Get filters
  As a user
  I want to be able get Filters

  Scenario: Admin user get all filters
    Given User with role ADMINISTRATOR and superadmin_personal project
    When User sends GET filters request
    Then Verify response has 200 status code
    And Verify response body contains filters

  Scenario: Admin user should not get all filters for not existing project
    Given User with role ADMINISTRATOR and some_personal project
    When User sends GET filters request
    Then Verify response has 403 status code
