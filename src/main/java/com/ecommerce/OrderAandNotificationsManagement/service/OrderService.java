package com.ecommerce.OrderAandNotificationsManagement.service;


import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public List<OrderEntity> getAllOrdersByCustomerId(Integer customerId){
        List<OrderEntity>AllOrders = orderRepository.findAll();
        List<OrderEntity>customerOrders = null;
        for(OrderEntity order: AllOrders){
            if(Objects.equals(order.getCustomer().getId(), customerId)){
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }
//    public Order addOrder(Order order) {
//        return orderRepository.save(order);
//    }
//
//    public long getOrderPriceByOrderId(Integer id){
//        Optional<Order> orderOptional = orderRepository.findById(id);
//        return orderOptional.map(Order::).orElse(-1L);
//    }


}
