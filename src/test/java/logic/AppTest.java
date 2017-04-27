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
    private DAO mockDao;
    
    public AppTest() {
    }
    
    @Before
    public void setUp() {
        mockDao = new DAO() {

            @Override
            public void delete(Object key) {
            }

            @Override
            public void clearDatabase() {
            }

            @Override
            public void add(Object key) {
            }

            @Override
            public void update(Object key) {
            }

            @Override
            public List getAll() {
                List list = new ArrayList();
                return list;
            }
        };
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void closingWorks() {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("print");
        inputLines.add("quit");
        StubIO io = new StubIO(inputLines);
        App ap = new App(io, mockDao);
        ap.run();
        assertTrue(io.getPrints().contains("No articles in memory"));
    }
    
    @Test
    public void incorrectReferenceDoesntWork() {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("add");
        inputLines.add("kissa");
        inputLines.add("quit");
        StubIO io = new StubIO(inputLines);
        App ap = new App(io, mockDao);
        ap.run();
        assertTrue(io.getPrints().contains("Invalid reference type"));
    }
    
    @Test
    public void emptyInputNotAccepted() {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("add");
        inputLines.add("1");
        inputLines.add("");
        inputLines.add("quit");
        StubIO io = new StubIO(inputLines);
        App ap = new App(io, mockDao);
        ap.run();
        assertTrue(io.getPrints().contains("Invalid author"));
    }
}
