package main.java;

import java.io.IOException;
import java.sql.Connection;
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
		connPool = ConnectionPool.getInstance();
		
		// reading in queries from queries.properties
		queries = new Properties();
		try {
			queries.load(this.getClass().getResourceAsStream("/util/queries.properties"));
		} catch (IOException e1) {
			System.out.println("ERROR: Unable to access queries.properties file.");
			e1.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getGameTitles(String username) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> userGames = new ArrayList<String>();
		
		try {
			conn = connPool.getConnection();
			
			stmt = conn.prepareStatement((String) queries.get("GET_GAMES_FOR_USER"));
			stmt.setString(1, username);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				userGames.add(rs.getString(1));
			}
		} 
		catch (SQLException e1) {
			System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
			e1.printStackTrace();
		} 
		finally {
			try {
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
	
	public String getGameInfo(String gameName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String gameInfo = "";
		
		try {
			conn = connPool.getConnection();
			
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
	 * 
	 * @return
	 */
	public boolean insertUser() {
		Connection conn = null;
		
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean insertPremiumUser() {
		return false;
	}
}
