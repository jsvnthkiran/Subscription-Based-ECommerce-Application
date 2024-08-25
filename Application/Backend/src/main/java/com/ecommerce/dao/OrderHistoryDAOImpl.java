package com.ecommerce.dao;

import com.ecommerce.model.OrderHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {

    private Connection connection;

    public OrderHistoryDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<OrderHistory> getAllOrderHistory() throws SQLException {
        List<OrderHistory> orderHistories = new ArrayList<>();
        String sql = "SELECT * FROM order_history";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("subscription_id"),
                        rs.getDate("order_date")
                );
                orderHistories.add(orderHistory);
            }
        }
        return orderHistories;
    }

    @Override
    public List<OrderHistory> getOrderHistoryByCustomerId(int customerId) throws SQLException {
        List<OrderHistory> orderHistories = new ArrayList<>();
        String sql = "SELECT * FROM order_history WHERE customer_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderHistory orderHistory = new OrderHistory(
                            rs.getInt("id"),
                            rs.getInt("customer_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getInt("subscription_id"),
                            rs.getDate("order_date")
                    );
                    orderHistories.add(orderHistory);
                }
            }
        }
        return orderHistories;
    }
}
