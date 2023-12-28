package com.ecommerce.OrderAandNotificationsManagement.service.order;

import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import org.springframework.stereotype.Service;


@Service
public interface ShippingPaymentStrategy {
    public long calaculateShippingFees(OrderEntity order);
}
