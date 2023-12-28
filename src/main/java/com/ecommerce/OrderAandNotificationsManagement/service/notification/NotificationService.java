package com.ecommerce.OrderAandNotificationsManagement.service.notification;

import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.repository.NotificationRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.NotificationTempleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class NotificationService {
    @Autowired
    private NotificationTempleteRepository notificationTempleteRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    public abstract void  notifyOrderPlacement(OrderEntity order);
    public abstract void notifyOrderShipping(OrderEntity order);
}