package com.ecommerce.service;

import com.ecommerce.model.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionService {
    void addSubscription(Subscription subscription) throws SQLException;
    Subscription getSubscriptionById(int id) throws SQLException;
    List<Subscription> getSubscriptionsByCustomerId(int customerId) throws SQLException;
    List<Subscription> getAllSubscriptions() throws SQLException;
    void updateSubscription(Subscription subscription) throws SQLException;
    void deleteSubscription(int id) throws SQLException;
}
