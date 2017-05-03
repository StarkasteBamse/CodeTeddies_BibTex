Feature: A reference can be edited after it has been created

Scenario: editing article succeeds after it has been created
        Given an article has been created
        And command "edit" is selected
        When valid input are entered for editing
        Then system will respond with "reference updated!"

Scenario: editing article fails if wrong input is given for the field
        Given an article has been created
        And command "edit" is selected
        When invalid input are entered for editing
        Then system will respond with "invalid input value for the field: year"

Scenario: editing article will be stopped if empty field is given
        Given an article has been created
        And command "edit" is selected
        When empty field is given
        Then system will not respond with "reference updated!"

Scenario: editing article succeeds after it has been created with only required fields
        Given an article has been created with required fields
        And command "edit" is selected
        When valid input are entered for editing
        Then system will respond with "reference updated!"

Scenario: editing article can be left is 0 is entered
        Given an article has been created
        And command "edit" is selected
        When command "0" is selected
        Then system will not respond with "reference updated!"