package com.ecommerce.OrderAandNotificationsManagement.service.notification;

import com.ecommerce.OrderAandNotificationsManagement.entity.Notification;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.repository.NotificationRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.NotificationTempleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.util.Collections;

@Service
public abstract class NotificationService {
    @Autowired
    private NotificationTempleteRepository notificationTempleteRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    private Queue<Notification> notificationsQueue = new LinkedList<>();
    private Map<String, Integer> emailStats = new HashMap<>();
    private Map<String, Integer> templateStats = new HashMap<>();

    public abstract void notifyOrderPlacement(OrderEntity order);
    public abstract void notifyOrderShipping(OrderEntity order);

    public void addToQueue(Notification notification) {
        notificationsQueue.add(notification);

        emailStats.put(notification.getOrder().getCustomer().getEmail(), emailStats.getOrDefault(notification.getOrder().getCustomer().getEmail(), 0) + 1);
        templateStats.put(notification.getTemplate(), templateStats.getOrDefault(notification.getTemplate(), 0) + 1);
    }
public boolean hasEmail (OrderEntity order ){
        try{
            order.getCustomer().getEmail();
            return true;
        } catch (Exception e) {return false;}
}
public boolean hasPhoneNum (OrderEntity order ){
        try{
            order.getCustomer().getPhoneNumber();
            return true;
        } catch (Exception e) {return false;}
    }

    public abstract void notify (OrderEntity order);
    public List<Notification> listQueue() {
        return new ArrayList<>(notificationsQueue);
    }

    public void removeFromQueue() {
        notificationsQueue.remove();
    }

    public String getMostNotified() {
        return Collections.max(emailStats.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public String getMostUsedTemplate() {
        return Collections.max(templateStats.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
