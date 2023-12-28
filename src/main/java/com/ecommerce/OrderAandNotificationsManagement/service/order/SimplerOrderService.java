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

    public OrderEntity saveOrderWithOrderDetailsAndCustomer(List<OrderDetail> orderDetails, Customer customer){
        OrderEntity orderEntity = new OrderEntity();
        orderRepository.save(orderEntity);
        orderEntity.setCustomer(customer);
        orderEntity.setTime(Time.valueOf(LocalTime.now()));
        orderEntity.setDate(Date.valueOf(LocalDate.now()));
        for(OrderDetail orderDetail: orderDetails){
            OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
            orderDetail.setOrder(orderEntity);
            orderEntity.getOrderDetails().add(newOrderDetail);
        }
        return orderRepository.save(orderEntity);

    }


}
