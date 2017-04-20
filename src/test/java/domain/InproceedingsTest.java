package domain;

import logic.Validator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InproceedingsTest {
    
    private Inproceedings instance;
    private Validator validator = new Validator();
    
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
        instance.setTitle(testTitle, validator);
        assertEquals(testTitle, instance.getField("title"));
    }

    @Test
    public void testSetAuthor() {
        String testAuthor = "testAuthor";
        instance.setAuthor(testAuthor, validator);
        assertEquals(testAuthor, instance.getField("author"));
    }

    @Test
    public void testSetBookTitle() {
        String testBookTitle = "testBookTitle";
        instance.setBookTitle(testBookTitle, validator);
        assertEquals(testBookTitle, instance.getField("booktitle"));
    }

    @Test
    public void testSetYear() {
        String testYear = "1984";
        instance.setYear(testYear, validator);
        assertEquals(testYear, instance.getField("year"));
    }

    @Test
    public void testHasRequiredFieldsWhenEmpty() {
        // Test  all required fields one by one
        assertEquals(false, instance.hasRequiredFields());
        instance.setAuthor("testA", validator);
        assertEquals(false, instance.hasRequiredFields());
        instance.setTitle("testT", validator);
        assertEquals(false, instance.hasRequiredFields());
        instance.setBookTitle("testBT", validator);
        assertEquals(false, instance.hasRequiredFields());
    }
    
    @Test
    public void testHasRequiredFieldsReturnsTrueWhenSet() {
        String testAuthor = "testAuthor";
        instance.setAuthor(testAuthor, validator);
        String testTitle = "testTitle";
        instance.setTitle(testTitle, validator);
        String testBookTitle = "testBookTitle";
        instance.setBookTitle(testBookTitle, validator);
        String testYear = "1984";
        instance.setYear(testYear, validator);
        assertEquals(true, instance.hasRequiredFields());
    }
    
    @Test
    public void getFieldReturnsNull() {
        instance = new Inproceedings();
        instance.setAuthor(null, validator);
        boolean result = false;
        if(instance.getField("author") == null) {
            result = true;
        }
        assertTrue(result);
    }
}
