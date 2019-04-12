package main.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Manages connections to TheArmory database.
 * 
 * @author Alper Mencek, Manolito Ramirez, and Ryan Kirmis
 * @version 1.0.0
 */

public class DatabaseManager {
    ConnectionPool connPool = null;
    Properties queries;

    /**
     * Setting up database manager and connections to database.
     */
    public DatabaseManager() {
        connPool = ConnectionPool.getInstance(); // setting up connection pool

        // reading in queries from queries.properties
        queries = new Properties();
        try {
            // loading queries properties file
            queries.load(this.getClass().getResourceAsStream("/util/queries.properties"));
        } catch (IOException e1) {
            System.out.println("ERROR: Unable to access queries.properties file.");
            e1.printStackTrace();
        }
    }

    /**
     * Inserts a new user into the database.
     * 
     * @param username the username
     * @param password the password of the user
     * @param balance the user's balance
     * @param cardType the user's card type (debit, credit, etc.)
     * @param cardNum the card number
     * @param securityCode the security code of the user's card
     * @param expDate the expiration date of the card
     * @return boolean value whether transaction was successful
     */
    public boolean insertUser(String username, String password, double balance, 
            String cardType, String cardNum, String securityCode, Date expDate) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = true;

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("INSERT_USER"));
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setDouble(3, balance);
            stmt.setString(4, cardType);
            stmt.setString(5, cardNum);
            stmt.setString(6, securityCode);
            stmt.setDate(7, expDate);

            rs = stmt.executeQuery();
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
            result = false; // set return to false
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Inserts a new premium user into the database.
     * 
     * @param username foreign key to user
     * @param discountedGames boolean for whether user has access to discounted games
     * @param playOnline boolean for whether user can play online
     * @param renewalDate renewal date for user's premium membership
     * @return boolean value whether transaction was successful
     */
    public boolean insertPremiumUser(String username, boolean discountedGames, boolean playOnline, 
            Date renewalDate) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = true;

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("INSERT_PREMIUM_USER"));
            stmt.setString(1, username);
            stmt.setBoolean(2, discountedGames);
            stmt.setBoolean(3, playOnline);
            stmt.setDate(4, renewalDate);

            rs = stmt.executeQuery();
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
            result = false; // set return to false
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Inserts a new review user into the database.
     * 
     * @param username foreign key to user
     * @param earlyAccess boolean for whether user has early access
     * @return boolean value whether transaction was successful
     */
    public boolean insertReviewUser(String username, boolean earlyAccess) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = true;

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("INSERT_REVIEW_USER"));
            stmt.setString(1, username);
            stmt.setBoolean(2, earlyAccess);

            rs = stmt.executeQuery();
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
            result = false; // set return to false
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Inserts a new banned user into the database.
     * 
     * @param username foreign key to user
     * @param bannedBranding the banned branding
     * @param bannedDesription the description for being banned
     * @return boolean value whether transaction was successful
     */
    public boolean insertBannedUser(String username, boolean bannedBranding, String bannedDesription) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = true;

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("INSERT_BANNED_USER"));
            stmt.setString(1, username);
            stmt.setBoolean(2, bannedBranding);
            stmt.setString(3, bannedDesription);

            rs = stmt.executeQuery();
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
            result = false; // set return to false
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Inserts a new system administrator into the database.
     * 
     * @param username foreign key to user
     * @return boolean value whether transaction was successful
     */
    public boolean insertSystemAdmin(String username) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = true;

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("INSERT_SYSTEM_ADMIN"));
            stmt.setString(1, username);

            rs = stmt.executeQuery();
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
            result = false; // set return to false
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Inserts a new game into the database.
     * 
     * @param gameID the game ID
     * @param gameName the name of the game
     * @param releaseDate the release date of the game
     * @param reviewReleaseDate the release date of the review for the game
     * @param price the price of the game
     * @param publisherID the ID of the publisher of the game
     * @return boolean value whether transaction was successful
     */
    public boolean insertGame(String gameID, String gameName, String releaseDate, 
            String reviewReleaseDate, double price, String publisherID) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = true;

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("INSERT_GAME"));
            stmt.setString(1, gameID);
            stmt.setString(2, gameName);
            stmt.setString(3, releaseDate);
            stmt.setString(4, reviewReleaseDate);
            stmt.setDouble(5, price);
            stmt.setString(6, publisherID);

            rs = stmt.executeQuery();
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
            result = false; // set return to false
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Adds a game to user's owned games.
     * 
     * @param username the username 
     * @param gameID the ID of the game being added to the user
     * @return boolean value whether transaction was successful
     */
    public boolean addGameToUser(String username, String gameID) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = true;

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("INSERT_OWNS"));
            stmt.setString(1, username);
            stmt.setString(2, gameID);

            rs = stmt.executeQuery();
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
            result = false; // set return to false
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Inserts a new review into the database.
     * 
     * @param title the title of the review
     * @param rating the rating of the review
     * @param gameID the ID of the game being reviewed
     * @param reviewerUsername the username of the user who created the review
     * @param datePosted the date the review was posted
     * @return boolean value whether transaction was successful
     */
    public boolean insertReview(String title, double rating, String gameID, String reviewerUsername,
            Date datePosted) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = true;

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("INSERT_REVIEW"));
            stmt.setString(1, title);
            stmt.setDouble(2, rating);
            stmt.setString(3, gameID);
            stmt.setString(4, reviewerUsername);
            stmt.setDate(5, datePosted);

            rs = stmt.executeQuery();
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
            result = false; // set return to false
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Adds a platform to a user's owned platforms.
     * 
     * @param username the username
     * @param platformType the type of platform
     * @return boolean value whether transaction was successful
     */
    public boolean addPlatformToUser(String username, String platformType) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = true;

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("INSERT_USES"));
            stmt.setString(1, username);
            stmt.setString(2, platformType);

            rs = stmt.executeQuery();
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
            result = false; // set return to false
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Gets all game titles that a user owns.
     * 
     * @param username the username
     * @return list of game titles
     */
    public List<String> getGameTitles(String username) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> userGames = new ArrayList<String>();

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("GET_USER_GAME_TITLES"));
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            while (rs.next()) { // add the game titles to a list of strings
                userGames.add(rs.getString(1));
            }
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return userGames;
    }

    /**
     * Gets all ID of a game from the name.
     * 
     * @param username the username
     * @return the ID of the game
     */
    public String getGameID(String title) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String gameID = "";

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("GET_GAME_ID_BY_NAME"));
            stmt.setString(1, title);

            rs = stmt.executeQuery();

            while (rs.next()) { // add the game titles to a list of strings
                gameID = rs.getString(1);
            }
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return gameID;
    }

    /**
     * Returns all of a game's attributes by game name.
     * 
     * @param gameName the name of the game
     * @return list containing all the information for a game (separated by tabs)
     */
    public List<String> getGameInfoByName(String gameName) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> gameInfo = new ArrayList<String>();

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("GET_GAME_INFO_BY_NAME"));
            stmt.setString(1, gameName);

            rs = stmt.executeQuery();

            while (rs.next()) {
                gameInfo.add(rs.getString(1));
                gameInfo.add(rs.getString(2));
                gameInfo.add(rs.getString(3));
                gameInfo.add(rs.getString(4));
                gameInfo.add(rs.getString(5));
                gameInfo.add(rs.getString(6));
            }
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return gameInfo;
    }

    /**
     * Returns all of a game's attributes by game ID.
     * 
     * @param gameID the ID of the game
     * @return list containing all the information for a game (separated by tabs)
     */
    public List<String> getGameInfoByID(String gameID) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> gameInfo = new ArrayList<String>();

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("GET_GAME_INFO_BY_ID"));
            stmt.setString(1, gameID);

            rs = stmt.executeQuery();

            while (rs.next()) {
                gameInfo.add(rs.getString(1));
                gameInfo.add(rs.getString(2));
                gameInfo.add(rs.getString(3));
                gameInfo.add(rs.getString(4));
                gameInfo.add(rs.getString(5));
                gameInfo.add(rs.getString(6));
            }
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return gameInfo;
    }

    /**
     * Returns the publisher of a specific game.
     * 
     * @param gameID the ID of the specific game
     * @return list containing all the information for a publisher
     */
    public List<String> getPublisherByGame(String gameID) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> pubInfo = new ArrayList<String>(); // list for publisher information

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("GET_PUBLISHER_BY_GAME"));
            stmt.setString(1, gameID);

            rs = stmt.executeQuery();

            while (rs.next()) {
                pubInfo.add(rs.getString(1));
                pubInfo.add(rs.getString(2));
                pubInfo.add(rs.getString(3));
            }
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return pubInfo;
    }

    /**
     * Returns the reviews for a specific game.
     * 
     * @param gameID the ID of the specific game
     * @return list containing all the information for a publisher
     */
    public List<List<String>> getReviewsByGame(String gameID) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<List<String>> revInfo = new ArrayList<List<String>>(); // list for publisher information

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("GET_REVIEW_BY_GAME"));
            stmt.setString(1, gameID);

            rs = stmt.executeQuery();

            while (rs.next()) {
                ArrayList<String> currReview = new ArrayList<String>();
                currReview.add(rs.getString(1));
                currReview.add(rs.getString(2));
                currReview.add(rs.getString(3));
                currReview.add(rs.getString(4));
                currReview.add(rs.getString(5));
                revInfo.add(currReview);
            }
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return revInfo;
    }

    public boolean login(String username, String password) {
        // declaring connections
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean login = false;

        try {
            conn = connPool.getConnection(); // get new connection

            stmt = conn.prepareStatement((String) queries.get("GET_USER_LOGIN"));
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            while (rs.next()) { // add the game titles to a list of strings
                login = true;
            }
        } 
        catch (SQLException e1) {
            System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
            e1.printStackTrace();
        } 
        finally {
            try {
                // closing connections
                if (conn != null) conn.close();
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } 
            catch (SQLException e1) {
                System.out.println("ERROR: Connection to database could not be closed");
                e1.printStackTrace();
            }
        }

        return login;
    }
}
