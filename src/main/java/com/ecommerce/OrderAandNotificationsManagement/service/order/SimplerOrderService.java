package com.ecommerce.OrderAandNotificationsManagement.service.order;


import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderDetail;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class SimplerOrderService extends OrderService{

    @Autowired
    private OrderDetailRepository orderDetailRepository;
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

    public void canelOrderByOrderId(Integer order_id){
        OrderEntity order = orderRepository.getReferenceById(order_id);
        long orderPrice = 0;
        for(OrderDetail orderDetail: order.getOrderDetails()){
            orderPrice += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
        }
        long currentBalance = order.getCustomer().getAccount().getBalance();
        order.getCustomer().getAccount().setBalance(currentBalance + orderPrice);
        customerRepository.save(order.getCustomer());
        deleteOrderById(order_id);
    }




    public OrderEntity listOrder(Integer id) {
        return orderRepository.findById(id).get();
    }
}
