package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

	private final static String INSERT_QUERY = "insert into `Menu` (`menuId`, `restaurantId`, `itemName`, `description`, `price`, `rating`, `isAvailable`, `imagePath`)"
			+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String UPDATE_QUERY =
		    "UPDATE `Menu` SET `restaurantId`=?, `itemName`=?, `description`=?, `price`=?, `rating`=?, `isAvailable`=?, `imagePath`=? " +
		    "WHERE `menuId`=?";


	private final static String DELELTE_QUERY = "delete from `menu` where `menuId`=?";

	private final static String SELELCT_QUERY = "select * from `menu` where `menuId`=?";

	private final static String SELECT_ALL_MENU_QUERY = "select * from `menu`";
	
	private final static String SELECT_ALL_MENU_BY_RESTAURANT_ID_QUERY="select * from `menu` where `restaurantId` =?";
	
	
	
	@Override
	public List<Menu> getAllMenuByRestaurantId(int restaurantId) {
		
		List<Menu> allMenu = new ArrayList<>();
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MENU_BY_RESTAURANT_ID_QUERY);) {
			
			statement.setInt(1, restaurantId);
			ResultSet resultSet = statement.executeQuery();
			
			
			while (resultSet.next()) {

				Menu menu = new Menu(resultSet.getInt("menuId"), resultSet.getInt("restaurantId"),
						resultSet.getString("itemName"), resultSet.getString("description"),
						resultSet.getDouble("price"), resultSet.getDouble("rating"),
						resultSet.getBoolean("isAvailable"), resultSet.getString("imagePath"));
				
				allMenu.add(menu);
			}
	
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return allMenu;
		
	}

	// insert menu
	@Override
	public void addMenu(Menu menu) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);) {

			statement.setInt(1, menu.getMenuId());
			statement.setInt(2, menu.getRestaurantId());
			statement.setString(3, menu.getItemName());
			statement.setString(4, menu.getDescription());
			statement.setDouble(5, menu.getPrice());
			statement.setDouble(6, menu.getRating());
			statement.setBoolean(7, menu.isAvailable());
			statement.setString(8, menu.getImagePath());

			int rowsAffected = statement.executeUpdate();
			System.out.println("Successfully " + rowsAffected + " Menu details inserted.");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// update menu
	@Override
	public void updateMenu(Menu menu) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
			statement.setInt(1, menu.getRestaurantId());
			statement.setString(2, menu.getItemName());
			statement.setString(3, menu.getDescription());
			statement.setDouble(4, menu.getPrice());
			statement.setDouble(5, menu.getRating());
			statement.setBoolean(6, menu.isAvailable());
			statement.setString(7, menu.getImagePath());
			statement.setInt(8, menu.getMenuId());
;

			int rowsAffected = statement.executeUpdate();
			System.out.println("Successfully " + rowsAffected + " Menu(" + menu.getMenuId() + ") has updated.");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// delete menu
	@Override
	public void deleteMenu(int menuId) {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELELTE_QUERY);) {
			statement.setInt(1, menuId);
			int rowsAffected = statement.executeUpdate();
			System.out.println("Successfully " + rowsAffected + " Menu(" + menuId + ") has deleted.");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// select query
	@Override
	public Menu getMenu(int menuId) {

		Menu menu = null;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELELCT_QUERY);) {
			statement.setInt(1, menuId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				menu = new Menu(resultSet.getInt("menuId"), resultSet.getInt("restaurantId"),
						resultSet.getString("itemName"), resultSet.getString("description"),
						resultSet.getDouble("price"), resultSet.getDouble("rating"),
						resultSet.getBoolean("isAvailable"), resultSet.getString("imagePath"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return menu;
	}

//	select all menu
	@Override
	public List<Menu> getAllMenu() {

		List<Menu> allMenu = new ArrayList<>();
		try (Connection connection = DBConnection.getConnection();
				Statement statement=connection.createStatement();) {
			
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_MENU_QUERY);
			
			while (resultSet.next()) {

				Menu menu = new Menu(resultSet.getInt("menuId"), resultSet.getInt("restaurantId"),
						resultSet.getString("itemName"), resultSet.getString("description"),
						resultSet.getDouble("price"), resultSet.getDouble("rating"),
						resultSet.getBoolean("isAvailable"), resultSet.getString("imagePath"));
				
				allMenu.add(menu);
			}
	
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return allMenu;

	}

}
