package com.ecommerce.OrderAandNotificationsManagement.service.notification;

import com.ecommerce.OrderAandNotificationsManagement.entity.Notification;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class NotificationService {
    @Autowired
    protected NotificationRepository notificationRepository;
    public abstract Notification notifyOrderPlacement(OrderEntity order);
    public abstract Notification notifyOrderShipping(OrderEntity order);
}