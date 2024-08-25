package com.ecommerce.service;

import com.ecommerce.dao.CustomerDAO;
import com.ecommerce.model.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDAO.createCustomer(customer);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerDAO.getCustomerByEmail(email);
    }

    @Override
    public Customer getCustomer(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}
