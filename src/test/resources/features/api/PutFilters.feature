@Api
Feature: Put filters
  As a user
  I want to be able to update existing Filter

  Background:
    Given User with role ADMINISTRATOR and superadmin_personal project
    When User sends POST filter request
    And Save new filter ID

  @delete_filter_if_exist
  Scenario: Admin user update existing filter
    Given User sends GET filter by ID request
    When User updates new 'updated Description for the filter' description for existing filter
    Then Verify response has 200 status code
    When User sends GET filter by ID request
    Then Verify that filter has 'updated Description for the filter' description

  Scenario: Admin user should not update existing filter for wrong  project
    Given User with role ADMINISTRATOR and some_personal project
    When User sends GET filter by ID request
    Then Verify response has 403 status code

  Scenario: Admin user create a new filter with no filter ID
    Given User sends GET filter by ID request
    When User updates existing filter with no conditions
    Then Verify response has 400 status code
