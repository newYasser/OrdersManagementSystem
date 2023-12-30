package com.ecommerce.OrderAandNotificationsManagement.controller;

import com.ecommerce.OrderAandNotificationsManagement.dto.NotificationStatisticsDTO;
import com.ecommerce.OrderAandNotificationsManagement.entity.Notification;
import com.ecommerce.OrderAandNotificationsManagement.service.notification.NotificationService;
import com.ecommerce.OrderAandNotificationsManagement.service.order.ShippingPaymentStrategy;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/to-be-sent")
    public ResponseEntity<List<Notification>> getNotificationsToBeSent(){
        List<Notification>notifications = notificationService.getTobeSentNotification();
        return new ResponseEntity<>(notifications,HttpStatus.OK);
    }
    @GetMapping("/sent")
    public ResponseEntity<List<Notification>> getSentNotifications(){
        List<Notification>notifications = notificationService.getSentNotification();
        return new ResponseEntity<>(notifications,HttpStatus.OK);
    }
    @GetMapping("/statistics")
    public ResponseEntity<NotificationStatisticsDTO>getNotificationStatistics(){
        NotificationStatisticsDTO notificationStatisticsDTO = new NotificationStatisticsDTO();
        notificationStatisticsDTO.setMostNotifiedEmail(notificationService.getMostNotifiedEmail());
        notificationStatisticsDTO.setMostNotifiedPhoneNumber(notificationService.getMostNotifiedPhoneNumber());
        notificationStatisticsDTO.setMostUsedTemplete(notificationService.getMostUsedMessage());
        return new ResponseEntity<>(notificationStatisticsDTO,HttpStatus.OK);
    }

}
