Feature: A new inproceedings is added if all required fields are entered and none of the fields contains invalid characters

  # Required fields: Author, Title, Booktitle, Year
  # Optional fields: Editor, Volume/number, Series, Pages, Address, Month, Organization, Publisher, Note, Key
  Scenario: adding inproceedings succeeds with valid author and title and booktitle and year
    Given command add is selected
    And command add book is selected
    When author "Testerman" and title "Testingtitle" and booktitle "Testingbook" and year "1999" are entered
    Then system will respond with "New book added successfully"

  Scenario: adding inproceedings fails with valid author and title and booktitle and invalid year
    Given command add is selected
    And command add book is selected
    When author "Testerman" and title "Testingtitle" and booktitle "Testingbook" and year "ääää" are entered
    Then system will respond with "Invalid year"

  Scenario: adding inproceedings fails with valid author and title and invalid booktitle and valid year
    Given command add is selected
    And command add book is selected
    When author "Testerman" and title "Testingtitle" and booktitle "ÄääÖöö" and year "1999" are entered
    Then system will respond with "Invalid booktitle name"

  Scenario: adding inproceedings fails with valid author and invalid title and valid booktitle and year
    Given command add is selected
    And command add book is selected
    When author "Testerman" and title "Testääää" and booktitle "Testingbook" and year "1999" are entered
    Then system will respond with "Invalid title name"

Scenario: adding inproceedings fails with invalid valid author and valid title and booktitle and year
    Given command add is selected
    And command add book is selected
    When author "TestermÄn" and title "Testingtitle" and booktitle "Testingbook" and year "1999" are entered
    Then system will respond with "Invalid author name"
