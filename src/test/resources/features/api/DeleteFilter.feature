@Api
Feature: Delete filters
  As a user
  I want to be able delete existing filter

  Scenario: Admin user delete existing filter
    Given User with role ADMINISTRATOR and superadmin_personal project
    And User sends POST filter request
    And Save new filter ID
    When User sends DELETE filters request
    Then Verify response has 200 status code
    When User sends GET filter by ID request
    Then Verify response has 404 status code

  Scenario: Admin user should not delete not existing filter
    Given User with role ADMINISTRATOR and superadmin_personal project
    And Set not existing Filter ID
    When User sends DELETE filters request
    Then Verify response has 404 status code
