Feature: A reference can be deleted after it has been created

Scenario: deleting article succeeds after it has been created
        Given an article has been created
        And command "delete" is selected
        When created article is selected to be deleted
        Then system will respond with "reference deleted!"

Scenario: deleting article can be left is 0 is entered
        Given an article has been created
        And command "delete" is selected
        When command "0" is selected
        Then system will not respond with "reference deleted!"