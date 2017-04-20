package domain;

import logic.Validator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    
    private Book instance;
    private Validator vali = new Validator();
    
    public BookTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Book();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetTitle() {
        String testTitle = "testTitle";
        instance.setTitle(testTitle, vali);
        assertEquals(testTitle, instance.getField("title"));
    }

    @Test
    public void testSetAuthor() {
        String testAuthor = "testAuthor";
        instance.setAuthor(testAuthor, vali);
        assertEquals(testAuthor, instance.getField("author"));
    }

    @Test
    public void testSetPublisher() {
        String testPublisher = "testPublisher";
        instance.setPublisher(testPublisher, vali);
        assertEquals(testPublisher, instance.getField("publisher"));
    }

    @Test
    public void testSetYear() {
        String testYear = "1984";
        instance.setYear(testYear, vali);
        assertEquals(testYear, instance.getField("year"));
    }

    @Test
    public void testHasRequiredFieldsWhenEmpty() {
        // Test  all required fields one by one
        assertEquals(false, instance.hasRequiredFields());
        instance.setAuthor("testA", vali);
        assertEquals(false, instance.hasRequiredFields());
        instance.setTitle("testT", vali);
        assertEquals(false, instance.hasRequiredFields());
        instance.setPublisher("testPub", vali);
        assertEquals(false, instance.hasRequiredFields());
    }
    
    @Test
    public void testHasRequiredFieldsReturnsTrueWhenSet() {
        String testAuthor = "testAuthor";
        instance.setAuthor(testAuthor, vali);
        String testTitle = "testTitle";
        instance.setTitle(testTitle, vali);
        String testPublisher = "testPublisher";
        instance.setPublisher(testPublisher, vali);
        String testYear = "1984";
        instance.setYear(testYear, vali);
        assertEquals(true, instance.hasRequiredFields());
    }
    
    @Test
    public void getFieldReturnsNull() {
        instance = new Book();
        instance.setAuthor(null, vali);
        boolean result = false;
        if(instance.getField("author") == null) {
            result = true;
        }
        assertTrue(result);
    }
}
