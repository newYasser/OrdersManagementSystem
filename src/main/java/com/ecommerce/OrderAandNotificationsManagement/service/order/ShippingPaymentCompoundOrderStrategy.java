package com.ecommerce.OrderAandNotificationsManagement.service.order;

import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class ShippingPaymentCompoundOrderStrategy implements ShippingPaymentStrategy{
    @Override
    public long calaculateShippingFees() {
        return 40;
    }
}
