package com.ecommerce.service;

import com.ecommerce.dao.SubscriptionDAO;
import com.ecommerce.model.Subscription;

import java.util.List;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionDAO subscriptionDAO;

    public SubscriptionServiceImpl(SubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }

    @Override
    public void addSubscription(Subscription subscription) throws SQLException {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }
        subscriptionDAO.addSubscription(subscription);
    }

    @Override
    public Subscription getSubscriptionById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Subscription ID must be positive");
        }
        return subscriptionDAO.getSubscriptionById(id);
    }

    @Override
    public List<Subscription> getSubscriptionsByCustomerId(int customerId) throws SQLException {
        if (customerId <= 0) {
            throw new IllegalArgumentException("Customer ID must be positive");
        }
        return subscriptionDAO.getSubscriptionsByCustomerId(customerId);
    }

    @Override
    public List<Subscription> getAllSubscriptions() throws SQLException {
        return subscriptionDAO.getAllSubscriptions();
    }

    @Override
    public void updateSubscription(Subscription subscription) throws SQLException {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }
        if (subscription.getId() <= 0) {
            throw new IllegalArgumentException("Subscription ID must be positive");
        }
        subscriptionDAO.updateSubscription(subscription);
    }

    @Override
    public void deleteSubscription(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Subscription ID must be positive");
        }
        subscriptionDAO.deleteSubscription(id);
    }
}
