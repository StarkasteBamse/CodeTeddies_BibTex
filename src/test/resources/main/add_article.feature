Feature: A new article is added if all required fields are entered and none of the fields contains invalid characters

# Required fields for article: Author, Title, Journal, Year, Volume
# Optional fields for article: Number, Pages, Month, Note, Key

    Scenario: addition succeeds with valid author and title and journal and year and volume
        Given command add is selected
        When author "Testerman" and title "Testingname" and journal "Testermag" and year "2017" and volume "13" are entered
        Then system will respond with "New article added successfully"

    Scenario: addition fails with invalid author and valid title and journal and year and volume
        Given command add is selected
        When author "ääkkösman" and title "Testingname" and journal "Testermag" and year "2017" and volume "13" are entered
        Then system will respond with "Invalid author name"

    Scenario: addition fails with valid author and invalid title and valid journal and year and volume
        Given command add is selected
        When author "Testerman" and title "Ääkkösname" and journal "Testermag" and year "2017" and volume "13" are entered
        Then system will respond with "Invalid title name"

    Scenario: addition fails with valid author and title and invalid journal and valid year and volume
        Given command add is selected
        When author "Testerman" and title "Testingname" and journal "Testermäg" and year "2017" and volume "13" are entered
        Then system will respond with "Invalid journal name"

    Scenario: addition fails with valid author and title and journal and invalid year and valid volume
        Given command add is selected
        When author "Testerman" and title "Testingname" and journal "Testermag" and year "a" and volume "13" are entered
        Then system will respond with "Invalid year"

    Scenario: addition fails with valid author and title and journal and year and invalid volume
        Given command add is selected
        When author "Testerman" and title "Testingname" and journal "Testermag" and year "2017" and volume "a" are entered
        Then system will respond with "Invalid volume"