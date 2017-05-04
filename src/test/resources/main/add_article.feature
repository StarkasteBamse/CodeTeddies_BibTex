Feature: A new article is added if all required fields are entered and none of the fields contains invalid characters

# Required fields for article: Author, Title, Journal, Year, Volume
# Optional fields for article: Number, Pages, Month, Note, Key


    Scenario: adding article succeeds with valid input
        Given command "add" is selected
        And command article is selected
        When valid input are entered for article       
        Then system will respond with "New article added successfully"

    Scenario: adding article fails with invalid input
        Given command "add" is selected
        And command article is selected
        When invalid input are entered for article        
        Then system will respond with "Not proper format!"

