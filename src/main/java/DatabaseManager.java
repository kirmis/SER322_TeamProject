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
 * @author Ryan Kirmis, Alper Mencek, and Manolito Ramirez
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
			
			stmt = conn.prepareStatement((String) queries.get("GET_GAMES_FOR_USER"));
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
	 * Returns all of a game's attributes by game name.
	 * 
	 * @param gameName the name of the game
	 * @return string containing all the information for a game (separated by tabs)
	 */
	public String getGameInfoByName(String gameName) {
		// declaring connections
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String gameInfo = "";
		
		try {
			conn = connPool.getConnection(); // get new connection
			
			stmt = conn.prepareStatement((String) queries.get("GET_GAME_INFO_BY_NAME"));
			stmt.setString(1, gameName);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				gameInfo = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +
						rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6);
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
	 * @return string containing all the information for a game (separated by tabs)
	 */
	public String getGameInfoByID(String gameID) {
		// declaring connections
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String gameInfo = "";
		
		try {
			conn = connPool.getConnection(); // get new connection
			
			stmt = conn.prepareStatement((String) queries.get("GET_GAME_INFO_BY_ID"));
			stmt.setString(1, gameID);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				gameInfo = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" +
						rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6);
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
	 * @param userID foreign key to user
	 * @param discountedGames boolean for whether user has access to discounted games
	 * @param playOnline boolean for whether user can play online
	 * @param renewalDate renewal date for user's premium membership
	 * @return boolean value whether transaction was successful
	 */
	public boolean insertPremiumUser(String userID, boolean discountedGames, boolean playOnline, 
			Date renewalDate) {
		// declaring connections
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean result = true;
		
		try {
			conn = connPool.getConnection(); // get new connection
			
			stmt = conn.prepareStatement((String) queries.get("INSERT_PREMIUM_USER"));
			stmt.setString(1, userID);
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
}
