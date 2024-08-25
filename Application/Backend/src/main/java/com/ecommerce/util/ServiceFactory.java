package com.ecommerce.util;


import com.ecommerce.dao.*;
import com.ecommerce.service.*;

import java.sql.SQLException;

public class ServiceFactory {

    private final ProductService productService;
    private final SubscriptionService subscriptionService;
    private final DeliveryScheduleService deliveryScheduleService;
    private final OrderHistoryService orderHistoryService;
    private final CustomerService customerService;

    public ServiceFactory(DAOFactory daoFactory) {
        ProductDAO productDAO = daoFactory.getProductDAO();
        SubscriptionDAO subscriptionDAO = daoFactory.getSubscriptionDAO();
        DeliveryScheduleDAO deliveryScheduleDAO = daoFactory.getDeliveryScheduleDAO();
        OrderHistoryDAO orderHistoryDAO = daoFactory.getOrderHistoryDAO();
        CustomerDAO customerDAO = daoFactory.getCustomerDAO();

        this.productService = new ProductServiceImpl(productDAO);
        this.subscriptionService = new SubscriptionServiceImpl(subscriptionDAO);
        this.deliveryScheduleService = new DeliveryScheduleServiceImpl(deliveryScheduleDAO);
        this.orderHistoryService = new OrderHistoryServiceImpl(orderHistoryDAO);
        this.customerService = new CustomerServiceImpl(customerDAO);
    }

    public ProductService getProductService() {
        return productService;
    }

    public SubscriptionService getSubscriptionService() {
        return subscriptionService;
    }

    public DeliveryScheduleService getDeliveryScheduleService() {
        return deliveryScheduleService;
    }

    public OrderHistoryService getOrderHistoryService() {
        return orderHistoryService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }
}
