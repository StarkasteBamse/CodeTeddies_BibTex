
Feature: application prints BibTex formatted block for an article

    Scenario: printing fails when there is no articles in memory
        Given command "print" is selected
        Then system will respond with "No articles in memory"

    Scenario: printing succeeds when there are articles in memory
        Given valid article is added
        When command "print" is selected
        Then system will respond with a valid wrapping

# Formatting the output as a string is not wise, better to create a logic for 
# verification
