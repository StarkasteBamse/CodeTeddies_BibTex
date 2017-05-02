
package wrapper;

import java.util.Scanner;
import domain.Article;
import logic.Validator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class WrapperTest {

    private Article a;
    private String author;
    private String journal;
    private String title;
    private final String year = "1999";
    private final String volume = "12";
    private final int idLength = 3;
    public WrapperTest() {
        a = new Article();
        author = "author";
        journal = "journal";
        title = "title";
        a.setField("author", author);
        a.setField("journal", journal);
        a.setField("title", title);
        a.setField("year", year);
        a.setField("volume", volume);
        a.setID("0000000001");
    }

    @Before
    public void setUp() {
    }

    @Test
    public void keyCorrect() {

        String key = a.getField("title").substring(0, idLength)
                + a.getID().substring(idLength);
        Wrapper wrp = new Wrapper();
        String bib = wrp.wrap(a);
        Scanner scn = new Scanner(bib);
        boolean correct = false;
        while(scn.hasNextLine()) {
            String line = scn.nextLine();
            if(line.startsWith("@article{" + key + ",")) {
                correct = true;
                break;
            }
        }
        assertTrue(correct);
    }

    @Test
    public void authorCorrect() {
        String comp = author;
        Wrapper wrp = new Wrapper();
        String bib = wrp.wrap(a);
        Scanner scn = new Scanner(bib);
        boolean correct = false;
        while(scn.hasNextLine()) {
            String line = scn.nextLine();
            if(line.startsWith("\tauthor = {" + comp + "}")) {
                correct = true;
                break;
            }
        }
        assertTrue(correct);
    }

    @Test
    public void journalCorrect() {
        String comp = journal;
        Wrapper wrp = new Wrapper();
        String bib = wrp.wrap(a);
        Scanner scn = new Scanner(bib);
        boolean correct = false;
        while(scn.hasNextLine()) {
            String line = scn.nextLine();
            if(line.startsWith("\tjournal = {" + comp + "}")) {
                correct = true;
                break;
            }
        }
        assertTrue(correct);
    }

    @Test
    public void titleCorrect() {
        String comp = title;
        Wrapper wrp = new Wrapper();
        String bib = wrp.wrap(a);
        Scanner scn = new Scanner(bib);
        boolean correct = false;
        while(scn.hasNextLine()) {
            String line = scn.nextLine();
            if(line.startsWith("\ttitle = {" + comp + "}")) {
                correct = true;
                break;
            }
        }
        assertTrue(correct);
    }

    @Test
    public void yearCorrect() {
        String comp = "" + year;
        Wrapper wrp = new Wrapper();
        String bib = wrp.wrap(a);
        Scanner scn = new Scanner(bib);
        boolean correct = false;
        while(scn.hasNextLine()) {
            String line = scn.nextLine();
            if(line.startsWith("\tyear = {" + comp + "}")) {
                correct = true;
                break;
            }
        }
        assertTrue(correct);
    }

    @Test
    public void volumeCorrect() {
        String comp = "" + volume;
        Wrapper wrp = new Wrapper();
        String bib = wrp.wrap(a);
        Scanner scn = new Scanner(bib);
        boolean correct = false;
        while(scn.hasNextLine()) {
            String line = scn.nextLine();
            if(line.startsWith("\tvolume = {" + comp + "}")) {
                correct = true;
                break;
            }
        }
        assertTrue(correct);
    }
}
