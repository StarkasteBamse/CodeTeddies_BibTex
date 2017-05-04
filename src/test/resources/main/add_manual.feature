Feature: A new manual is added if all required fields are entered and none of the fields contains invalid characters

  # Required fields: Title
  # Optional fields: Author, Organization, Address, Edition, Month, Year, Note, Key

    Scenario: adding manual succeeds with valid input
        Given command "add" is selected
        And command manual is selected
        When valid input are entered for manual       
        Then system will respond with "New manual added successfully"

    Scenario: adding manual fails with invalid input
        Given command "add" is selected
        And command manual is selected
        When invalid input are entered for manual        
        Then system will respond with "Not proper format!"

