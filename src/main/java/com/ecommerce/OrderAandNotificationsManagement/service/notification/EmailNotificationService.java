package com.ecommerce.OrderAandNotificationsManagement.service.notification;

import com.ecommerce.OrderAandNotificationsManagement.entity.Notification;
import com.ecommerce.OrderAandNotificationsManagement.entity.NotificationTemplate;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService extends NotificationService{
    @Override
    public void notifyOrderPlacement(OrderEntity order) {
        // Get the order placement template
        NotificationTemplate template = notificationTemplateRepository.findByName("OrderPlacement");
    
        // Replace placeholders with actual values
        String content = template.getContent()
                                .replace("{x}", order.getCustomerName())
                                .replace("{y}", order.getItemName());
    
        // Create a new notification
        Notification notification = new Notification();
        notification.setEmail(order.getCustomerEmail());
        notification.setContent(content);
        notification.setTemplate("OrderPlacement");
    
        // Add the notification to the queue
        addToQueue(notification);
    }
    
    @Override
    public void notifyOrderShipping(OrderEntity order) {
        // Get the order shipping template
        NotificationTemplate template = notificationTemplateRepository.findByName("OrderShipping");
    
        // Replace placeholders with actual values
        String content = template.getContent()
                                .replace("{x}", order.getCustomerName())
                                .replace("{y}", order.getItemName());
    
        // Create a new notification
        Notification notification = new Notification();
        notification.setEmail(order.getCustomerEmail());
        notification.setContent(content);
        notification.setTemplate("OrderShipping");
    
        // Add the notification to the queue
        addToQueue(notification);
    }

    @Override
    public void notify(OrderEntity order) {

    }
}
