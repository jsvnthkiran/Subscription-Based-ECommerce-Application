package com.ecommerce.service;

import com.ecommerce.model.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);
    Customer getCustomerByEmail(String email);
    Customer getCustomer(int customerId);
    List<Customer> getAllCustomers();
}
