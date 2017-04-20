Feature: application writes BibTex formatted block for an article
    
    Scenario: writing to file succeeds when one article is added
        Given command add is selected
        And command article is selected
        When author "Testerman" are entered
        And title "Testingname" are entered
        And journal "Testermag" are entered
        And year "2017" are entered
        And volume "13" are entered
        And command print is selected
        And filename "sigproc.bib" is entered
        Then system will respond with a file written in bibtex format

