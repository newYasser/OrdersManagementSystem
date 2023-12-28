package com.ecommerce.OrderAandNotificationsManagement.service.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class SimplerOrderService extends OrderService{

    @Autowired
    public SimplerOrderService(@Qualifier("shippingPaymentSimpleOrderStrategy") ShippingPaymentStrategy shippingPaymentStrategy) {
        super(shippingPaymentStrategy);
    }

    @Override
    public long calculateShippingCost() {
        return shippingPaymentStrategy.calaculateShippingFees();
    }
    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }


}
