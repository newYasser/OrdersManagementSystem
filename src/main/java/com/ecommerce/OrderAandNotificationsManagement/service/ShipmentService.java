package com.ecommerce.OrderAandNotificationsManagement.service;

import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.entity.Shipment;
import com.ecommerce.OrderAandNotificationsManagement.repository.OrderRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.ShipmentRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private OrderRepository orderRepository;

    public void shipSimpleOrder(Integer order_id){
        Optional<OrderEntity> orderOptional = orderRepository.findById(order_id);
        if(orderOptional.isEmpty()) return;
        OrderEntity order = orderOptional.get();
        Shipment shipment = new Shipment();
        shipment.setOrders(List.of(orderOptional.get()));
        order.setShipment(shipment);
        order.setShipped(true);
        shipmentRepository.save(shipment);
        orderRepository.save(order);
    }
}
