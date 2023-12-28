package com.ecommerce.OrderAandNotificationsManagement.service.notification;

import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public class SMSNotificationService extends NotificationService{
    @Override
    public void notifyOrderPlacement(OrderEntity order) {

    }

    @Override
    public void notifyOrderShipping(OrderEntity order) {

    }
}
