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
    
    public Article createMockArticle() {
        Article article = new Article();
        article.setTitle("Prediction and real-time compensation of qubit "
                + "decoherence via machine learning");
        article.setJournal("Caltech Arxiv");
        article.setVolume("222");
        article.setAuthor("Sandeep Mavadia, Jarrah Sastrawan");
        article.setYear("2016");
        return article;
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
        instance.setAuthor(author);
        assertEquals(instance.getField("author"), author);
    }

    @Test
    public void testEqualsMethodTrueWithSameTitles() {
        Article article = createMockArticle();
        Article articleIdentical = createMockArticle();
        assertTrue(article.equals(articleIdentical));
    }
    
    @Test
    public void testEqualsMethodFalseWithDifferentTitles() {
        Article article = createMockArticle();
        Article articleTest = createMockArticle();
        articleTest.setField("title", "Prediction and real-time "
                + "compensation of qubitons");
        assertFalse(article.equals(articleTest));
    }
    
    @Test
    public void testEqualsMethodTrueWithSameButWithOtherHavingCapitals() {
        Article article = createMockArticle();
        Article articleTest = createMockArticle();
        String journalUpperCase = articleTest.getField("journal").toUpperCase();
        articleTest.setJournal(journalUpperCase);
        assertTrue(article.equals(articleTest));
    }
    
    @Test
    public void testEqualsMethodTrueWithSameAndWithOptionalFields() {
        Article article = createMockArticle();
        Article articleWithOptional = createMockArticle();
        articleWithOptional.setField("number", "222");
        
        assertTrue(article.equals(articleWithOptional));
    }
    
    /**
     * Test of setTitle method, of class Article.
     */
    @Test
    public void testSetTitle() {
        String title = "title";
        instance.setTitle(title);
        assertEquals(instance.getField("title"), title);
    }

    /**
     * Test of setJournal method, of class Article.
     */
    @Test
    public void testSetJournal() {
        String journal = "journal";
        instance.setJournal(journal);
        assertEquals(instance.getField("journal"), journal);
    }

    /**
     * Test of setYear method, of class Article.
     */
    @Test
    public void testSetYear() {
        String year = "2015";
        instance.setYear(year);
        assertEquals(instance.getField("year"), year);
    }

    /**
     * Test of setVolume method, of class Article.
     */
    @Test
    public void testSetVolume() {
        String volume = "20";
        instance.setVolume(volume);
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
        instance.setAuthor("testA");
        assertEquals(expResult, instance.hasRequiredFields());
        instance.setTitle("testT");
        assertEquals(expResult, instance.hasRequiredFields());
        instance.setJournal("testJ");
        assertEquals(expResult, instance.hasRequiredFields());
        instance.setVolume("testVol");
        assertEquals(expResult, instance.hasRequiredFields());
    }
    
    /**
     * Test of hasRequiredFields method when all fields are set, 
     * of class Article.
     */
    @Test
    public void testHasRequiredFieldsWhenFieldsAreSet() {
        instance = new Article();
        instance.setAuthor("Arto Hellas");
        instance.setJournal("SSCSI");
        instance.setTitle("My doctoral dissertation: never getting it ready");
        instance.setYear("2014");
        instance.setVolume("13");
        boolean expResult = true;
        boolean result = instance.hasRequiredFields();
        assertEquals(expResult, result);
    }
    
    @Test
    public void getFieldReturnsNull() {
        instance = new Article();
        instance.setAuthor(null);
        boolean result = false;
        if(instance.getField("author") == null) {
            result = true;
        }
        assertTrue(result);
    }
    
    
    @Test
    public void setFieldFailsWithInvalidFieldType() {
        instance = new Article();
        instance.setField("invaliidi", "I won't be used");
        
        String expResult = null;
        assertEquals(expResult, instance.getField("invaliidi"));
    }

    
    @Test
    public void setOptionalFieldsWorksOnRightTypes() {
        instance = new Article();
        
        instance.setField("number", "1");
        instance.setField("pages", "2");
        instance.setField("month", "march");
        instance.setField("note", "helloworld");
        instance.setField("key", "ABCD1234");
        
        assertEquals("1", instance.getField("number"));
        assertEquals("2", instance.getField("pages"));
        assertEquals("march", instance.getField("month"));
        assertEquals("helloworld", instance.getField("note"));
        assertEquals("ABCD1234", instance.getField("key"));
    }
    
    @Test
    public void removeFieldEmptiesProperly() {
        instance = new Article();
        
        instance.setField("author", "Testerman");
        instance.removeField("author");
        assertEquals(null, instance.getField("author"));
    }
    
    @Test
    public void removeFieldReturnsTrue() {
        instance = new Article();
        
        instance.setField("author", "Testerman");
        assertEquals(true, instance.removeField("author"));
    }

    @Test
    public void removeFieldFailsOnUnsetField() {
        instance = new Article();
        
        assertEquals(false, instance.removeField("author"));
    }
    
    @Test
    public void setAndGetIDWork() {
        instance = new Article();
        
        instance.setID("abcd1234");
        assertEquals("abcd1234", instance.getID());
    }
    
    @Test
    public void isNotEqualToOthers() {
        instance = new Article();
        Article comparable = new Article();
        
        instance.setField("title", "qwe");
        instance.setField("author", "a");
        instance.setField("journal", "b");
        instance.setField("year", "2016");
        instance.setField("volume", "2");

        comparable.setField("title", "asd");
        comparable.setField("author", "a");
        comparable.setField("journal", "b");
        comparable.setField("year", "2016");
        comparable.setField("volume", "2");

        boolean expResult = false;
        assertEquals(expResult, instance.equals(comparable));
        assertEquals(expResult, instance.equals(null));
        assertEquals(expResult, instance.hashCode() == comparable.hashCode());
    }
}

