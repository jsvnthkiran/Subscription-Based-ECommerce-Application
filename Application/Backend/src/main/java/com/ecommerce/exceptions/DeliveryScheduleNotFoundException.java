package com.ecommerce.exceptions;

public class DeliveryScheduleNotFoundException extends RuntimeException {
    public DeliveryScheduleNotFoundException(String message) {
        super(message);
    }
}
