Feature: A new phdthesis is added if all required fields are entered and none of the fields contains invalid characters

  # Required fields: Author, Title, School, Year
  # Optional fields: Type, Address, Month, Note, Key 

    Scenario: adding phdthesis succeeds with valid input
        Given command "add" is selected
        And command phdthesis is selected
        When valid input are entered for phdthesis       
        Then system will respond with "New phdthesis added successfully"

    Scenario: adding phdthesis fails with invalid input
        Given command "add" is selected
        And command phdthesis is selected
        When invalid input are entered for phdthesis        
        Then system will respond with "Not proper format!"

