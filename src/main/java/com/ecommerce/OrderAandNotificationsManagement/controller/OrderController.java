package com.ecommerce.OrderAandNotificationsManagement.controller;


import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.service.order.CompoundOrderService;
import com.ecommerce.OrderAandNotificationsManagement.service.order.OrderService;
import com.ecommerce.OrderAandNotificationsManagement.service.order.SimplerOrderService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Internal;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.filter.OrderedWebFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private SimplerOrderService simplerOrderService;

    @Autowired
    private CompoundOrderService compoundOrderService;

    @PostMapping("/add-simpler-order")
    public ResponseEntity<OrderEntity>addSimpleOrder(@RequestBody OrderEntity order){
        OrderEntity newOrder = simplerOrderService.addOrder(order);
        return new ResponseEntity<>(newOrder,HttpStatus.OK);
    }

    @PostMapping("/add-compound-order")
    public void addCompoundOrder(@RequestBody List<OrderEntity>orders){
        compoundOrderService.addOrder(orders);
    }

    @GetMapping("/get-order/{id}")
    public OrderEntity getOrderById(@PathVariable Integer id){
        OrderEntity order = simplerOrderService.getOrderById(id);
        return order;
    }


}
