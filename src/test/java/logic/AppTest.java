/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import database.DAO;
import database.ReferenceDAO;
import io.IO;
import io.StubIO;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mrreflex
 */
public class AppTest {
    
    private App app;
    private IO stubIo;
    
    public AppTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void closingWorks() {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("n");
        StubIO io = new StubIO(inputLines);
        App ap = new App(io, new ReferenceDAO());
        ap.run();
        assertTrue(io.getPrints().contains("No articles in memory"));
    }
    
    @Test
    public void incorrectReferenceDoesntWork() {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("y");
        inputLines.add("kissa");
        inputLines.add("n");
        StubIO io = new StubIO(inputLines);
        App ap = new App(io, new ReferenceDAO());
        ap.run();
        assertTrue(io.getPrints().contains("Invalid reference type"));
    }
    
    @Test
    public void emptyInputNotAccepted() {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("y");
        inputLines.add("1");
        inputLines.add("");
        inputLines.add("n");
        StubIO io = new StubIO(inputLines);
        App ap = new App(io, new ReferenceDAO());
        ap.run();
        assertTrue(io.getPrints().contains("Invalid author"));
    }
}
