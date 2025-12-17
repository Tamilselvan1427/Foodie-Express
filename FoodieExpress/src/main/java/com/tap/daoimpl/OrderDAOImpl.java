package com.tap.daoimpl;

import java.sql.*;
import java.util.*;

import com.tap.dao.OrderDAO;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT_ORDER =
        "INSERT INTO `order` (userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?,?,?,?,?,?)";

    private static final String GET_ORDERS_BY_USER =
        "SELECT * FROM `order` WHERE userId=? ORDER BY orderDate DESC";

    private static final String GET_ITEMS_BY_ORDER =
        "SELECT m.itemName, m.imagePath, oi.quantity, oi.totalPrice " +
        "FROM orderitem oi JOIN menu m ON oi.menuId=m.menuId WHERE oi.orderId=?";

    @Override
    public void addOrder(Order order) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setTimestamp(3, order.getOrderDate());
            ps.setDouble(4, order.getTotalAmount());
            ps.setString(5, order.getStatus());
            ps.setString(6, order.getPaymentMode());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrderByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement psOrder = con.prepareStatement(GET_ORDERS_BY_USER);
             PreparedStatement psItem = con.prepareStatement(GET_ITEMS_BY_ORDER)) {

            psOrder.setInt(1, userId);
            ResultSet rsOrder = psOrder.executeQuery();

            while (rsOrder.next()) {
                Order order = new Order();
                order.setOrderId(rsOrder.getInt("orderId"));
                order.setUserId(rsOrder.getInt("userId"));
                order.setRestaurantId(rsOrder.getInt("restaurantId"));
                order.setOrderDate(rsOrder.getTimestamp("orderDate"));
                order.setTotalAmount(rsOrder.getDouble("totalAmount"));
                order.setStatus(rsOrder.getString("status"));
                order.setPaymentMode(rsOrder.getString("paymentMode"));

                psItem.setInt(1, order.getOrderId());
                ResultSet rsItem = psItem.executeQuery();

                List<OrderItem> items = new ArrayList<>();
                while (rsItem.next()) {
                    OrderItem item = new OrderItem();
                    item.setItemName(rsItem.getString("itemName"));
                    item.setImagePath(rsItem.getString("imagePath"));
                    item.setQuantity(rsItem.getInt("quantity"));
                    item.setTotalPrice(rsItem.getDouble("totalPrice"));
                    items.add(item);
                }

                order.setItems(items);
                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    // ---- unused methods (safe stubs) ----
    public Order getOrder(int id) { return null; }
    public void updateOrder(Order o) {}
    public void deleteOrder(int id) {}
    public List<Order> getAllOrder() { return null; }
}
