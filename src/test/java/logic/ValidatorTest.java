/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidatorTest {
    
    Validator vali;
    
    public ValidatorTest() {
        vali = new Validator();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void validatorRefusesAlphaInNumerics() {
        String badYear = "aä";
        String badVolume = "aä";
        boolean expResult = false;
        assertEquals(expResult, vali.checkInput("year", badYear, true));
        assertEquals(expResult, vali.checkInput("volume", badVolume, true));
    }
    
    @Test
    public void validatorRefusesUnknownType() {
        String badType = "volmme";
        String badValue = "1234ABCD";
        boolean expResult = false;
        
        assertEquals(expResult, vali.checkInput(badType, badValue, true));
    }
}
