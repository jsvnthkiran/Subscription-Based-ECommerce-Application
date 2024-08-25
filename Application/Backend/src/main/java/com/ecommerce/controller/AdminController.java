package com.ecommerce.controller;

import com.ecommerce.model.DeliverySchedule;
import com.ecommerce.model.OrderHistory;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import com.ecommerce.service.DeliveryScheduleService;
import com.ecommerce.service.OrderHistoryService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.SubscriptionService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminController {

    private ProductService productService;
    private SubscriptionService subscriptionService;
    private DeliveryScheduleService deliveryScheduleService;
    private OrderHistoryService orderHistoryService;

    public AdminController(ProductService productService, SubscriptionService subscriptionService, DeliveryScheduleService deliveryScheduleService,OrderHistoryService orderHistoryService) {
        this.productService = productService;
        this.subscriptionService = subscriptionService;
        this.deliveryScheduleService = deliveryScheduleService;
        this.orderHistoryService = orderHistoryService;
    }

    public void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. View all products");
            System.out.println("2. Add product");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("5. View all subscriptions");
            System.out.println("6. View order history");
            System.out.println("7. View delivery schedule");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        viewAllProducts();
                        break;
                    case 2:
                        addProduct(scanner);
                        break;
                    case 3:
                        updateProduct(scanner);
                        break;
                    case 4:
                        deleteProduct(scanner);
                        break;
                    case 5:
                        viewAllSubscriptions();
                        break;
                    case 6:
                        viewOrderHistory();
                        break;
                    case 7:
                        viewDeliverySchedule(scanner);
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        }
    }

    private void viewAllProducts() throws SQLException {
        List<Product> products = productService.getAllProducts();
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private void addProduct(Scanner scanner) throws SQLException {
        System.out.print("Enter product ID to add: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Product product = new Product(id,name, price);
        productService.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private void updateProduct(Scanner scanner) throws SQLException {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Product product = new Product(id, name, price);
        productService.updateProduct(product);
        System.out.println("Product updated successfully.");
    }

    private void deleteProduct(Scanner scanner) throws SQLException {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        productService.deleteProduct(id);
        System.out.println("Product deleted successfully.");
    }

    private void viewAllSubscriptions() throws SQLException {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        System.out.println("Subscriptions:");
        for (Subscription subscription : subscriptions) {
            System.out.println(subscription);
        }
    }

    private void viewOrderHistory() throws SQLException {
        List<OrderHistory> orderHistories = orderHistoryService.getAllOrderHistory();
        System.out.println("Order History:");
        for (OrderHistory orderHistory : orderHistories) {
            System.out.println(orderHistory);
        }
    }

    private void viewDeliverySchedule(Scanner scanner) throws SQLException {
        System.out.print("Enter subscription ID to view delivery schedule: ");
        int subscriptionId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        List<DeliverySchedule> schedules = deliveryScheduleService.getDeliverySchedulesBySubscriptionId(subscriptionId);
        System.out.println("Delivery Schedules:");
        for (DeliverySchedule schedule : schedules) {
            System.out.println(schedule);
        }
    }
}
