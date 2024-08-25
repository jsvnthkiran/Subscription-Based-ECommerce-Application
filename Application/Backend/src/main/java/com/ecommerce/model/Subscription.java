package com.ecommerce.model;

import java.util.Date;

public class Subscription {
    private int id;
    private int customerId;
    private String type;
    private boolean isActive;
    private Date startDate;
    private Date endDate;

    public Subscription(int id, int customerId, String type, boolean isActive, Date startDate, Date endDate) {
        this.id = id;
        this.customerId = customerId;
        this.type = type;
        this.isActive = isActive;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public java.sql.Date getStartDate() {
        return (java.sql.Date) startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return (java.sql.Date) endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", type='" + type + '\'' +
                ", isActive=" + isActive +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
