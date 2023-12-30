package com.ecommerce.OrderAandNotificationsManagement.service.notification;

import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import com.ecommerce.OrderAandNotificationsManagement.entity.Notification;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderDetail;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public class SendEmailNotificationService extends SendNotificationService{
    @Override
    public Notification notifyOrderPlacement(OrderEntity order) {
        String subject = "Order Placment Successfully";
        Customer customer = order.getCustomer();
        String content = "Dear" + customer.getAccount() + " , your booking of the ";
        for(OrderDetail orderDetail: order.getOrderDetails()){
            content += orderDetail.getProduct().getName() + " ";
        }
        content += "is confirmed. thanks for using our store :) ";
        Notification notification = new Notification();
        notification.setMessage(content);
        notification.setSubject(subject);
        notificationRepository.save(notification);
        return notification;
    }

    @Override
    public Notification notifyOrderShipping(OrderEntity order) {
        String subject = "Order Started Shipping";
        Customer customer = order.getCustomer();
        String content = "Dear" + customer.getAccount() + " , your order started to ship. ";
        Notification notification = new Notification();
        notification.setMessage(content);
        notification.setSubject(subject);
        notificationRepository.save(notification);
        return notification;
    }
}
