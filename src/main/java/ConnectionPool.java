package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
	private static ConnectionPool singleInstance;
	private Stack<Connection> conns;
	
	private ConnectionPool() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e1) {
			System.out.println("ERROR: Driver class not found. DatabaseManager not created.");
			e1.printStackTrace();
		} catch (InstantiationException e3) {
			System.out.println("ERROR: Driver could not be instantiated.");
			e3.printStackTrace();
		} catch (IllegalAccessException e4) {
			System.out.println("ERROR: Driver could not be accessed.");
			e4.printStackTrace();
		}
	}
	
	public ConnectionPool getInstance() {
		if (singleInstance == null)
			return new ConnectionPool();
		
		return singleInstance;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		if (!conns.isEmpty()) {
			return conns.pop();
		} else {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TheArmory", "user", "password");
			} catch (SQLException e1) {
				System.out.println("ERROR: Could not retrieve connection to TheArmory database.");
				e1.printStackTrace();
			}
		}
		
		return conn;
	}
	
	public void releaseConnection(Connection conn) {
		conns.push(conn);
	}
}
