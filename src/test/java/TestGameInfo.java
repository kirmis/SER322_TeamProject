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
 * Testing all database manager methods that involve game information.
 * 
 * Note: This test case requires that the database must be populated 
 * with data in the TheArmoryDML.sql file located in ./SQL_Scripts/.
 * 
 * @author Ryan Kirmis
 * @version 1.0.0
 */

public class TestGameInfo {
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
     * Tests getting the price of a game.
     */
    @Test
    public void testGamePrice() {
        float price = dbMgr.gamePrice("Super Smash Bros. Ultimate");
        assertEquals(59.99, price, .01);
    }
    
    /**
     * Tests getting all games that a user does not own but has a platform for.
     */
    @Test
    public void testUserAvailableGames() {
        List<String> games = dbMgr.getAllGameTitles("thanos");
        
        assertTrue(games.get(0).equals("Anthem"));
        assertTrue(games.get(1).equals("Tom Clancys The Division"));
        assertTrue(games.get(2).equals("Call of Duty: Black Ops 4"));
    }

    /**
     * Tests getting a game's ID by its name.
     */
    @Test
    public void testGetGameIDByName() {
        String ID = dbMgr.getGameID("Anthem");
        
        assertTrue(ID.equals("11111"));
    }
    
    /**
     * Tests getting a game's information by its name.
     */
    @Test
    public void testGetGameInfoByName() {
        List<String> gameInfo = dbMgr.getGameInfoByName("Anthem");
        
        assertTrue(gameInfo.get(0).equals("11111"));
        assertTrue(gameInfo.get(1).equals("Anthem"));
        assertTrue(gameInfo.get(2).equals("2019-02-22"));
        assertTrue(gameInfo.get(3).equals("2019-02-22"));
        assertTrue(gameInfo.get(4).equals("59.99"));
        assertTrue(gameInfo.get(5).equals("12345"));
    }
    
    /**
     * Tests getting a game's information by its ID.
     */
    @Test
    public void testGetGameInfoByID() {
        List<String> gameInfo = dbMgr.getGameInfoByID("11111");
        
        assertTrue(gameInfo.get(0).equals("11111"));
        assertTrue(gameInfo.get(1).equals("Anthem"));
        assertTrue(gameInfo.get(2).equals("2019-02-22"));
        assertTrue(gameInfo.get(3).equals("2019-02-22"));
        assertTrue(gameInfo.get(4).equals("59.99"));
        assertTrue(gameInfo.get(5).equals("12345"));
    }
}
