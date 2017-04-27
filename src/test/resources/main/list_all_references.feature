Feature: application lists all references
    
    Scenario: when print is selected application prints to screen all articles
        Given valid article is added
        And command "clear" is selected 
#       clear memory from articles
        And command "load" is selected 
#       load all articles from database
        When command "print" is selected
        Then system will respond with a valid wrapping

