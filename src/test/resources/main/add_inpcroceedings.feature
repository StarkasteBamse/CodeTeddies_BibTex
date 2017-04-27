Feature: A new inproceedings is added if all required fields are entered and none of the fields contains invalid characters

  # Required fields: Author, Title, Booktitle, Year
  # Optional fields: Editor, Volume/number, Series, Pages, Address, Month, Organization, Publisher, Note, Key

    Scenario: adding inproceedings succeeds with valid input
        Given command "add" is selected
        And command inproceedings is selected
        When valid input are entered for inproceedings       
        Then system will respond with "New inproceedings added successfully"

    Scenario: adding inproceedings fails with invalid input
        Given command "add" is selected
        And command inproceedings is selected
        When invalid input are entered for inproceedings        
        Then system will respond with "Not proper format!"


  
#  Scenario: adding inproceedings succeeds with valid author and title and booktitle and year
#    Given command add is selected
#    And command inproceedings is selected
#    When author "Testerman" are entered
#    And title "Testingtitle" are entered
#    And booktitle "Testingbook" are entered
#    And year "1999" are entered
#    And command print is selected
#    And filename "sigproc.bib" is entered
#    Then system will respond with "New inproceedings added successfully"
#
#  Scenario: adding inproceedings fails with valid author and title and booktitle and invalid year
#    Given command add is selected
#    And command inproceedings is selected
#    When author "Testerman" are entered
#    And title "Testingtitle" are entered
#    And booktitle "Testingbook" are entered
#    And year "-98b" are entered
#    And command print is selected
#    And filename "sigproc.bib" is entered
#    Then system will respond with "Invalid year"
#
#  Scenario: adding inproceedings fails with valid author and title and invalid booktitle and valid year
#    Given command add is selected
#    And command inproceedings is selected
#    When author "Testerman" are entered
#    And title "Testingtitle" are entered
#    And booktitle "ÄääÖöö" are entered
#    And year "1999" are entered
#    And command print is selected
#    And filename "sigproc.bib" is entered
#    Then system will respond with "Invalid booktitle"
#
#  Scenario: adding inproceedings fails with valid author and invalid title and valid booktitle and year
#    Given command add is selected
#    And command inproceedings is selected
#    When author "Testerman" are entered
#    And title "Testääää" are entered
#    And booktitle "Testingbook" are entered
#    And year "1999" are entered
#    And command print is selected
#    And filename "sigproc.bib" is entered
#    Then system will respond with "Invalid title"
#
#Scenario: adding inproceedings fails with invalid author and valid title and booktitle and year
#    Given command add is selected
#    And command inproceedings is selected
#    When author "TestermÄn" are entered
#    And title "Testingtitle" are entered
#    And booktitle "Testingbook" are entered
#    And year "1999" are entered
#    And command print is selected
#    And filename "sigproc.bib" is entered
#    Then system will respond with "Invalid author"
