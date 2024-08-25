package com.ecommerce.service;

import com.ecommerce.dao.DeliveryScheduleDAO;
import com.ecommerce.model.DeliverySchedule;

import java.sql.SQLException;
import java.util.List;

public class DeliveryScheduleServiceImpl implements DeliveryScheduleService {

    private DeliveryScheduleDAO deliveryScheduleDAO;

    public DeliveryScheduleServiceImpl(DeliveryScheduleDAO deliveryScheduleDAO) {
        this.deliveryScheduleDAO = deliveryScheduleDAO;
    }

    @Override
    public void addDeliverySchedule(DeliverySchedule deliverySchedule) throws SQLException {
        if (deliverySchedule == null) {
            throw new IllegalArgumentException("DeliverySchedule cannot be null");
        }
        deliveryScheduleDAO.addDeliverySchedule(deliverySchedule);
    }

    @Override
    public List<DeliverySchedule> getDeliverySchedulesBySubscriptionId(int subscriptionId) throws SQLException {
        if (subscriptionId <= 0) {
            throw new IllegalArgumentException("Subscription ID must be positive");
        }
        return deliveryScheduleDAO.getDeliverySchedulesBySubscriptionId(subscriptionId);
    }

    @Override
    public void updateDeliverySchedule(DeliverySchedule deliverySchedule) throws SQLException {
        if (deliverySchedule == null) {
            throw new IllegalArgumentException("DeliverySchedule cannot be null");
        }
        if (deliverySchedule.getId() <= 0) {
            throw new IllegalArgumentException("DeliverySchedule ID must be positive");
        }
        deliveryScheduleDAO.updateDeliverySchedule(deliverySchedule);
    }

    @Override
    public void deleteDeliverySchedule(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("DeliverySchedule ID must be positive");
        }
        deliveryScheduleDAO.deleteDeliverySchedule(id);
    }
}
