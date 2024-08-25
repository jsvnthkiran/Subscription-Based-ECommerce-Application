package com.ecommerce.dao;

import com.ecommerce.exceptions.DeliveryScheduleNotFoundException;
import com.ecommerce.model.DeliverySchedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryScheduleDAOImpl implements DeliveryScheduleDAO {

    private Connection connection;

    public DeliveryScheduleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addDeliverySchedule(DeliverySchedule deliverySchedule) throws SQLException {
        String sql = "INSERT INTO delivery_schedule (subscription_id, delivery_date) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, deliverySchedule.getSubscriptionId());
            stmt.setDate(2, deliverySchedule.getDeliveryDate());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<DeliverySchedule> getDeliverySchedulesBySubscriptionId(int subscriptionId) throws SQLException {
        List<DeliverySchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM delivery_schedule WHERE subscription_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date deliveryDate = rs.getDate("delivery_date");
                DeliverySchedule schedule = new DeliverySchedule(id, subscriptionId, deliveryDate);
                schedules.add(schedule);
            }
        }
        return schedules;
    }


    @Override
    public void updateDeliverySchedule(DeliverySchedule deliverySchedule) throws SQLException {
        String sql = "UPDATE delivery_schedule SET delivery_date = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, deliverySchedule.getDeliveryDate());
            stmt.setInt(2, deliverySchedule.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteDeliverySchedule(int id) throws SQLException {
        String sql = "DELETE FROM delivery_schedule WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
