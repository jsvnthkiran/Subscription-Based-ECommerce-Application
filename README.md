Sure! Here is the updated `README.md` file reflecting the SQL schema you provided:

---

# Subscription-Based E-Commerce Web Application

## Overview

This project is a subscription-based e-commerce web application designed to handle recurring food deliveries. It allows customers to subscribe to various food products, manage subscriptions, view order history, and more. The application includes both admin and customer functionalities, with support for managing products, subscriptions, delivery schedules, and orders.

## Technology Stack

- **Java**: Programming language used for backend development.
- **MySQL**: Database for storing application data.
- **SQL JDBC Connector**: For connecting Java applications to the MySQL database.
- **IntelliJ IDEA**: Integrated Development Environment (IDE) used for development.

## Project Structure

The project is organized into the following layers:

- **DAO Layer**: Manages database interactions.
- **Service Layer**: Contains business logic.
- **Controller Layer**: Manages user interactions.
- **Model Layer**: Represents data entities.
- **Factory Layer**: Creates DAO and service instances.
- **View Layer**: Console-based interface for user interaction.

## Getting Started

### Prerequisites

1. **Java JDK**: Ensure JDK 11 or later is installed.
2. **MySQL Server**: Ensure MySQL is installed and running.
3. **IntelliJ IDEA**: Use IntelliJ IDEA or any other IDE of your choice.

### Database Setup

1. **Create a Database**:
   - Open MySQL Workbench or any MySQL client.
   - Create a new database named `food_delivery`.

   ```sql
   CREATE DATABASE food_delivery;
   ```

2. **Create Tables**:
   - Use the provided SQL script to set up the database schema and insert dummy data.

   ```sql
   USE food_delivery;

   CREATE TABLE customer (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       email VARCHAR(255) NOT NULL UNIQUE,
       password VARCHAR(255) NOT NULL
   );

   CREATE TABLE product (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       price DECIMAL(10,2) NOT NULL,
       subscription_discount DECIMAL(5,2)
   );

   CREATE TABLE subscription (
       id INT AUTO_INCREMENT PRIMARY KEY,
       customer_id INT,
       subscription_type VARCHAR(255) NOT NULL,
       start_date DATE,
       end_date DATE,
       is_active BOOLEAN DEFAULT TRUE,
       FOREIGN KEY (customer_id) REFERENCES customer(id)
   );

   CREATE TABLE orders (
       id INT AUTO_INCREMENT PRIMARY KEY,
       customer_email VARCHAR(255) NOT NULL,
       total_price DECIMAL(10,2) NOT NULL,
       subscription_type VARCHAR(255),
       order_date DATE,
       FOREIGN KEY (customer_email) REFERENCES customer(email)
   );

   CREATE TABLE order_item (
       id INT AUTO_INCREMENT PRIMARY KEY,
       order_id INT,
       product_id INT,
       quantity INT NOT NULL,
       FOREIGN KEY (order_id) REFERENCES orders(id),
       FOREIGN KEY (product_id) REFERENCES product(id)
   );

   CREATE TABLE delivery_schedule (
       id INT AUTO_INCREMENT PRIMARY KEY,
       customer_id INT,
       product_id INT,
       delivery_date DATE NOT NULL,
       FOREIGN KEY (customer_id) REFERENCES customer(id),
       FOREIGN KEY (product_id) REFERENCES product(id)
   );

   CREATE TABLE order_history (
       id INT AUTO_INCREMENT PRIMARY KEY,
       customer_id INT,
       product_id INT,
       quantity INT,
       subscription_id INT,
       order_date DATE,
       FOREIGN KEY (customer_id) REFERENCES customer(id),
       FOREIGN KEY (product_id) REFERENCES product(id),
       FOREIGN KEY (subscription_id) REFERENCES subscription(id)
   );

   -- Insert dummy data
   INSERT INTO product (name, price, subscription_discount) VALUES 
       ('Organic Apple', 1.50, 0.10), 
       ('Whole Wheat Bread', 2.00, 0.15), 
       ('Almond Milk', 3.50, 0.20);
   ```

### Setting Up the Project

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/jsvnthkiran/Subscription-Based-ECommerce-Application.git
   cd Subscription-Based-ECommerce-Application
   ```

2. **Import the Project into IntelliJ IDEA**:
   - Open IntelliJ IDEA.
   - Click on `File` > `Open`.
   - Select the project directory and click `OK`.

3. **Add MySQL JDBC Connector**:
   - Download the MySQL JDBC driver (Connector/J) from the [MySQL website](https://dev.mysql.com/downloads/connector/j/).
   - In IntelliJ IDEA, go to `File` > `Project Structure` > `Libraries`.
   - Click on `+` and select `Java`.
   - Locate the downloaded JDBC JAR file and add it to the project.

4. **Configure Database Connection**:
   - Open the `DatabaseConnection` class in the `com.ecommerce.util` package.
   - Update the database URL, username, and password as per your local MySQL setup.

   ```java
   public class DatabaseConnection {
       private static final String URL = "jdbc:mysql://localhost:3306/food_delivery?useSSL=false";
       private static final String USER = "root";
       private static final String PASSWORD = "yourpassword";

       public static Connection getConnection() throws SQLException {
           return DriverManager.getConnection(URL, USER, PASSWORD);
       }
   }
   ```

### Running the Application

1. **Run the Application**:
   - In IntelliJ IDEA, open the `ViewImpl` class in the `com.ecommerce.view` package.
   - Right-click on the `ViewImpl` class and select `Run 'ViewImpl.main()'`.

2. **Using the Application**:
   - Follow the console prompts to navigate between Admin and Customer functionalities.
   - Admin functionalities include managing products, subscriptions, and viewing order history.
   - Customer functionalities include subscribing to products, viewing delivery schedules, and placing orders.

### Error Handling

- Ensure SQL exceptions or JDBC connection issues are handled gracefully.
- Review the exception handling code in DAO implementations for proper error management.

