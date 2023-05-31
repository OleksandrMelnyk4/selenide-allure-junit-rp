@Api
Feature: Post filters
  As a user
  I want to be able create new Filter

  @delete_filter_if_exist
  Scenario: Admin user create a new filter
    Given User with role ADMINISTRATOR and superadmin_personal project
    When User sends POST filter request
    Then Verify response has 201 status code
    And Save new filter ID
    When User sends GET filter by ID request
    Then Verify response has 200 status code
    And Verify response body is not empty

  Scenario: Admin user should not create a new filter for not existing project
    Given User with role ADMINISTRATOR and some_personal project
    When User sends POST filter request
    Then Verify response has 403 status code

  Scenario: Admin user create a new filter with no specific parameters
    Given User with role ADMINISTRATOR and superadmin_personal project
    When User sends POST filter request request with wrong body
    Then Verify response has 400 status code
