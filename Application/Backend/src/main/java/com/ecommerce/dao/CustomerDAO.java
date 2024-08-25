package com.ecommerce.dao;

import com.ecommerce.model.Customer;

import java.util.List;

public interface CustomerDAO {
    void createCustomer(Customer customer);
    Customer getCustomerByEmail(String email);
    Customer getCustomerById(int customerId);
    List<Customer> getAllCustomers();
}
