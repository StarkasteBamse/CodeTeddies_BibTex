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
        book.setField("title",
              "Prediction for real-time sampling of Robothands");
        book.setField("publisher", "Caltech Arxiv");
        book.setField("author",
              "Sandeep Mavadia, Jarrah Sastrawan, Ostawa Snapsahan");
        book.setField("year", "2016");
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
        String publishUpperCase = bookTest.getField("publisher").toUpperCase();
        bookTest.setField("publisher", publishUpperCase);
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
        instance.setField("title", testTitle);
        assertEquals(testTitle, instance.getField("title"));
    }

    @Test
    public void testSetAuthor() {
        String testAuthor = "testAuthor";
        instance.setField("author", testAuthor);
        assertEquals(testAuthor, instance.getField("author"));
    }

    @Test
    public void testSetPublisher() {
        String testPublisher = "testPublisher";
        instance.setField("publisher", testPublisher);
        assertEquals(testPublisher, instance.getField("publisher"));
    }

    @Test
    public void testSetYear() {
        String testYear = "1984";
        instance.setField("year", testYear);
        assertEquals(testYear, instance.getField("year"));
    }

    @Test
    public void testHasRequiredFieldsWhenEmpty() {
        // Test  all required fields one by one
        assertEquals(false, instance.hasRequiredFields());
        instance.setField("author", "testA");
        assertEquals(false, instance.hasRequiredFields());
        instance.setField("title", "testT");
        assertEquals(false, instance.hasRequiredFields());
        instance.setField("field", "testPub");
        assertEquals(false, instance.hasRequiredFields());
    }

    @Test
    public void testHasRequiredFieldsReturnsTrueWhenSet() {
        String testAuthor = "testAuthor";
        instance.setField("author", testAuthor);
        String testTitle = "testTitle";
        instance.setField("title", testTitle);
        String testPublisher = "testPublisher";
        instance.setField("publisher", testPublisher);
        String testYear = "1984";
        instance.setField("year", testYear);
        assertEquals(true, instance.hasRequiredFields());
    }

    @Test
    public void getFieldReturnsNull() {
        instance = new Book();
        instance.setField("author", null);
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

    @Test
    public void removeFieldEmptiesProperly() {
        instance = new Book();

        instance.setField("author", "Testerman");
        instance.removeField("author");
        assertEquals(null, instance.getField("author"));
    }

    @Test
    public void removeFieldReturnsTrue() {
        instance = new Book();

        instance.setField("author", "Testerman");
        assertEquals(true, instance.removeField("author"));
    }

    @Test
    public void removeFieldFailsOnUnsetField() {
        instance = new Book();

        assertEquals(false, instance.removeField("author"));
    }

    @Test
    public void setAndGetIDWork() {
        instance = new Book();
        
        instance.setID("abcd1234");
        assertEquals("abcd1234", instance.getID());
    }
    
    @Test
    public void isNotEqualToOthers() {
        instance = new Book();
        Book comparable = new Book();
        
        instance.setField("title", "qwe");
        instance.setField("author", "a");
        instance.setField("publisher", "b");
        instance.setField("year", "2016");

        comparable.setField("title", "asd");
        comparable.setField("author", "a");
        comparable.setField("publisher", "b");
        comparable.setField("year", "2016");

        boolean expResult = false;
        assertEquals(expResult, instance.equals(comparable));
        assertEquals(expResult, instance.equals(null));
        assertEquals(expResult, instance.hashCode() == comparable.hashCode());
    }
}
