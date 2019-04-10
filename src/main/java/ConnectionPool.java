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
			conns = new Stack<Connection>();
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
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
	
	public static ConnectionPool getInstance() {
		if (singleInstance == null)
			return new ConnectionPool();
		
		return singleInstance;
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		
		if (!conns.isEmpty()) {
			return conns.pop();
		} else {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TheArmory", "armoryuser", "pass");
		}
		
		return conn;
	}
	
	public void releaseConnection(Connection conn) {
		conns.push(conn);
	}
}
