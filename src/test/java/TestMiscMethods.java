package test.java;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.DatabaseManager;

/**
 * Tests all database manager methods that involve retrieving information
 * other than User/Game records.
 * 
 * Note: This test case requires that the database must be populated 
 * with data in the TheArmoryDML.sql file located in ./SQL_Scripts/.
 * 
 * @author Ryan Kirmis
 * @version 1.0.0
 */

public class TestMiscMethods {
    static DatabaseManager dbMgr;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dbMgr = new DatabaseManager();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        dbMgr = null;
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Tests getting review information from the ID of a game.
     */
    @Test
    public void testReviewsByGame() {
        List<List<String>> reviewsInfo = dbMgr.getReviewsByGame("11111");
        
        assertTrue(reviewsInfo.get(0).get(0).equals("Anthem Review"));
        assertTrue(reviewsInfo.get(0).get(1).equals("7.2"));
        assertTrue(reviewsInfo.get(0).get(2).equals("11111"));
        assertTrue(reviewsInfo.get(0).get(3).equals("mchief"));
        assertTrue(reviewsInfo.get(0).get(4).equals("2019-02-22"));
    }

    /**
     * Tests getting a publisher by the game ID of the game it publishes.
     */
    @Test
    public void testGetPublisherByGame() {
        List<String> publisher = dbMgr.getPublisherByGame("22222");
        
        assertTrue(publisher.get(0).equals("23456"));
        assertTrue(publisher.get(1).equals("Ubisoft"));
        assertTrue(publisher.get(2).equals("Montreuil, France"));
    }
    
    /**
     * Tests getting all developers of a game (by the game's ID).
     */
    @Test
    public void testGetDevelopersByGame() {
        List<List<String>> developers = dbMgr.getDevelopersByGame("33333");
        
        assertTrue(developers.get(0).get(0).equals("88888"));
        assertTrue(developers.get(0).get(1).equals("BANDAI NAMCO"));
        assertTrue(developers.get(0).get(2).equals("76543"));
    }
}
