package com.tap.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/foodieexpress";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Tamil@1427";
	private static Connection connection;

	public static Connection getConnection() {
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish the connection
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	

}
