package com.ecommerce.model;

import java.util.Date;

public class DeliverySchedule {
    private int id;
    private int subscriptionId;
    private Date deliveryDate;

    public DeliverySchedule(int id, int subscriptionId, Date deliveryDate) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.deliveryDate = deliveryDate;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public java.sql.Date getDeliveryDate() {
        return (java.sql.Date) deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "DeliverySchedule{" +
                "id=" + id +
                ", subscriptionId=" + subscriptionId +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
