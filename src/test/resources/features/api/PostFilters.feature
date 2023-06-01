@Api
Feature: Post filters
  As a user
  I want to be able create new Filter

  Scenario: Admin user create a new filter
    Given User with role ADMINISTRATOR and superadmin_personal project
    When User sends POST filter request to api/v1/{projectId}/filter
    Then Verify response has 201 status code
    And Save new filter ID
    When User sends GET filter by ID request to api/v1/{projectId}/filter/{filterId}
    Then Verify response has 200 status code
    And Verify response body is not empty

  Scenario: Admin user should not create a new filter for not existing project
    Given User with role ADMINISTRATOR and some_personal project
    When User sends POST filter request to api/v1/{projectId}/filter
    Then Verify response has 403 status code

  Scenario: Admin user create a new filter with no specific parameters
    Given User with role ADMINISTRATOR and superadmin_personal project
    When User sends POST filter request request with wrong body to api/v1/{projectId}/filter
    Then Verify response has 400 status code
