package com.ecommerce.service;

import com.ecommerce.dao.OrderHistoryDAO;
import com.ecommerce.model.OrderHistory;

import java.sql.SQLException;
import java.util.List;

public class OrderHistoryServiceImpl implements OrderHistoryService {

    private OrderHistoryDAO orderHistoryDAO;

    public OrderHistoryServiceImpl(OrderHistoryDAO orderHistoryDAO) {
        this.orderHistoryDAO = orderHistoryDAO;
    }

    @Override
    public List<OrderHistory> getAllOrderHistory() throws SQLException {
        return orderHistoryDAO.getAllOrderHistory();
    }

    @Override
    public List<OrderHistory> getOrderHistoryByCustomerId(int customerId) throws SQLException {
        return orderHistoryDAO.getOrderHistoryByCustomerId(customerId);
    }
}
