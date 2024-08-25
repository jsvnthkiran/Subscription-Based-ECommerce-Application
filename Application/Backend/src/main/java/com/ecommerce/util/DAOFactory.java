package com.ecommerce.util;

import com.ecommerce.dao.*;
import com.ecommerce.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory {

    private static DAOFactory instance;
    private final ProductDAO productDAO;
    private final SubscriptionDAO subscriptionDAO;
    private final DeliveryScheduleDAO deliveryScheduleDAO;
    private final OrderHistoryDAO orderHistoryDAO;
    private final CustomerDAO customerDAO;

    private DAOFactory() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        this.productDAO = new ProductDAOImpl(connection);
        this.subscriptionDAO = new SubscriptionDAOImpl(connection);
        this.deliveryScheduleDAO = new DeliveryScheduleDAOImpl(connection);
        this.orderHistoryDAO = new OrderHistoryDAOImpl(connection);
        this.customerDAO = new CustomerDAOImpl(connection);
    }

    public static DAOFactory getInstance() throws SQLException {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public SubscriptionDAO getSubscriptionDAO() {
        return subscriptionDAO;
    }

    public DeliveryScheduleDAO getDeliveryScheduleDAO() {
        return deliveryScheduleDAO;
    }

    public OrderHistoryDAO getOrderHistoryDAO() {
        return orderHistoryDAO;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }
}
