Feature: A new article is added if all required fields are entered and none of the fields contains invalid characters

# Required fields for article: Author, Title, Journal, Year, Volume
# Optional fields for article: Number, Pages, Month, Note, Key

    Scenario: adding article succeeds with valid author and title and journal and year and volume
        Given command add is selected
        And command article is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And journal "Testermag" are entered
        And year "2017" are entered
        And volume "13" are entered
        And command print is selected
        Then system will respond with "New article added successfully"

    Scenario: adding article fails with invalid author and valid title and journal and year and volume
        Given command add is selected
        And command article is selected
        When author "ääkkösman" are entered
        And title "Testingname" are entered
        And journal "Testermag" are entered
        And year "2017" are entered
        And volume "13" are entered
        And command print is selected
        Then system will respond with "Invalid author"

    Scenario: adding article fails with valid author and invalid title and valid journal and year and volume
        Given command add is selected
        And command article is selected
        When author "Testerman" are entered
        And title "Ääkkösname" are entered
        And journal "Testermag" are entered
        And year "2017" are entered
        And volume "13" are entered
        And command print is selected
        Then system will respond with "Invalid title"

    Scenario: adding article fails with valid author and title and invalid journal and valid year and volume
        Given command add is selected
        And command article is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And journal "Testermäg" are entered
        And year "2017" are entered
        And volume "13" are entered
        And command print is selected        
        Then system will respond with "Invalid journal"

    Scenario: adding article fails with valid author and title and journal and invalid year and valid volume
        Given command add is selected
        And command article is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And journal "Testermag" are entered
        And year "-2017" are entered
        And volume "13" are entered
        And command print is selected
        Then system will respond with "Invalid year"

    Scenario: adding article fails with valid author and title and journal and year and invalid volume
        Given command add is selected
        And command article is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And journal "Testermag" are entered
        And year "2017" are entered
        And volume "No Volume" are entered
        And command print is selected        
        Then system will respond with "Invalid volume"