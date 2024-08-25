package com.ecommerce.controller;

import com.ecommerce.model.*;
import com.ecommerce.service.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerController {

    private ProductService productService;
    private SubscriptionService subscriptionService;
    private DeliveryScheduleService deliveryScheduleService;
    private OrderHistoryService orderHistoryService;
    private final CustomerService customerService;
    int customerId;

    public CustomerController(ProductService productService, SubscriptionService subscriptionService, DeliveryScheduleService deliveryScheduleService,OrderHistoryService orderHistoryService, CustomerService customerService) {
        this.productService = productService;
        this.subscriptionService = subscriptionService;
        this.deliveryScheduleService = deliveryScheduleService;
        this.orderHistoryService = orderHistoryService;
        this.customerService = customerService;
    }

    public void showCustomerMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer ID:");
        customerId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            System.out.println("Invalid Customer ID. Please try again.");
            return;
        }
        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1. View all products");
            System.out.println("2. Subscribe to product");
            System.out.println("3. View my subscriptions");
            System.out.println("4. Cancel subscription");
            System.out.println("5. View order history");
            System.out.println("6. View delivery schedule");

            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        viewAllProducts();
                        break;
                    case 2:
                        subscribeToProduct(scanner);
                        break;
                    case 3:
                        viewMySubscriptions(scanner);
                        break;
                    case 4:
                        cancelSubscription(scanner);
                        break;
                    case 5:
                        viewOrderHistory();
                        break;
                    case 6:
                        viewDeliverySchedule(scanner);
                        break;
                    case 7:
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

    private void viewOrderHistory() throws SQLException {
        List<OrderHistory> orderHistories = orderHistoryService.getOrderHistoryByCustomerId(customerId);
        if(orderHistories.isEmpty()) {
            System.out.println("You have no order history");
            return;
        }
        System.out.println("Your Order History:");
        for (OrderHistory orderHistory : orderHistories) {
            System.out.println(orderHistory);
        }
    }

    private void viewDeliverySchedule(Scanner scanner) throws SQLException {
        System.out.println("Fetching your subscriptions...");

        // Retrieve all subscriptions for the customer
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByCustomerId(customerId);

        if (subscriptions.isEmpty()) {
            System.out.println("You have no active subscriptions.");
            return;
        }

        // Display the subscriptions
        System.out.println("Your Subscriptions:");
        for (Subscription subscription : subscriptions) {
            System.out.println("Subscription ID: " + subscription.getId() +
                    ", StartDate: " + subscription.getStartDate() +
                    ", EndDate: " + subscription.getEndDate());
        }

        // Ask the customer to select a subscription ID to view the delivery schedule
        System.out.println("Enter Subscription ID to view the delivery schedule:");
        int subscriptionId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // Fetch and display the delivery schedule for the selected subscription
        List<DeliverySchedule> deliverySchedules = deliveryScheduleService.getDeliverySchedulesBySubscriptionId(subscriptionId);

        if (deliverySchedules.isEmpty()) {
            System.out.println("No delivery schedules found for this subscription.");
        } else {
            System.out.println("Delivery Schedule:");
            for (DeliverySchedule schedule : deliverySchedules) {
                System.out.println("DeliverySchedule ID: " + schedule.getId() +"Subscription ID: " + schedule.getSubscriptionId() +
                        ", Delivery Date: " + schedule.getDeliveryDate() );

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

    private void subscribeToProduct(Scanner scanner) throws SQLException {
        System.out.print("Enter product ID to subscribe: ");
        int productId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter customer ID to subscribe: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter subscription type (daily, weekly, bi-weekly, monthly): ");
        String type = scanner.nextLine();
        System.out.print("Enter start date (yyyy-mm-dd): ");
        Date startDate = Date.valueOf(scanner.nextLine());
        System.out.print("Enter end date (yyyy-mm-dd): ");
        Date endDate = Date.valueOf(scanner.nextLine());

        Subscription subscription = new Subscription(productId,customerId, type, true, startDate, endDate);
        subscriptionService.addSubscription(subscription);
        System.out.println("Subscription added successfully.");
    }

    private void viewMySubscriptions(Scanner scanner) throws SQLException {
        System.out.print("Enter your customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByCustomerId(customerId);
        System.out.println("My Subscriptions:");
        for (Subscription subscription : subscriptions) {
            System.out.println(subscription);
        }
    }

    private void cancelSubscription(Scanner scanner) throws SQLException {
        System.out.print("Enter subscription ID to cancel: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription != null) {
            subscription.setActive(false);
            subscriptionService.updateSubscription(subscription);
            System.out.println("Subscription cancelled successfully.");
        } else {
            System.out.println("Subscription not found.");
        }
    }
}
