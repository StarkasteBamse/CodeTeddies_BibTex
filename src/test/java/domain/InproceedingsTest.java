package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InproceedingsTest {
    
    private Inproceedings instance;
    
    public InproceedingsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Inproceedings();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetTitle() {
        String testTitle = "testTitle";
        instance.setTitle(testTitle);
        assertEquals(testTitle, instance.title);
    }

    @Test
    public void testSetAuthor() {
        String testAuthor = "testAuthor";
        instance.setAuthor(testAuthor);
        assertEquals(testAuthor, instance.author);
    }

    @Test
    public void testSetBookTitle() {
        String testBookTitle = "testBookTitle";
        instance.setBookTitle(testBookTitle);
        assertEquals(testBookTitle, instance.booktitle);
    }

    @Test
    public void testSetYear() {
        String testYear = "1984";
        instance.setYear(testYear);
        assertEquals(testYear, instance.year);
    }

    @Test
    public void testHasRequiredFieldsWhenEmpty() {
        assertEquals(false, instance.hasRequiredFields());
    }
    
    @Test
    public void testHasRequiredFieldsReturnsTrueWhenSet() {
        String testAuthor = "testAuthor";
        instance.setAuthor(testAuthor);
        String testTitle = "testTitle";
        instance.setTitle(testTitle);
        String testBookTitle = "testBookTitle";
        instance.setBookTitle(testBookTitle);
        String testYear = "1984";
        instance.setYear(testYear);
        assertEquals(true, instance.hasRequiredFields());
    }
}
