package com.ecommerce.service;

import com.ecommerce.model.OrderHistory;

import java.sql.SQLException;
import java.util.List;

public interface OrderHistoryService {
    List<OrderHistory> getAllOrderHistory() throws SQLException;
    List<OrderHistory> getOrderHistoryByCustomerId(int customerId) throws SQLException;
}
