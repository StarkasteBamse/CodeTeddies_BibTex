Feature: A new book is added if all required fields are entered and none of the fields contains invalid characters

# Required fields for article: Author/Editor, Title, Publisher, Year
# Optional fields for article: Volume/Number, Series, Address, Edition, Month, Note, Key

    Scenario: adding book succeeds with valid author and title and publisher and year 
        Given command add is selected
        And command book is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And publisher "Testingpublisher" are entered
        And year "2017" are entered
        And command print is selected
        And filename "sigproc.bib" is entered
        Then system will respond with "New book added successfully"

    Scenario: adding book fails with invalid author and valid title and publisher and year
        Given command add is selected
        And command book is selected
        When author "ääkkösman" are entered
        And title "Testingname" are entered
        And publisher "Testingpublisher" are entered
        And year "2017" are entered
        And command print is selected
        And filename "sigproc.bib" is entered
        Then system will respond with "Invalid author"

    Scenario: adding book fails with valid author and invalid title and valid publisher and year
        Given command add is selected
        And command book is selected
        When author "Testerman" are entered
        And title "Ääkkösname" are entered
        And publisher "Testingpublisher" are entered
        And year "2017" are entered
        And command print is selected
        And filename "sigproc.bib" is entered
        Then system will respond with "Invalid title"

    Scenario: adding book fails with valid author and title and invalid publisher and valid year 
        Given command add is selected
        And command book is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And publisher "Ääkköspublisher" are entered
        And year "2017" are entered
        And command print is selected  
        And filename "sigproc.bib" is entered
        Then system will respond with "Invalid publisher"

    Scenario: adding book fails with valid author and title and publisher and invalid year
        Given command add is selected
        And command book is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And publisher "Testingpublisher" are entered
        And year "TesterYear" are entered
        And command print is selected
        And filename "sigproc.bib" is entered
        Then system will respond with "Invalid year"

    