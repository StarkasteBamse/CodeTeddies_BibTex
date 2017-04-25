package main;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.ReferenceDAO;
import io.StubIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.App;
import static org.junit.Assert.*;

public class Stepdefs {

    List<String> inputLines = new ArrayList<>();
    App app;
    StubIO io;

    @Given("^command add is selected$")
    public void command_add_selected() throws Throwable {
        inputLines.add("y");
    }

    @Given("^command print is selected$")
    public void command_print_selected() throws Throwable {
        inputLines.add("n");
    }

    @Given("^command article is selected$")
    public void command_article_selected() throws Throwable {
        inputLines.add("1");
    }

    @Given("^command book is selected$")
    public void command_book_selected() throws Throwable {
        inputLines.add("2");
    }

    @Given("^command inproceedings is selected$")
    public void command_inproceedings_selected() throws Throwable {
        inputLines.add("3");
    }

    @Given("^filename \"([^\"]*)\" is entered")
    public void filename_signed_is_entered(String fileName) throws Throwable {
        inputLines.add(fileName);
    }
    
    
    @When("^author \"([^\"]*)\" are entered")
    public void a_author_are_entered(String author) {
        inputLines.add(author);
    }

    @When("^title \"([^\"]*)\" are entered")
    public void a_title_are_entered(String title) {
        inputLines.add(title);
    }

    @When("^booktitle \"([^\"]*)\" are entered$")
    public void a_booktitle_are_entered(String booktitle) {
        inputLines.add(booktitle);
    }

    @When("^publisher \"([^\"]*)\" are entered")
    public void a_publisher_are_entered(String publisher) {
        inputLines.add(publisher);
    }

    @When("^journal \"([^\"]*)\" are entered")
    public void a_journal_are_entered(String journal) {
        inputLines.add(journal);
    }

    @When("^year \"([^\"]*)\" are entered")
    public void a_year_are_entered(String year) {
        inputLines.add(year);
    }

    @When("^volume \"([^\"]*)\" are entered")
    public void a_volume_are_entered(String volume) {
        inputLines.add(volume);
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String expectedOutput)
            throws Throwable {

        io = new StubIO(inputLines);
        app = new App(io, new ReferenceDAO(true));
        app.run();
        assertTrue(io.getPrints().contains(expectedOutput));
    }

    @Then("^system will respond with a valid wrapping$")
    public void system_will_respond_with_valid_wrapping()
            throws Throwable {

        io = new StubIO(inputLines);
        app = new App(io, new ReferenceDAO(true));
        app.run();

        //needs logic for bibtex verification, 
        //now only prints what programn prints
        List<String> prints = io.getPrints();
        String bibtex = prints.get(prints.size() - 2);
        System.out.println("in bibtex format:\n" + bibtex);
        assertTrue(isThisBibTex(bibtex));
    }

    @Then("^system will respond with a file written in bibtex format$")
    public void system_will_respond_with_a_file_written_in_bibtex_format() {
        io = new StubIO(inputLines);
        app = new App(io, new ReferenceDAO(true));
        app.run();
        Scanner reader;
        String bibtex = "";
        try {
            reader = new Scanner(new File("sigproc.bib"));
            while (reader.hasNext()) {
                bibtex += reader.nextLine();
                if (reader.hasNext()) {
                    bibtex += "\n";
                }
            }
        } catch (FileNotFoundException ex) {

        }

        System.out.println("in bibtex format from file:\n" + bibtex);
        assertTrue(isThisBibTex(bibtex));

    }

//CHECKSTYLE:OFF
    public boolean isThisBibTex(String bibtex) {
        if (bibtex.charAt(0) != '@') {
            return false;
        }
        int i;
        int leftCurly = 0;
        int rightCurly = 0;
        int lines = 0;
        int equals = 0;
        for (i = 1; i < bibtex.length(); i++) {
            char c = bibtex.charAt(i);
            if (c == '{') {
                leftCurly++;
            } else if (c == '}') {
                rightCurly++;
            } else if (c == '\n') {
                lines++;
            } else if (c == '=') {
                equals++;
            }
        }
        if (leftCurly != rightCurly) {
            return false;
        }
        if (lines != (equals + 1)) {
            return false;
        }
        return true;
    }
//CHECKSTYLE:ON
}
