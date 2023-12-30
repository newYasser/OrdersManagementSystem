package com.ecommerce.OrderAandNotificationsManagement.service.notification;

import com.ecommerce.OrderAandNotificationsManagement.entity.Notification;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public abstract class SendNotificationService {
    @Autowired
    protected NotificationRepository notificationRepository;
    public abstract Notification notifyOrderPlacement(OrderEntity order);
    public abstract Notification notifyOrderShipping(OrderEntity order);


}