/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.Article;
import logic.Validator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Willburner
 */
public class ArticleTest {
    
    private Article instance;
    private Validator vali = new Validator();
    
    public ArticleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Article();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setAuthor method, of class Article.
     */
    @Test
    public void testSetAuthor() {
        String author = "tekija";
        instance.setAuthor(author, vali);
        assertEquals(instance.getField("author"), author);
    }

    /**
     * Test of setTitle method, of class Article.
     */
    @Test
    public void testSetTitle() {
        String title = "title";
        instance.setTitle(title, vali);
        assertEquals(instance.getField("title"), title);
    }

    /**
     * Test of setJournal method, of class Article.
     */
    @Test
    public void testSetJournal() {
        String journal = "journal";
        instance.setJournal(journal, vali);
        assertEquals(instance.getField("journal"), journal);
    }

    /**
     * Test of setYear method, of class Article.
     */
    @Test
    public void testSetYear() {
        String year = "2015";
        instance.setYear(year, vali);
        assertEquals(instance.getField("year"), year);
    }

    /**
     * Test of setVolume method, of class Article.
     */
    @Test
    public void testSetVolume() {
        String volume = "20";
        instance.setVolume(volume, vali);
        assertEquals(instance.getField("volume"), volume);
    }

    /**
     * Test of hasRequiredFields method, of class Article.
     */
    @Test
    public void testHasRequiredFields() {
        boolean expResult = false;
        boolean result = instance.hasRequiredFields();
        assertEquals(expResult, result);
        instance.setAuthor("testA", vali);
        assertEquals(expResult, instance.hasRequiredFields());
        instance.setTitle("testT", vali);
        assertEquals(expResult, instance.hasRequiredFields());
        instance.setJournal("testJ", vali);
        assertEquals(expResult, instance.hasRequiredFields());
        instance.setVolume("testVol", vali);
        assertEquals(expResult, instance.hasRequiredFields());
    }
    
    /**
     * Test of hasRequiredFields method when all fields are set, 
     * of class Article.
     */
    @Test
    public void testHasRequiredFieldsWhenFieldsAreSet() {
        instance = new Article();
        instance.setAuthor("Arto Hellas", vali);
        instance.setJournal("SSCSI", vali);
        instance.setTitle("My doctoral dissertation: never getting it ready",
                                                                          vali);
        instance.setYear("2014", vali);
        instance.setVolume("13", vali);
        boolean expResult = true;
        boolean result = instance.hasRequiredFields();
        assertEquals(expResult, result);
    }
    
    @Test
    public void getFieldReturnsNull() {
        instance = new Article();
        instance.setAuthor(null, vali);
        boolean result = false;
        if(instance.getField("author") == null) {
            result = true;
        }
        assertTrue(result);
    }
}

