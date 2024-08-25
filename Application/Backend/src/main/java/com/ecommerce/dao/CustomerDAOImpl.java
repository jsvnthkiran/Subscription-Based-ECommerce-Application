package com.ecommerce.dao;

import com.ecommerce.exceptions.CustomerNotFoundException;
import com.ecommerce.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private Connection connection;

    public CustomerDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating customer", e);
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
            } else {
                throw new CustomerNotFoundException("Customer not found with email: " + email);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving customer", e);
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
            } else {
                throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving customer", e);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving customers", e);
        }
        return customers;
    }
}
