package main.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Stack;

/**
 * Connection pool for saving and holding connections in a pool.
 * 
 * @author Alper Mencek, Manolito Ramirez, and Ryan Kirmis
 * @version 1.0.0
 */

public class ConnectionPool {
	private static ConnectionPool singleInstance;
	private Stack<Connection> conns;
	private Properties config;
	
	/**
	 * Setting up connection pool.
	 */
	private ConnectionPool() {
		try {
			// loading configuration properties file
			config = new Properties();
			config.load(this.getClass().getResourceAsStream("/util/config.properties"));
			
			// setting up pool
			conns = new Stack<Connection>();
			
			// setting up database driver
			Class.forName((String)config.get("DRIVER")).newInstance();
		} catch (ClassNotFoundException e1) {
			System.out.println("ERROR: Driver class not found. DatabaseManager not created.");
			e1.printStackTrace();
		} catch (IOException e2) {
			System.out.println("ERROR: Could not load configuration properties file.");
			e2.printStackTrace();
		} catch (InstantiationException e3) {
			System.out.println("ERROR: Driver could not be instantiated.");
			e3.printStackTrace();
		} catch (IllegalAccessException e4) {
			System.out.println("ERROR: Driver could not be accessed.");
			e4.printStackTrace();
		}
	}
	
	/**
	 * Getting singleton of connection pool.
	 * 
	 * @return the singleton
	 */
	public static ConnectionPool getInstance() {
		if (singleInstance == null)
			return new ConnectionPool();
		
		return singleInstance;
	}
	
	/**
	 * Returns either a new connection or an unused connection in the pool.
	 * 
	 * @return the connection
	 * @throws SQLException if connection fails
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		
		if (!conns.isEmpty()) {
			return conns.pop();
		} else {
			conn = DriverManager.getConnection("jdbc:mysql://" + config.get("HOST") + ":" +
					config.get("PORT") + "/" + config.get("DATABASE"), (String) config.get("USERNAME"), 
					(String) config.get("PASSWORD"));
		}
		
		return conn;
	}
	
	/**
	 * Adds a connection back to the pool.
	 * 
	 * @param conn the connection being added
	 */
	public void releaseConnection(Connection conn) {
		conns.push(conn);
	}
}
