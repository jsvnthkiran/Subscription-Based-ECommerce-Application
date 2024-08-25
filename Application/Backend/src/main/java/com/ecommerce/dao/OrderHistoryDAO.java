package com.ecommerce.dao;

import com.ecommerce.model.OrderHistory;

import java.sql.SQLException;
import java.util.List;

public interface OrderHistoryDAO {
    List<OrderHistory> getAllOrderHistory() throws SQLException;
    List<OrderHistory> getOrderHistoryByCustomerId(int customerId) throws SQLException;
}
