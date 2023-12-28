package com.ecommerce.OrderAandNotificationsManagement.service.order;



import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.repository.CustomerRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class OrderService {

    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected OrderRepository orderRepository;
    protected final ShippingPaymentStrategy shippingPaymentStrategy;

    @Autowired
    public OrderService(ShippingPaymentStrategy shippingPaymentStrategy) {
        this.shippingPaymentStrategy = shippingPaymentStrategy;
    }
    public abstract void addOrder(OrderEntity order);
    public abstract void deleteOrderById(Integer id);
    public abstract double calculateTotalCost();

}
