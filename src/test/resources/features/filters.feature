Feature: Filters feature


  @smoke
  Scenario Outline: User is able to login
    Given <user> login to the Report Portal
    Then Dashboard page is opened

    Examples:
      | user          |
      | ADMINISTRATOR |
      | DEFAULT       |


  @smoke
  Scenario: Verify Filters page contains some filters
    Given ADMINISTRATOR login to the Report Portal
    When User navigates to FILTERS menu
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

