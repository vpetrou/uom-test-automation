@Demo @Contacts
Feature: Contacts

  @UI @TC-UI-CON-001
  Scenario: Tests the valid addition of a Contact
    Given the Demo Application is opened
    When the user navigates to "Add New Contact" page
    And the user adds a new contact
      | Name        | City   | Email                |
      | Contact 001 | Athens | email001@vpetrou.com |
    Then a new Contact "email001@vpetrou.com" is created successfully

  @API @TC-API-CON-001
  Scenario: Tests the valid addition of a Contact through REST API
    When a system requests the addition of a new contact
      | Name            | Address         | City   | Phone      | Email                    | Status  | Gender |
      | ContactRest 001 | AddressRest 001 | Athens | 2101111111 | emailRest001@vpetrou.com | enabled | male   |
    Then the system responds and a new Contact "emailRest001@vpetrou.com" is created successfully
