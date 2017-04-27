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

    public Book createMockBook() {
        Book book = new Book();
        book.setTitle("Prediction for real-time sampling of Robothands");
        book.setPublisher("Caltech Arxiv");
        book.setAuthor("Sandeep Mavadia, Jarrah Sastrawan, Ostawa Snapsahan");
        book.setYear("2016");
        return book;
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEqualsMethodTrueWithSameTitles() {
        Book book = createMockBook();
        Book bookIdentical = createMockBook();
        assertTrue(book.equals(bookIdentical));
    }
    
    @Test
    public void testEqualsMethodFalseWithDifferentTitles() {
        Book book = createMockBook();
        Book articleTest = createMockBook();
        articleTest.setField("title", "Prediction and real-time "
                + "compensation of qubitons");
        assertFalse(book.equals(articleTest));
    }
    
    @Test
    public void testEqualsMethodTrueWithSameButWithOtherHavingCapitals() {
        Book book = createMockBook();
        Book bookTest = createMockBook();
        String publisherUpperCase = bookTest.getField("publisher").toUpperCase();
        bookTest.setPublisher(publisherUpperCase);
        assertTrue(book.equals(bookTest));
    }
    
    @Test
    public void testEqualsMethodTrueWithSameAndWithOptionalFields() {
        Book book = createMockBook();
        Book bookWithOptional = createMockBook();
        bookWithOptional.setField("number", "222");
        
        assertTrue(book.equals(bookWithOptional));
    }
    
    @Test
    public void testSetTitle() {
        String testTitle = "testTitle";
        instance.setTitle(testTitle);
        assertEquals(testTitle, instance.getField("title"));
    }

    @Test
    public void testSetAuthor() {
        String testAuthor = "testAuthor";
        instance.setAuthor(testAuthor);
        assertEquals(testAuthor, instance.getField("author"));
    }

    @Test
    public void testSetPublisher() {
        String testPublisher = "testPublisher";
        instance.setPublisher(testPublisher);
        assertEquals(testPublisher, instance.getField("publisher"));
    }

    @Test
    public void testSetYear() {
        String testYear = "1984";
        instance.setYear(testYear);
        assertEquals(testYear, instance.getField("year"));
    }

    @Test
    public void testHasRequiredFieldsWhenEmpty() {
        // Test  all required fields one by one
        assertEquals(false, instance.hasRequiredFields());
        instance.setAuthor("testA");
        assertEquals(false, instance.hasRequiredFields());
        instance.setTitle("testT");
        assertEquals(false, instance.hasRequiredFields());
        instance.setPublisher("testPub");
        assertEquals(false, instance.hasRequiredFields());
    }

    @Test
    public void testHasRequiredFieldsReturnsTrueWhenSet() {
        String testAuthor = "testAuthor";
        instance.setAuthor(testAuthor);
        String testTitle = "testTitle";
        instance.setTitle(testTitle);
        String testPublisher = "testPublisher";
        instance.setPublisher(testPublisher);
        String testYear = "1984";
        instance.setYear(testYear);
        assertEquals(true, instance.hasRequiredFields());
    }

    @Test
    public void getFieldReturnsNull() {
        instance = new Book();
        instance.setAuthor(null);
        boolean result = false;
        if (instance.getField("author") == null) {
            result = true;
        }
        assertTrue(result);
    }

    
    @Test
    public void setFieldFailsWithInvalidFieldType() {
        instance = new Book();
        instance.setField("invaliidi", "I won't be used");

        String expResult = null;
        assertEquals(expResult, instance.getField("invaliidi"));
    }
    
}
