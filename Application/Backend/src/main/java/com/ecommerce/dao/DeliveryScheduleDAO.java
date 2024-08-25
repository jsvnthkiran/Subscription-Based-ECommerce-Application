package com.ecommerce.dao;

import com.ecommerce.model.DeliverySchedule;

import java.util.List;

import java.sql.SQLException;
import java.util.List;

public interface DeliveryScheduleDAO {
    void addDeliverySchedule(DeliverySchedule deliverySchedule) throws SQLException;
    List<DeliverySchedule> getDeliverySchedulesBySubscriptionId(int subscriptionId) throws SQLException;
    void updateDeliverySchedule(DeliverySchedule deliverySchedule) throws SQLException;
    void deleteDeliverySchedule(int id) throws SQLException;
}
