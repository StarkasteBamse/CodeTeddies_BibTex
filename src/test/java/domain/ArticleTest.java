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
        article.setField("title", "Prediction and real-time compensation"
          + "of qubit decoherence via machine learning");
        article.setField("journal", "Caltech Arxiv");
        article.setField("volume", "222");
        article.setField("author", "Sandeep Mavadia, Jarrah Sastrawan");
        article.setField("year", "2016");
        return article;
    }

    @After
    public void tearDown() {
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
        articleTest.setField("journal", journalUpperCase);
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
     * Test of setField method, of class Article.
     */
    @Test
    public void testSetField() {
        String title = "title";
        instance.setField("title", title);
        assertEquals(instance.getField("title"), title);
    }


    /**
     * Test of hasRequiredFields method, of class Article.
     */
    @Test
    public void testHasRequiredFields() {
        boolean expResult = false;
        boolean result = instance.hasRequiredFields();
        assertEquals(expResult, result);
        instance.setField("author", "testA");
        assertEquals(expResult, instance.hasRequiredFields());
        instance.setField("title", "testT");
        assertEquals(expResult, instance.hasRequiredFields());
        instance.setField("journal", "testJ");
        assertEquals(expResult, instance.hasRequiredFields());
        instance.setField("volume", "testVol");
        assertEquals(expResult, instance.hasRequiredFields());
    }

    /**
     * Test of hasRequiredFields method when all fields are set,
     * of class Article.
     */
    @Test
    public void testHasRequiredFieldsWhenFieldsAreSet() {
        instance = new Article();
        instance.setField("author", "Arto Hellas");
        instance.setField("journal", "SSCSI");
        instance.setField("title",
          "My doctoral dissertation: never getting it ready");
        instance.setField("year", "2014");
        instance.setField("volume", "13");
        boolean expResult = true;
        boolean result = instance.hasRequiredFields();
        assertEquals(expResult, result);
    }

    @Test
    public void getFieldReturnsNull() {
        instance = new Article();
        instance.setField("article", null);
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
}
