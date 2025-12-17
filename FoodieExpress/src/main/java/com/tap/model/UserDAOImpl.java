//package com.tap.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class UserDAOImpl {
//
//	private static final String URL = "jdbc:mysql://localhost:3306/foodieexpress";
//	private static final String USERNAME = "root";
//	private static final String PASSWORD = "Tamil@1427";
//	private static Connection connection;
//
//	private static final String SELECT_ALL_USER_QUERY = "select * from `user`";
//	private static final String SELECT_USER_QUERY = "select * from `user` where `userid`=?";
//
//	private static final String INSERT_QUERY = "insert into user(`userId`, `userName`, `password`, `email`, `phone`, `address`, `role`, `createdDate`, `LastLoginDate`) values"
//			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//
//	private static final String DELETE_QUERY = "delete from `user` where `userId` = ?";
//
//	private static final Scanner scan = new Scanner(System.in);
//
//	// main method
//	public static void main(String[] agrs) {
//		try {
//			UserDAOImpl dao = new UserDAOImpl();
//			List<User> allUser = getAllUsers();
//			for (User user : allUser) {
//
//				System.out.println(user.getUserId() + " " + user.getUserName() + " " + user.getPassword() + " "
//						+ user.getEmail() + " " + user.getPhone() + " " + user.getAddress() + " " + user.getRole() + " "
//						+ user.getCreatedDate() + " " + user.getLastLoginDate());
//
//			}
//
//			User user = getUser();
//			System.out.println(user.getUserId() + " " + user.getUserName() + " " + user.getPassword() + " "
//					+ user.getEmail() + " " + user.getPhone() + " " + user.getAddress() + " " + user.getRole() + " "
//					+ user.getCreatedDate() + " " + user.getLastLoginDate());
//			
//			
//			insert();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//// 2steps
//	public UserDAOImpl() {
//		try {
//			// load the driver
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			// Establish the connection
//			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	// select all users
//	public static List<User> getAllUsers() throws SQLException {
//
//		Statement statement = connection.createStatement();
//		ResultSet resultSet = statement.executeQuery(SELECT_ALL_USER_QUERY);
//		List<User> allUsers = new ArrayList<User>();
//
//		while (resultSet.next()) {
//
//			int userId = resultSet.getInt("userId");
//			String userName = resultSet.getString("userName");
//			String password = resultSet.getString("password");
//			String email = resultSet.getString("email");
//			int phone = resultSet.getInt("phone");
//			String address = resultSet.getString("address");
//			String role = resultSet.getString("role");
//			String createdDate = resultSet.getString("createdDate");
//			String LastLoginDate = resultSet.getString("LastLoginDate");
//
//			User user = new User(userId, userName, password, email, phone, address, role, createdDate, LastLoginDate);
//
//			allUsers.add(user);
//			
//		}
//		return allUsers;
//
//	}
//
//	// select specific user
//	public static User getUser() throws SQLException {
//
//		PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY);
//		System.out.println("Entre the user id");
//		int userId = scan.nextInt();
//		statement.setInt(1, userId);
//
//		ResultSet resultSet = statement.executeQuery();
//		User user = null;
//		while (resultSet.next()) {
//			user = new User(resultSet.getInt("userId"), resultSet.getString("userName"),
//					resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("phone"),
//					resultSet.getString("address"), resultSet.getString("role"), resultSet.getString("createdDate"),
//					resultSet.getString("LastLoginDate"));
//		}
//		return user;
//	}
//
//	// insert user
//	public static void insert() throws SQLException {
//
//		PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
//
//		String choice = null;
//		do {
//			User user = User.getUserDetails();
//
//			statement.setInt(1, user.getUserId());
//			statement.setString(2, user.getUserName());
//			statement.setString(3, user.getPassword());
//			statement.setString(4, user.getEmail());
//			statement.setInt(5, user.getPhone());
//			statement.setString(6, user.getAddress());
//			statement.setString(7, user.getRole());
//			statement.setString(8, user.getCreatedDate());
//			statement.setString(9, user.getLastLoginDate());
//
//			int rowsAffected = statement.executeUpdate();
//			System.out.println("Successfully " + rowsAffected + " user details inserted.");
//			System.out.println("do you want insert more user details ? (yes/no)");
//			choice = scan.next();
//		} while (choice.equalsIgnoreCase("yes"));
//
//	}
//
//	// delete user
//	public static void deleteUser() throws SQLException {
//
//		PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
//
//		System.out.println("Enter the user id");
//		int userId = scan.nextInt();
//		statement.setInt(1, userId);
//		int rowsAffected = statement.executeUpdate();
//		System.out.println("Successfully " + rowsAffected + " user(" + userId + ") has deleted.");
//
//	}
//
//	// update user
//	public static void updateUser() {
//
//	}
//
//}
