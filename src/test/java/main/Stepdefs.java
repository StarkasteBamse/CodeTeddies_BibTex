package main;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.DAO;
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
    List<String> errorLines = new ArrayList<>();
    App app;
    StubIO io;
    String bibFile = "";
     
    @Given("^command \"([^\"]*)\" is selected$")
    public void command_selected(String command) throws Throwable {
        inputLines.add(command);
    }

    @Given("^command article is selected$")
    public void command_article_selected() throws Throwable {
        inputLines.add("1");
    }
    
    @Given("^an article has been created$")
    public void an_article_has_been_created() throws Throwable {
        inputLines.add("add");
        inputLines.add("1");
        this.valid_input_are_entered_for_article();
    }
    
    @Given("^an article has been created with required fields$")
    public void an_article_has_been_created_w_req_fields() throws Throwable {
        final int lim = 5;
        inputLines.add("add");
        inputLines.add("1");
        inputLines.add("Tommi Pelkonen");
        inputLines.add("Hyppaa hyppaa");
        inputLines.add("AAC");
        inputLines.add("2006"); //year
        inputLines.add("12");    //volume
        for (int i = 0; i < lim; i++) {
            inputLines.add("");
        }
    }

    @When("^valid input are entered for article$")
    public void valid_input_are_entered_for_article() throws Throwable {
        //required fields
        inputLines.add("author");
        inputLines.add("title");
        inputLines.add("journal");
        inputLines.add("2000"); //year
        inputLines.add("6");    //volume
        //optional fields
        inputLines.add("12");   //number
        inputLines.add("1-2");  //pages
        inputLines.add("12");   //month
        inputLines.add("note");
        inputLines.add("key");
    }
//CHECKSTYLE:OFF
    @When("^invalid input are entered for article$")
    public void invalid_input_are_entered_for_article() throws Throwable {
        //required fields
        inputLines.add("");
        errorLines.add("Invalid author");
        
        inputLines.add("add");
        inputLines.add("1");
        
        inputLines.add("author");
        inputLines.add("");
        errorLines.add("Invalid title");
        
        inputLines.add("add");
        inputLines.add("1");
        
        inputLines.add("author");
        inputLines.add("title");
        inputLines.add("");
        errorLines.add("Invalid journal");
        
        inputLines.add("add");
        inputLines.add("1");
        
        inputLines.add("author");
        inputLines.add("title");
        inputLines.add("journal");        
        inputLines.add("WRONG");
        errorLines.add("Invalid year");
        
        inputLines.add("add");
        inputLines.add("1");
        
        inputLines.add("author");
        inputLines.add("title");
        inputLines.add("journal");        
        inputLines.add("2000");
        inputLines.add("WRONGthisToo");
        errorLines.add("Invalid volume");
        
           
//        //optional fields
//        inputLines.add("12");   //number
//        inputLines.add("1-2");  //pages
//        inputLines.add("12");   //month
//        inputLines.add("note");
//        inputLines.add("key");
    }
//CHECKSTYLE:ON
    @Given("^command book is selected$")
    public void command_book_selected() throws Throwable {
        inputLines.add("2");
    }

    @When("^valid input are entered for book")
    public void valid_input_are_entered_for_book() throws Throwable {
        //required fields
        inputLines.add("author");
        inputLines.add("title");
        inputLines.add("publisher");
        inputLines.add("2000"); //year
        //optional fields
        inputLines.add("666"); //number
        inputLines.add("series");
        inputLines.add("address");
        inputLines.add("edition");
        inputLines.add("1"); //month
        inputLines.add("note");
        inputLines.add("key");
    }
//CHECKSTYLE:OFF
    @When("^invalid input are entered for book$")
    public void invalid_input_are_entered_for_book() throws Throwable {
        //required fields
        inputLines.add("");
        errorLines.add("Invalid author");
        
        inputLines.add("add");
        inputLines.add("2");
        
        inputLines.add("author");
        inputLines.add("");
        errorLines.add("Invalid title");
        
        inputLines.add("add");
        inputLines.add("2");
        
        inputLines.add("author");
        inputLines.add("title");
        inputLines.add("");
        errorLines.add("Invalid publisher");
        
        inputLines.add("add");
        inputLines.add("2");
        
        inputLines.add("author");
        inputLines.add("title");
        inputLines.add("publisher");
        inputLines.add("IamWRONG");
        errorLines.add("Invalid year");
                               
        //optional fields
//        inputLines.add("666");  //number
//        inputLines.add("series");
//        inputLines.add("address");
//        inputLines.add("edition");
//        inputLines.add("1");    //month
//        inputLines.add("note");
//        inputLines.add("key");
    }
