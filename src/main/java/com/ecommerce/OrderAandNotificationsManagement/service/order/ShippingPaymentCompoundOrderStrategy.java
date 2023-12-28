package com.ecommerce.OrderAandNotificationsManagement.service.order;

import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ShippingPaymentCompoundOrderStrategy implements ShippingPaymentStrategy{
    @Override
    public long calaculateShippingFees(OrderEntity orderService) {
        return 0;
    }
}
