package main;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import static main.Main.testMe;


public class TestMain {
    
    public TestMain() {
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


    @Test
    public void Mainluokkatoimii() {
        String hello = "Moi t. CodeTeddies";
        assertEquals(hello, testMe("Moi t. CodeTeddies"));
    }
}
