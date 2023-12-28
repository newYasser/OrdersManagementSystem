package com.ecommerce.OrderAandNotificationsManagement.controller;


import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderDetail;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.service.CustomerService;
import com.ecommerce.OrderAandNotificationsManagement.service.OrderDetailService;
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
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/add-simpler-order/{customer_id}")
    public ResponseEntity<OrderEntity>addSimpleOrderByCustomerId(
            @RequestBody List<OrderDetail> orderDetails,
            @PathVariable Integer customer_id){
        Customer customer = customerService.getCustomerById(customer_id);
        OrderEntity newOrder = simplerOrderService.saveOrderWithOrderDetailsAndCustomer(orderDetails,customer);
        return new ResponseEntity<>(newOrder,HttpStatus.OK);
    }

    @PostMapping("/add-compound-order/{customer_id}")
    public void addCompoundOrderBy(@RequestBody OrderEntity mainOrder,@RequestBody List<OrderEntity>orders){
        compoundOrderService.addOrder(orders);
    }

    @GetMapping("/get-simple-order/{id}")
    public OrderEntity getOrderById(@PathVariable Integer id){
        OrderEntity order = simplerOrderService.getOrderById(id);
        return order;
    }




}
