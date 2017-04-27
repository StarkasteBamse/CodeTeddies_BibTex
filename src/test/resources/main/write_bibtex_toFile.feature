Feature: application writes BibTex formatted block for an article
    
    Scenario: writing to file succeeds when one article is added
        Given valid article is added
        And command "file" is selected
        When filename "sigproc.bib" is entered
        Then system will respond with a file written in bibtex format

