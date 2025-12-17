package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDAO;
import com.tap.model.User;
import com.tap.util.DBConnection;

public class UserDAOImpl implements UserDAO {

	private static final String SELECT_ALL_USER_QUERY = "select * from `user`";
	private static final String SELECT_USER_QUERY = "select * from `user` where `userid`=?";

	private static final String INSERT_QUERY = 
		    "INSERT INTO user(`userName`, `password`, `email`, `phone`, `address`, `role`, `createdDate`, `LastLoginDate`) " +
		    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


	private static final String DELETE_QUERY = "delete from `user` where `userId` = ?";

	private static final String UPDATE_QUERY = "UPDATE user SET `userName` = ?, `password` = ?, `email` = ?, `phone` = ?, `address` = ?, `role` = ?, `createdDate` = ?, `lastLoginDate` = ? "
			+ "WHERE `userId` = ?";
	
	private static final String SELECT_USER_EMAIL_BASED_QUERY = "select * from `user` where `email`=? and password=?";
	private static final String GET_USER_BY_EMAIL_QUERY = "select * FROM user where email=?";
	private static final String UPADTE_PASSWORD_QUERY = "UPDATE user SET password=? WHERE email=?";
	

	// get the user
	public User getUser(int userId) {

		User user = null;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY);) {

			statement.setInt(1, userId);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				user = new User(resultSet.getInt("userId"), resultSet.getString("userName"),
						resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("phone"),
						resultSet.getString("address"), resultSet.getString("role"),
						resultSet.getTimestamp("createdDate"), resultSet.getTimestamp("LastLoginDate"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;

	}

	// insert the user
	public int addUser(User user) {
		
		int rowsAffected=0;
		
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);) {

			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getAddress());
			statement.setString(6, user.getRole());
			statement.setTimestamp(7, user.getCreatedDate());
			statement.setTimestamp(8, user.getLastLoginDate());

			rowsAffected = statement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rowsAffected;

	}

//	 update the user
	public boolean updateUser(User user) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getAddress());
			statement.setString(6, user.getRole());
			statement.setTimestamp(7, user.getCreatedDate());
			statement.setTimestamp(8, user.getLastLoginDate());
			statement.setInt(9, user.getUserId());

			return statement.executeUpdate() > 0;
//			System.out.println("Successfully " + rowsAffected + " user details updated.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	// delete the user
	public void deleteUser(int userId) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {

			statement.setInt(1, userId);
			int rowsAffected = statement.executeUpdate();
			System.out.println("Successfully " + rowsAffected + " user(" + userId + ") has deleted.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// get all user
	public List<User> getAllUser() {

		List<User> allUsers = new ArrayList<User>();
		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();) {

			ResultSet resultSet = statement.executeQuery(SELECT_ALL_USER_QUERY);

			while (resultSet.next()) {

				int userId = resultSet.getInt("userId");
				String userName = resultSet.getString("userName");
				String password = resultSet.getString("password");
				String email = resultSet.getString("email");
				String phone = resultSet.getString("phone");
				String address = resultSet.getString("address");
				String role = resultSet.getString("role");
				Timestamp createdDate = resultSet.getTimestamp("createdDate");
				Timestamp LastLoginDate = resultSet.getTimestamp("LastLoginDate");

				User user = new User(userId, userName, password, email, phone, address, role, createdDate,
						LastLoginDate);

				allUsers.add(user);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return allUsers;

	}
	//get user based on email
	
	@Override
	public User getUser(String email, String password) {
		User user=null;
		try(Connection connection = DBConnection.getConnection();PreparedStatement statement = connection.prepareStatement(SELECT_USER_EMAIL_BASED_QUERY);){
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int userId = resultSet.getInt("userId");
				String userName = resultSet.getString("userName");
				String UserPassword = resultSet.getString("password");
				String UserEmail = resultSet.getString("email");
				String phone = resultSet.getString("phone");
				String address = resultSet.getString("address");
				String role = resultSet.getString("role");
				Timestamp createdDate = resultSet.getTimestamp("createdDate");
				Timestamp LastLoginDate = resultSet.getTimestamp("LastLoginDate");

				user = new User(userId, userName, UserPassword, UserEmail, phone, address, role, createdDate,
						LastLoginDate);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;
		
	}
	
	
	
	
	
	
	
	
	
	public User getUserByEmail(String email) {
	    
	    try (Connection connection = DBConnection.getConnection();PreparedStatement statement = connection.prepareStatement(GET_USER_BY_EMAIL_QUERY);) {
	    	statement.setString(1, email);
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            User u = new User();
	            u.setUserId(rs.getInt("userId"));
	            u.setEmail(rs.getString("email"));
	            u.setPassword(rs.getString("password"));
	            return u;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public boolean updatePassword(String email, String newPass) {
	   
	    try (Connection connection = DBConnection.getConnection();PreparedStatement statement = connection.prepareStatement(UPADTE_PASSWORD_QUERY);) {
	    	statement.setString(1, newPass);
	    	statement.setString(2, email);
	        return statement.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	
	
	
}
