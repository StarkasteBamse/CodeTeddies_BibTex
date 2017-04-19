/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import io.IO;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mrreflex
 */
public class AppTest {
    
    private App app;
    private IO stubIo;
    
    public AppTest() {
    }
    
    @Before
    public void setUp() {
        app = new App(stubIo);
    }

    private void createArticle1() {
        ArrayList<String> article1 = new ArrayList<>();
        article1.add("");
        article1.add("Adventures of Gireoux");
        article1.add("Journal of Psychonometrics");
        article1.add("1922");
        article1.add("122");
    }
    
    private void createBook1() {
        ArrayList<String> article1 = new ArrayList<>();
        article1.add("Robin Mobb");
        article1.add("Adventures of Psyo");
        article1.add("Otava");
        article1.add("2014");
    }
    
    private ArrayList<String> createInproceedings1() {
        ArrayList<String> article1 = new ArrayList<>();
        article1.add("Wayne Gre");
        article1.add("My years as");
        article1.add("Good: years as holigan");
        article1.add("1567");
        return article1;
    }
    
    @Test
    public void testAddArticleSuccessful() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class App.
     */
    @Test
    public void testRun() {
    }

    /**
     * Test of printReferences method, of class App.
     */
    @Test
    public void testPrintReferences() {
    }

}
