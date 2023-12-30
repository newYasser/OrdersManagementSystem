package com.ecommerce.OrderAandNotificationsManagement.service.notification;

import com.ecommerce.OrderAandNotificationsManagement.entity.Notification;
import com.ecommerce.OrderAandNotificationsManagement.repository.NotificationRepository;
import org.jetbrains.annotations.Async;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    public List<Notification> getTobeSentNotification(){
        return notificationRepository.findByIsSentFalse();
    }
    public List<Notification> getSentNotification(){return notificationRepository.findByIsSentTrue();}

    @Scheduled(fixedRate = 30000)
    public void notifyCustomers() {
        List<Notification> unsentNotifications = notificationRepository.findByIsSentFalse();
        if(unsentNotifications.get(0) != null) {
            unsentNotifications.get(0).setSent(true);
            notificationRepository.save(unsentNotifications.get(0));
        }
    }
}
