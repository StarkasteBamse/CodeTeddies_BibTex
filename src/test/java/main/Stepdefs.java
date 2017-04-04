package main;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.StubIO;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class Stepdefs {

    // TODO: Everything
    List<String> inputLines = new ArrayList<>();
    //App app;
    StubIO io;

    @Given("^command add is selected$")
    public void command_add_selected() throws Throwable {
        inputLines.add("add");
    }

    @Given("^command print is selected$")
    public void command_print_selected() throws Throwable {
        inputLines.add("print");
    }

    @When("^author \"([^\"]*)\" and title \"([^\"]*)\" and journal "
            + "\"([^\"]*)\" and year \"([^\"]*)\" and volume \"([^\"]*)\" "
            + "are entered$")
    //CHECKSTYLE:OFF
    public void a_author_and_title_and_journal_and_year_and_volume_are_entered
        (String author, String title, String journal, int year, int volume) 
            throws Throwable {
    //CHECKSTYLE:ON
        inputLines.add(author);
        inputLines.add(title);
        inputLines.add(journal);
        inputLines.add(Integer.toString(year));
        inputLines.add(Integer.toString(volume));

        io = new StubIO(inputLines);
        //app = new App(io);
        //app.run();
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String expectedOutput) 
            throws Throwable {
        assertTrue(io.getPrints().contains(expectedOutput));
    }
}
