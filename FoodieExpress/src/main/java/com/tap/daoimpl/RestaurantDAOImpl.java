package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {
	// addQuery
	private final static String INSER_QUERY = "insert into restaurant(`restaurantId`, `restaurantName`, `address`, `phone`, `rating`, `cuisineType`, `eta`, `adminUserId`, `imagePath`, `isActive`)"
			+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	// deleteQuery
	private final static String DELETE_QUERY = "delete from `restaurant` where `restaurantId` = ?";

	// getQuery
	private final static String SELECT_RESTAURANT_QUERY = "select * from `restaurant` where `restaurantId` = ?";

	private final static String SELECT_ALL_RESTAURANT_QUERY = "select * from `restaurant`";
	
	//updateQuery
	private final static String UPDATE_QUERY = "update `restaurant` set `restaurantId`=?, `restaurantName`=?, `address` =?, `phone`=?, `rating`=?, `cuisineType`=?, `eta`=?, `adminUserId`=?, `imagePath`=?, `isActive`=?"
			+ " where `restaurantId`=?";
			

//	Add RestaurantServlet
	@Override
	public void addRestaurant(Restaurant restaurant) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSER_QUERY);) {
			statement.setInt(1, restaurant.getRestaurantId());
			statement.setString(2, restaurant.getRestaurantName());
			statement.setString(3, restaurant.getAddress());
			statement.setString(4, restaurant.getPhone());
			statement.setDouble(5, restaurant.getRating());
			statement.setString(6, restaurant.getCuisineType());
			statement.setInt(7, restaurant.getEta());
			statement.setInt(8, restaurant.getAdminUserId());
			statement.setString(9, restaurant.getImagePath());
			statement.setBoolean(10, restaurant.isActive());

			int rowsAffected = statement.executeUpdate();
			System.out.println("Successfully " + rowsAffected + " RestaurantServlet details inserted.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
// delete RestaurantServlet
	@Override
	public void deleteRestaurant(int restaurantId) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
			statement.setInt(1, restaurantId);

			int rowsAffected = statement.executeUpdate();
			System.out.println("Successfully " + rowsAffected + " RestaurantServlet(" + restaurantId + ") has deleted.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
// Update RestaurantServlet
	@Override
	public void updateRestaurant(Restaurant restaurant) {
		
		try(Connection connection = DBConnection.getConnection();PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);){
			statement.setInt(1, restaurant.getRestaurantId());
			statement.setString(2, restaurant.getRestaurantName());
			statement.setString(3, restaurant.getAddress());
			statement.setString(4, restaurant.getPhone());
			statement.setDouble(5, restaurant.getRating());
			statement.setString(6, restaurant.getCuisineType());
			statement.setInt(7, restaurant.getEta());
			statement.setInt(8, restaurant.getAdminUserId());
			statement.setString(9, restaurant.getImagePath());
			statement.setBoolean(10, restaurant.isActive());
			statement.setInt(11, restaurant.getRestaurantId());
			
			int rowsAffected = statement.executeUpdate();
			System.out.println("Successfully " + rowsAffected + " RestaurantServlet(" + restaurant.getRestaurantId() + ") has updated.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	get RestaurantServlet
	@Override
	public Restaurant getRestaurant(int restaurantId) {
		Restaurant restaurant = null;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_RESTAURANT_QUERY);) {
			statement.setInt(1, restaurantId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				restaurant = new Restaurant(resultSet.getInt("restaurantId"), resultSet.getString("restaurantName"),
						resultSet.getString("address"), resultSet.getString("phone"), resultSet.getDouble("rating"),
						resultSet.getString("cuisineType"), resultSet.getInt("eta"), resultSet.getInt("adminUserId"),
						resultSet.getString("imagePath"), resultSet.getBoolean("isActive"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurant;
	}

	//get All RestaurantServlet
	@Override
	public List<Restaurant> getAllRestaurant() {

		List<Restaurant> allRestaurant = new ArrayList<>();
		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_RESTAURANT_QUERY);

			while (resultSet.next()) {
				Restaurant restaurant = new Restaurant(resultSet.getInt("restaurantId"),
						resultSet.getString("restaurantName"), resultSet.getString("address"),
						resultSet.getString("phone"), resultSet.getDouble("rating"), resultSet.getString("cuisineType"),
						resultSet.getInt("eta"), resultSet.getInt("adminUserId"), resultSet.getString("imagePath"),
						resultSet.getBoolean("isActive"));
				allRestaurant.add(restaurant);

			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return allRestaurant;

	}
}
