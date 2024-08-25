package com.ecommerce.dao;

import com.ecommerce.exceptions.SubscriptionNotFoundException;
import com.ecommerce.model.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOImpl implements SubscriptionDAO {

    private Connection connection;

    public SubscriptionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addSubscription(Subscription subscription) throws SQLException {
        String sql = "INSERT INTO subscription (customer_id, type, is_active, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, subscription.getCustomerId());
            stmt.setString(2, subscription.getType());
            stmt.setBoolean(3, subscription.isActive());
            stmt.setDate(4, subscription.getStartDate());
            stmt.setDate(5, subscription.getEndDate());
            stmt.executeUpdate();
        }
    }

    @Override
    public Subscription getSubscriptionById(int id) throws SQLException {
        String sql = "SELECT * FROM subscription WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSubscription(rs);
            }
            return null;
        }
    }

    @Override
    public List<Subscription> getSubscriptionsByCustomerId(int customerId) throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscription WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                subscriptions.add(mapResultSetToSubscription(rs));
            }
        }
        return subscriptions;
    }

    @Override
    public List<Subscription> getAllSubscriptions() throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscription";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                subscriptions.add(mapResultSetToSubscription(rs));
            }
        }
        return subscriptions;
    }

    @Override
    public void updateSubscription(Subscription subscription) throws SQLException {
        String sql = "UPDATE subscription SET customer_id = ?, type = ?, is_active = ?, start_date = ?, end_date = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, subscription.getCustomerId());
            stmt.setString(2, subscription.getType());
            stmt.setBoolean(3, subscription.isActive());
            stmt.setDate(4, subscription.getStartDate());
            stmt.setDate(5, subscription.getEndDate());
            stmt.setInt(6, subscription.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteSubscription(int id) throws SQLException {
        String sql = "DELETE FROM subscription WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Subscription mapResultSetToSubscription(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int customerId = rs.getInt("customer_id");
        String type = rs.getString("type");
        boolean isActive = rs.getBoolean("is_active");
        Date startDate = rs.getDate("start_date");
        Date endDate = rs.getDate("end_date");
        return new Subscription(id, customerId, type, isActive, startDate, endDate);
    }
}

