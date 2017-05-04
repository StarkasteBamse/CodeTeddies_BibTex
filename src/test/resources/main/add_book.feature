Feature: A new book is added if all required fields are entered and none of the fields contains invalid characters

# Required fields for article: Author/Editor, Title, Publisher, Year
# Optional fields for article: Volume/Number, Series, Address, Edition, Month, Note, Key

    Scenario: adding book succeeds with valid input
        Given command "add" is selected
        And command book is selected
        When valid input are entered for book       
        Then system will respond with "New book added successfully"

    Scenario: adding book fails with invalid input
        Given command "add" is selected
        And command book is selected
        When invalid input are entered for book        
        Then system will respond with "Not proper format!"

