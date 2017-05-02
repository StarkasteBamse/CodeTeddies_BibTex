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

import domain.PhdThesis;
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
public class PhdThesisTest {

    private PhdThesis instance;

    public PhdThesisTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new PhdThesis();
    }

    public PhdThesis createMockPhdThesis() {
        PhdThesis thesis = new PhdThesis();
        thesis.setField("title", "Prediction and real-time compensation of " +
              "qubit decoherence via machine learning");
        thesis.setField("school", "Caltech Arxiv");
        thesis.setField("author", "Sandeep Mavadia, Jarrah Sastrawan");
        thesis.setField("year", "2016");
        return thesis;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEqualsMethodTrueWithSameTitles() {
        PhdThesis thesis = createMockPhdThesis();
        PhdThesis thesisIdentical = createMockPhdThesis();
        assertTrue(thesis.equals(thesisIdentical));
    }

    @Test
    public void testEqualsMethodFalseWithDifferentTitles() {
        PhdThesis thesis = createMockPhdThesis();
        PhdThesis thesisTest = createMockPhdThesis();
        thesisTest.setField("title", "Prediction and real-time "
                + "compensation of qubitons");
        assertFalse(thesis.equals(thesisTest));
    }

    @Test
    public void testEqualsMethodTrueWithSameButWithOtherHavingCapitals() {
        PhdThesis thesis = createMockPhdThesis();
        PhdThesis thesisTest = createMockPhdThesis();
        String schoolUpperCase = thesisTest.getField("school").toUpperCase();
        thesisTest.setField("school", schoolUpperCase);
        assertTrue(thesis.equals(thesisTest));
    }

    @Test
    public void testEqualsMethodTrueWithSameAndWithOptionalFields() {
        PhdThesis thesis = createMockPhdThesis();
        PhdThesis thesisWithOptional = createMockPhdThesis();
        thesisWithOptional.setField("number", "222");

        assertTrue(thesis.equals(thesisWithOptional));
    }

    /**
     * Test of setField method, of class PhdThesis.
     */
    @Test
    public void testSetField() {
        String title = "title";
        instance.setField("title", title);
        assertEquals(instance.getField("title"), title);
    }

    /**
     * Test of hasRequiredFields method, of class PhdThesis.
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
        instance.setField("school", "testJ");
        assertEquals(expResult, instance.hasRequiredFields());
    }

    /**
     * Test of hasRequiredFields method when all fields are set,
     * of class PhdThesis.
     */
    @Test
    public void testHasRequiredFieldsWhenFieldsAreSet() {
        instance = new PhdThesis();
        instance.setField("author", "Arto Hellas");
        instance.setField("school", "University of Nakkila");
        instance.setField("title", "My doctoral dissertation: " +
              "never getting it ready");
        instance.setField("year", "2060");
        boolean expResult = true;
        boolean result = instance.hasRequiredFields();
        assertEquals(expResult, result);
    }

    @Test
    public void getFieldReturnsNull() {
        instance = new PhdThesis();
        instance.setField("author", null);
        boolean result = false;
        if (instance.getField("author") == null) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    public void setFieldFailsWithInvalidFieldType() {
        instance = new PhdThesis();
        instance.setField("invaliidi", "I won't be used");

        String expResult = null;
        assertEquals(expResult, instance.getField("invaliidi"));
    }

    @Test
    public void setOptionalFieldsWorksOnRightTypes() {
        instance = new PhdThesis();

        instance.setField("month", "march");
        instance.setField("address", "Jukutitie25");
        instance.setField("note", "helloworld");
        instance.setField("key", "ABCD1234");

        assertEquals("Jukutitie25", instance.getField("address"));
        assertEquals("march", instance.getField("month"));
        assertEquals("helloworld", instance.getField("note"));
        assertEquals("ABCD1234", instance.getField("key"));
    }

    @Test
    public void removeFieldEmptiesProperly() {
        instance = new PhdThesis();

        instance.setField("author", "Testerman");
        instance.removeField("author");
        assertEquals(null, instance.getField("author"));
    }

    @Test
    public void removeFieldReturnsTrue() {
        instance = new PhdThesis();

        instance.setField("author", "Testerman");
        assertEquals(true, instance.removeField("author"));
    }

    @Test
    public void removeFieldFailsOnUnsetField() {
        instance = new PhdThesis();

        assertEquals(false, instance.removeField("author"));
    }
    
    @Test
    public void setAndGetIDWork() {
        instance = new PhdThesis();
        
        instance.setID("abcd1234");
        assertEquals("abcd1234", instance.getID());
    }
    
    @Test
    public void isNotEqualToOthers() {
        instance = new PhdThesis();
        PhdThesis comparable = new PhdThesis();
        
        instance.setField("author", "Matti");
        instance.setField("title", "asd");
        instance.setField("school", "asdfa");
        instance.setField("year", "2016");
        
        comparable.setField("author", "Ville");
        comparable.setField("title", "asd");
        comparable.setField("school", "asdfa");
        comparable.setField("year", "2016");
        
        
        boolean expResult = false;
        assertEquals(expResult, instance.equals(comparable));
        assertEquals(expResult, instance.equals(null));
        assertEquals(expResult, instance.hashCode() == comparable.hashCode());
    }

}
