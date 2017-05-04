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

