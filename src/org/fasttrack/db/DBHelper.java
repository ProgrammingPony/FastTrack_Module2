package org.fasttrack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author 1319288
 * Set these yourself
 */
public class DBHelper {
	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "system";
	private static final String PASS = "system";
	
	public static String getJdbcDriver() {
		return JDBC_DRIVER;
	}
	

	public static String getDbUrl() {
		return DB_URL;
	}
	
	public static String getUser() {
		return USER;
	}
	
	public static String getPass() {
		return PASS;
	}
	
	public static Connection getDatabaseConnection () { 
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					getDbUrl(),
					getUser(),
					getPass()
					);
		} catch (SQLException e) {
			System.out.println("DBHelper failed to create database connection");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
}
