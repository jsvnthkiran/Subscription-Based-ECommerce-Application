package com.ecommerce.view;


import com.ecommerce.controller.AdminController;
import com.ecommerce.controller.CustomerController;
import com.ecommerce.dao.*;
import com.ecommerce.service.*;
import com.ecommerce.util.DAOFactory;
import com.ecommerce.util.DatabaseConnection;
import com.ecommerce.util.ServiceFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewImpl {

//    private static Connection getConnection() throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/food_delivery?useSSL=false";
//        String user = "root";
//        String password = "1234";
//        return DriverManager.getConnection(url, user, password);
//    }

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ServiceFactory serviceFactory = new ServiceFactory(daoFactory);

            ProductService productService = serviceFactory.getProductService();
            SubscriptionService subscriptionService = serviceFactory.getSubscriptionService();
            DeliveryScheduleService deliveryScheduleService = serviceFactory.getDeliveryScheduleService();
            OrderHistoryService orderHistoryService = serviceFactory.getOrderHistoryService();
            CustomerService customerService = serviceFactory.getCustomerService();


            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Main Menu:");
                System.out.println("1. Admin");
                System.out.println("2. Customer");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        AdminController adminController = new AdminController(
                                productService,
                                subscriptionService,
                                deliveryScheduleService,
                                orderHistoryService
                        );
                        adminController.showAdminMenu();
                        break;
                    case 2:
                        // Customer Menu
                        CustomerController customerController = new CustomerController(
                                productService,
                                subscriptionService,
                                deliveryScheduleService,
                                orderHistoryService,
                                customerService
                        );
                        customerController.showCustomerMenu();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
