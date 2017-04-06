
package wrapper;

import java.util.Scanner;
import domain.Article;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class WrapperTest {
    
    private Article a;
    private String author;
    private String journal;
    private String title;
    private final int year = 1999;
    private final int volume = 12;
    
    public WrapperTest() {
        a = new Article();
        author = "author";
        journal = "journal";
        title = "title";
        a.setAuthor(author);
        a.setJournal(journal);
        a.setTitle(title);
        a.setYear(year);
        a.setVolume(volume);
    }
    
    @Before
    public void setUp() {
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
