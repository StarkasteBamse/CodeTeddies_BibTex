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
        instance.setField("title", testTitle);
        assertEquals(testTitle, instance.getField("title"));
    }

    @Test
    public void testHasRequiredFieldsWhenEmpty() {
        // Test  all required fields one by one
        assertEquals(false, instance.hasRequiredFields());
        instance.setField("author", "testA");
        assertEquals(false, instance.hasRequiredFields());
        instance.setField("title", "testT");
        assertEquals(false, instance.hasRequiredFields());
        instance.setField("booktitle", "testBT");
        assertEquals(false, instance.hasRequiredFields());
    }

    @Test
    public void testHasRequiredFieldsReturnsTrueWhenSet() {
        String testAuthor = "testAuthor";
        instance.setField("author", testAuthor);
        String testTitle = "testTitle";
        instance.setField("title", testTitle);
        String testBookTitle = "testBookTitle";
        instance.setField("booktitle", testBookTitle);
        String testYear = "1984";
        instance.setField("year", testYear);
        assertEquals(true, instance.hasRequiredFields());
    }

    @Test
    public void getFieldReturnsNull() {
        instance = new Inproceedings();
        instance.setField("author", null);
        boolean result = false;
        if (instance.getField("author") == null) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    public void setFieldFailsWithInvalidFieldType() {
        instance = new Inproceedings();
        instance.setField("invaliidi", "I won't be used");

        String expResult = null;
        assertEquals(expResult, instance.getField("invaliidi"));
    }

    @Test
    public void removeFieldEmptiesProperly() {
        instance = new Inproceedings();

        instance.setField("author", "Testerman");
        instance.removeField("author");
        assertEquals(null, instance.getField("author"));
    }

    @Test
    public void removeFieldReturnsTrue() {
        instance = new Inproceedings();

        instance.setField("author", "Testerman");
        assertEquals(true, instance.removeField("author"));
    }

    @Test
    public void removeFieldFailsOnUnsetField() {
        instance = new Inproceedings();

        assertEquals(false, instance.removeField("author"));
    }
    
    @Test
    public void setAndGetIDWork() {
        instance = new Inproceedings();
        
        instance.setID("abcd1234");
        assertEquals("abcd1234", instance.getID());
    }
    
    @Test
    public void isNotEqualToOthers() {
        instance = new Inproceedings();
        Inproceedings comparable = new Inproceedings();
        
        instance.setField("author", "Matti");
        instance.setField("title", "asd");
        instance.setField("booktitle", "asdfa");
        instance.setField("year", "2016");
        
        comparable.setField("author", "Ville");
        comparable.setField("title", "asd");
        comparable.setField("booktitle", "asdfa");
        comparable.setField("year", "2016");
        
        
        boolean expResult = false;
        assertEquals(expResult, instance.equals(comparable));
        assertEquals(expResult, instance.equals(null));
        assertEquals(expResult, instance.hashCode() == comparable.hashCode());
    }
}
