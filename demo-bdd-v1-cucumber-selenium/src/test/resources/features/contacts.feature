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
