Feature: Filters feature
  As an administrator
  I want to open Filters
  So that I can grid with filters

  Background:
    Given ADMINISTRATOR login to the Report Portal
    When User navigates to FILTERS menu
    Then User creates a new filter

  @smoke @filter_is_great
  Scenario: Verify Filters page contains some filters
    Given User navigates to FILTERS menu
    Then Filters grid contains such filters
      | Android_API |
      | DEMO_FILTER |
      | Test        |
    And Filters grid contains such columns
      | Filter name         |
      | Options             |
      | Owner               |
      | Shared              |
      | Display on launches |
      | Delete              |
