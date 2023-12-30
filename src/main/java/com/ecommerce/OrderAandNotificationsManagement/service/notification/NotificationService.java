package com.ecommerce.OrderAandNotificationsManagement.service.notification;

import com.ecommerce.OrderAandNotificationsManagement.entity.Notification;
import com.ecommerce.OrderAandNotificationsManagement.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Async;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    public List<Notification> getTobeSentNotification(){
        return notificationRepository.findByIsSentFalse();
    }
    public List<Notification> getSentNotification(){return notificationRepository.findByIsSentTrue();}
    public String getMostNotifiedEmail() {
        String email = "No notification sent";
        if(notificationRepository.findMostNotifiedEmail().isPresent()){
            email =  notificationRepository.findMostNotifiedEmail().get().get(0);
        }
        return email;
    }

    public String getMostNotifiedPhoneNumber() {
        String phoneNumber = "No notification sent";
        if(notificationRepository.findMostNotifiedPhoneNumber().isPresent()){
            phoneNumber =  notificationRepository.findMostNotifiedPhoneNumber().get().get(0);
        }
        return phoneNumber;
    }
    public String getMostUsedMessage() {
        String message = "No notification sent";
        if(notificationRepository.findMostUsedMessage().isPresent()){
            message =  notificationRepository.findMostUsedMessage().get().get(0);
        }
        return message;
    }
    @Scheduled(fixedRate = 30000)
    public void notifyCustomers() {

        List<Notification> unsentNotifications = notificationRepository.findByIsSentFalse();
        if (unsentNotifications.isEmpty()) {
            return;
        }
        if(unsentNotifications.get(0) != null) {
                unsentNotifications.get(0).setSent(true);
                notificationRepository.save(unsentNotifications.get(0));
        }
    }
}
