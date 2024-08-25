package com.ecommerce.model;

import java.util.Date;

public class OrderHistory {
    private int id;
    private int customerId;
    private int productId;
    private int quantity;
    private int subscriptionId;
    private Date orderDate;

    // Constructor
    public OrderHistory(int id, int customerId, int productId, int quantity, int subscriptionId, Date orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.subscriptionId = subscriptionId;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    // toString method for displaying order history
    @Override
    public String toString() {
        return "OrderHistory{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", subscriptionId=" + subscriptionId +
                ", orderDate=" + orderDate +
                '}';
    }
}
