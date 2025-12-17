package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    // ✅ FIXED INSERT
    private static final String INSERT_QUERY =
        "INSERT INTO orderitem (orderId, menuId, itemName, imagePath, quantity, totalPrice) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    // ✅ FIXED table & column name
    private static final String DELETE_QUERY =
        "DELETE FROM orderitem WHERE orderItemId=?";

    // ✅ FIXED typo + quotes
    private static final String UPDATE_QUERY =
        "UPDATE orderitem SET orderId=?, menuId=?, quantity=?, totalPrice=? WHERE orderItemId=?";

    private static final String SELECT_ORDERITEM_QUERY =
        "SELECT * FROM orderitem WHERE orderItemId=?";

    private static final String SELECT_ALL_ORDER_QUERY =
        "SELECT * FROM orderitem";

    // ================= INSERT =================
    @Override
    public void addOrderItem(OrderItem orderItem) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {

            // ✅ CORRECT INDEX ORDER
            statement.setInt(1, orderItem.getOrderId());
            statement.setInt(2, orderItem.getMenuId());
            statement.setString(3, orderItem.getItemName());
            statement.setString(4, orderItem.getImagePath());
            statement.setInt(5, orderItem.getQuantity());
            statement.setDouble(6, orderItem.getTotalPrice());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Successfully " + rowsAffected + " OrderItem inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    @Override
    public void deleteOrderItem(int orderItemId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {

            statement.setInt(1, orderItemId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setInt(1, orderItem.getOrderId());
            statement.setInt(2, orderItem.getMenuId());
            statement.setInt(3, orderItem.getQuantity());
            statement.setDouble(4, orderItem.getTotalPrice());
            statement.setInt(5, orderItem.getOrderItemId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= GET ONE =================
    @Override
    public OrderItem getOrderItem(int orderItemId) {

        OrderItem orderItem = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDERITEM_QUERY)) {

            statement.setInt(1, orderItemId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                orderItem = new OrderItem();
                orderItem.setOrderItemId(rs.getInt("orderItemId"));
                orderItem.setOrderId(rs.getInt("orderId"));
                orderItem.setMenuId(rs.getInt("menuId"));
                orderItem.setItemName(rs.getString("itemName"));
                orderItem.setImagePath(rs.getString("imagePath"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setTotalPrice(rs.getDouble("totalPrice"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    // ================= GET ALL =================
    @Override
    public List<OrderItem> getAllOrderItem() {

        List<OrderItem> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(SELECT_ALL_ORDER_QUERY);

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(rs.getInt("orderItemId"));
                item.setOrderId(rs.getInt("orderId"));
                item.setMenuId(rs.getInt("menuId"));
                item.setItemName(rs.getString("itemName"));
                item.setImagePath(rs.getString("imagePath"));
                item.setQuantity(rs.getInt("quantity"));
                item.setTotalPrice(rs.getDouble("totalPrice"));
                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
