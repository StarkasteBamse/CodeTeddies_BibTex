package domain;

import domain.Manual;
import java.util.Objects;
import logic.Validator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ManualTest {
//CHECKSTYLE:OFF

    private Manual instance;
    private final String[] mockValues = {"Mornom Random",
                                        "Uni. Madagaskar",
                                        "Too bad C 23",
                                        "Sovietlux",
                                        "February",
                                        "2099",
                                        "the key"};
//CHECKSTYLE:ON
    @Before
    public void setUp() {
        instance = new Manual();
    }

    public Manual createMockManual() {
        Manual manual = new Manual();
        manual.setField("title", "Prediction and real-time compensation " +
                "of qubitdecoherence via machine learning");

        for (int i = 0; i < this.mockValues.length; i++) {
            manual.setField(manual.getOptionalFields().get(i), mockValues[i]);
        }
        return manual;
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setField method, of class Manual.
     */
    @Test
    public void testSetField() {
        String title = "Manual of Cooperation: Sixth sense for code smells";
        instance.setField("title", title);
        assertEquals(instance.getField("title"), title);
    }

    @Test
    public void testEqualsMethodTrueWithSameTitles() {
        Manual manual = createMockManual();
        Manual manualIdentical = createMockManual();
        assertTrue(manual.equals(manualIdentical));
    }

    @Test
    public void testEqualsMethodFalseWithDifferentTitles() {
        Manual manual = createMockManual();
        Manual manualTest = createMockManual();
        manualTest.setField("title", "Prediction and real-time "
                + "compensation of qubitons");
        assertFalse(manual.equals(manualTest));
    }

    @Test
    public void testEqualsMethodTrueWithSameButWithOtherHavingCapitals() {
        Manual manual = createMockManual();
        Manual manualTest = createMockManual();
        String titleUpperCase = manualTest.getField("title").toUpperCase();
        manualTest.setField("title", titleUpperCase);
        assertTrue(manual.equals(manualTest));
    }

    @Test
    public void testEqualsMethodTrueWithSameAndWithOptionalFields() {
        Manual manual = createMockManual();
        Manual manualWithOptional = createMockManual();
        manualWithOptional.setField("author", "222");

        assertTrue(manual.equals(manualWithOptional));
    }

    /**
     * Test of hasRequiredFields method, of class Manual.
     */
    @Test
    public void testHasRequiredFields() {
        boolean expResult = false;
        boolean result = instance.hasRequiredFields();
        assertEquals(expResult, result);
        instance.setField("title", "testT");
        assertEquals(true, instance.hasRequiredFields());
    }

    /**
     * Test of hasRequiredFields method when all fields are set,
     * of class Manual.
     */
    @Test
    public void testHasRequiredFieldsWhenFieldsAreSet() {
        instance = new Manual();
        instance.setField("title", "My doctoral dissertation:" +
              " never getting it ready");
        boolean expResult = true;
        boolean result = instance.hasRequiredFields();
        assertEquals(expResult, result);
    }

    @Test
    public void getFieldReturnsNull() {
        instance = new Manual();
        instance.setField("title", null);
        boolean result = false;
        if (instance.getField("author") == null) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    public void setFieldFailsWithInvalidFieldType() {
        instance = new Manual();
        instance.setField("invaliidi", "I won't be used");

        String expResult = null;
        assertEquals(expResult, instance.getField("invaliidi"));
    }

    @Test
    public void setOptionalFieldsWorksOnRightTypes() {
        instance = new Manual();

        instance.setField("author", "1");
        instance.setField("organization", "2");
        instance.setField("address", "Jollantie 22");
        instance.setField("month", "march");
        instance.setField("year", "2055");
        instance.setField("note", "helloworld");
        instance.setField("key", "ABCD1234");

        assertEquals("1", instance.getField("author"));
        assertEquals("2", instance.getField("organization"));
        assertEquals("2055", instance.getField("year"));
        assertEquals("march", instance.getField("month"));
        assertEquals("Jollantie 22", instance.getField("address"));
        assertEquals("helloworld", instance.getField("note"));
        assertEquals("ABCD1234", instance.getField("key"));
    }

    @Test
    public void removeFieldEmptiesProperly() {
        instance = new Manual();

        instance.setField("author", "Testerman");
        instance.removeField("author");
        assertEquals(null, instance.getField("author"));
    }

    @Test
    public void removeFieldReturnsTrue() {
        instance = new Manual();

        instance.setField("author", "Testerman");
        assertEquals(true, instance.removeField("author"));
    }

    @Test
    public void removeFieldFailsOnUnsetField() {
        instance = new Manual();

        assertEquals(false, instance.removeField("author"));
    }
    
    @Test
    public void setAndGetIDWork() {
        instance = new Manual();
        
        instance.setID("abcd1234");
        assertEquals("abcd1234", instance.getID());
    }
    
    @Test
    public void isNotEqualToOthers() {
        instance = new Manual();
        Manual comparable = new Manual();
        
        instance.setField("title", "qwegiohakg");
        comparable.setField("title", "ASC");

        boolean expResult = false;
        assertEquals(expResult, instance.equals(comparable));
        assertEquals(expResult, instance.equals(null));
        assertEquals(expResult, instance.hashCode() == comparable.hashCode());
    }
}