//CHECKSTYLE:ON
    @Given("^command inproceedings is selected$")
    public void command_inproceedings_selected() throws Throwable {
        inputLines.add("3");
    }

    @When("^valid input are entered for inproceedings")
    public void valid_input_are_entered_for_inproceedings() throws Throwable {
        //required fields
        inputLines.add("author");
        inputLines.add("title");
        inputLines.add("booktitle");
        inputLines.add("1");     //year
        //optional fields    
        inputLines.add("editor");
        inputLines.add("666");   //volume
        inputLines.add("series");
        inputLines.add("1-2");    //pages
        inputLines.add("address");
        inputLines.add("7");    //month
        inputLines.add("organization");
        inputLines.add("publisher");
        inputLines.add("note");
        inputLines.add("key");
    }
    
    @When("^valid input are entered for editing")
    public void valid_input_are_entered_for_editing() throws Throwable {
        inputLines.add("1");
        inputLines.add("title");
        inputLines.add("korjattu_title");
    }
    
    @When("^invalid input are entered for editing")
    public void invalid_input_are_entered_for_editing() throws Throwable {
        inputLines.add("1");
        inputLines.add("year");
        inputLines.add("ei_numeerinen_vuosi");
    }
    
    @When("^empty field is given")
    public void empty_field_is_given() throws Throwable {
        inputLines.add("1");
        inputLines.add("");
    }
//CHECKSTYLE:OFF
    @When("^invalid input are entered for inproceedings")
    public void invalid_input_are_entered_for_inproceedings() throws Throwable {
        //required fields
        inputLines.add("");
        errorLines.add("Invalid author");
        
        inputLines.add("add");
        inputLines.add("3");
        
        inputLines.add("author");
        inputLines.add("");
        errorLines.add("Invalid title");
        
        inputLines.add("add");
        inputLines.add("3");
        
        inputLines.add("author");        
        inputLines.add("title");
        inputLines.add("");
        errorLines.add("Invalid booktitle");
        
        inputLines.add("add");
        inputLines.add("3");
        
        inputLines.add("author");        
        inputLines.add("title");       
        inputLines.add("booktitle");
        inputLines.add("iAmWrongYear");
        errorLines.add("Invalid year");
        
        
//        //optional fields    
//        inputLines.add("editor");
//        inputLines.add("666");   //volume
//        inputLines.add("series");
//        inputLines.add("1-2");    //pages
//        inputLines.add("address");
//        inputLines.add("7");    //month
//        inputLines.add("organization");
//        inputLines.add("publisher");
//        inputLines.add("note");
//        inputLines.add("key");
    }
//CHECKSTYLE:ON
    @Given("^filename \"([^\"]*)\" is entered")
    public void filename_signed_is_entered(String fileName) throws Throwable {
        inputLines.add(fileName);
        bibFile = fileName;
    }
    
    @Given("^valid article is added$")
    public void valid_article_is_added() throws Throwable {
        command_selected("add");
        command_article_selected();
        valid_input_are_entered_for_article();
        
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String expectedOutput)
            throws Throwable {
        runApp();
        assertTrue(io.getPrints().contains(expectedOutput));
        for (String error : errorLines) {
            assertTrue(io.getPrints().contains(error));
        }
    }
    
    @Then("^system will not respond with \"([^\"]*)\"$")
    public void system_will_not_respond_with(String expectedOutput)
            throws Throwable {
        runApp();
        assertTrue(!io.getPrints().contains(expectedOutput));
    }

    private void runApp() {
        inputLines.add("quit");
        io = new StubIO(inputLines);
        app = new App(io, new ReferenceDAO(true));
        app.run();
    }

    @Then("^system will respond with a valid wrapping$")
    public void system_will_respond_with_valid_wrapping()
            throws Throwable {
        runApp();

        //needs logic for bibtex verification, 
        //now only prints what programn prints
        List<String> prints = io.getPrints();

        String bibtex = "";
        for (String print : prints) {
            if (print.startsWith("@")) {
                bibtex = print;
                break;
            }
        }
        System.out.println("in bibtex format:\n" + bibtex);
        assertTrue(isThisBibTex(bibtex));
    }

    @Then("^system will respond with a file written in bibtex format$")
    public void system_will_respond_with_a_file_written_in_bibtex_format() {
        runApp();
        Scanner reader;
        String bibtex = "";
        try {
            reader = new Scanner(new File(bibFile));
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
