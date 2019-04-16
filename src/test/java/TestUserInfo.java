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
 * Testing all database manager methods that involve user information.
 * 
 * Note: This test case requires that the database must be populated 
 * with data in the TheArmoryDML.sql file located in ./SQL_Scripts/.
 * 
 * @author Ryan Kirmis
 * @version 1.0.0
 */

public class TestUserInfo {
    static DatabaseManager dbMgr;

    /**
     * Setting up the database manager.
     * 
     * @throws Exception if connection fails
     */
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
     * Tests to see if a user exists in the database.
     * @throws InterruptedException 
     */
    @Test
    public void testUserExists() {
        boolean result = dbMgr.userExists("rkirmis");
        assertEquals(true, result);
        result = dbMgr.userExists("doesnotexist");
        assertEquals(false, result);
    }
    
    /**
     * Tests to see if user is a premium user.
     */
    @Test
    public void testCheckPremiumUser() {
        boolean result = dbMgr.checkPremiumUser("rkirmis");
        assertEquals(true, result);
        result = dbMgr.checkPremiumUser("doesnotexist");
        assertEquals(false, result);
    }

    /**
     * Tests to see if user is a review user.
     */
    @Test
    public void testCheckReviewUser() {
        boolean result = dbMgr.checkReviewUser("skywalker");
        assertEquals(true, result);
        result = dbMgr.checkReviewUser("doesnotexist");
        assertEquals(false, result);
    }
    
    /**
     * Tests to see if user is a banned user.
     */
    @Test
    public void testCheckBannedUser() {
        boolean result = dbMgr.checkBannedUser("ultron");
        assertEquals(true, result);
        result = dbMgr.checkBannedUser("doesnotexist");
        assertEquals(false, result);
    }
    
    /**
     * Tests to see if user is a system administrator.
     */
    @Test
    public void testCheckSystemAdmin() {
        boolean result = dbMgr.checkAdmin("bob");
        assertEquals(true, result);
        result = dbMgr.checkAdmin("doesnotexist");
        assertEquals(false, result);
    }
    
    /**
     * Tests to see if user has a specific platform.
     */
    @Test
    public void testCheckPlatform() {
        boolean result = dbMgr.checkUserPlatform("thanos", "Playstation 4");
        assertEquals(true, result);
        result = dbMgr.checkUserPlatform("thanos", "Xbox One");
        assertEquals(false, result);
    }
    
    /**
     * Tests to see if the user's balance is correct.
     */
    @Test
    public void testUserBalance() {
        float balance = dbMgr.userBalance("tracy");
        assertEquals(100.00, balance, 0);
    }
    
    /**
     * Tests to see if all user information is returned.
     */
    @Test
    public void testGetUserInfo() {
        List<String> userInfo = dbMgr.getUserInfo("john");
        
        assertTrue(userInfo.get(0).equals("john"));
        assertTrue(userInfo.get(1).equals("password12"));
        assertTrue(userInfo.get(2).equals("20.0"));
        assertTrue(userInfo.get(3).equals("Credit"));
        assertTrue(userInfo.get(4).equals("7777666655554444"));
        assertTrue(userInfo.get(5).equals("654"));
        assertTrue(userInfo.get(6).equals("2021-05-22"));
    }
    
    /**
     * Tests to see if user login works correctly.
     */
    @Test
    public void testUserLogin() {
        boolean result = dbMgr.login("mramirez", "password2");
        assertEquals(true, result);
        result = dbMgr.login("mramirez", "password3");
        assertEquals(false, result);
    }
    
    /**
     * Tests to see if getting user game titles returns correct strings.
     */
    @Test
    public void testGetGameTitles() {
        List<String> games = dbMgr.getGameTitles("george");
        
        assertTrue(games.get(0).equals("Tom Clancys The Division"));
    }
    
    /**
     * Tests to see if getting user platforms returns correct strings.
     */
    @Test
    public void testGetPlatforms() {
        List<String> platforms = dbMgr.getUserPlatforms("tstark");
        
        assertTrue(platforms.get(0).equals("PC"));
    }
}
