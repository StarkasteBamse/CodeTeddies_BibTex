
Feature: application prints BibTex formatted block for an article

    Scenario: printing fails when no articles are added
        Given command print is selected
        Then system will respond with "No articles in memory"

    Scenario: printing succeeds when one article is added
        Given command add is selected
        And command article is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And journal "Testermag" are entered
        And year "2017" are entered
        And volume "13" are entered
        And command print is selected
        And filename "sigproc.bib" is entered
        Then system will respond with a valid wrapping

# Formatting the output as a string is not wise, better to create a logic for 
# verification
