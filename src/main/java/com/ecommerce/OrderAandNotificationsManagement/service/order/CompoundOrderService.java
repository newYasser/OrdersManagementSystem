package com.ecommerce.OrderAandNotificationsManagement.service.order;


import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompoundOrderService extends OrderService {

    @Autowired
    public CompoundOrderService(@Qualifier("shippingPaymentCompoundOrderStrategy") ShippingPaymentStrategy shippingPaymentStrategy) {
        super(shippingPaymentStrategy);
    }

    @Override
    public long calculateShippingCost() {
       return shippingPaymentStrategy.calaculateShippingFees();
    }

    public void addOrder(List<OrderEntity> orderEntities) {
        orderRepository.saveAll(orderEntities);
    }
    public void payOrdersCompoundOrder(List<OrderEntity>orders) {
        for(OrderEntity order : orders){
            payOrderById(order.getId());
        }
    }

}
