Feature: application prints BibTex formatted block for an article

    Scenario: printing fails when no articles are added
        Given command print is selected
        Then system will respond with "No articles in memory"

    Scenario: printing succeeds when one article is added
        Given article "Testerman" "Testingname" "Testermag" "2017" "13" is added
        And command print is selected
        Then system will respond with a valid wrapping
# Formatting the output as a string is not wise, better to create a logic for 
# verification
