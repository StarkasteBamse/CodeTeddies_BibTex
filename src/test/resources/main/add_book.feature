Feature: A new book is added if all required fields are entered and none of the fields contains invalid characters

# Required fields for article: Author/Editor, Title, Publisher, Year
# Optional fields for article: Volume/Number, Series, Address, Edition, Month, Note, Key

    Scenario: adding book succeeds with valid author and title and journal and year 
        Given command add is selected
        And command book is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And publisher "Testingpublisher" are entered
        And year "2017" are entered
        And command print is selected
        Then system will respond with "New book added succesfully"

    Scenario: adding book fails with invalid author and valid title and journal and year
        Given command add is selected
        And command book is selected
        When author "ääkkösman" are entered
        And title "Testingname" are entered
        And publisher "Testingpublisher" are entered
        And year "2017" are entered
        And command print is selected
        Then system will respond with "Invalid author name"

    Scenario: adding book fails with valid author and invalid title and valid journal and year
        Given command add is selected
        And command book is selected
        When author "Testerman" are entered
        And title "Ääkkösname" are entered
        And publisher "Testingpublisher" are entered
        And year "2017" are entered
        And command print is selected
        Then system will respond with "Invalid title name"

    Scenario: adding book fails with valid author and title and invalid journal and valid year 
        Given command add is selected
        And command book is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And publisher "Testingpublisher" are entered
        And year "2017" are entered
        And command print is selected        
        Then system will respond with "Invalid publisher name"

    Scenario: adding book fails with valid author and title and journal and invalid year
        Given command add is selected
        And command book is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And publisher "Testingpublisher" are entered
        And year "ö" are entered
        And command print is selected
        Then system will respond with "Invalid year"

    