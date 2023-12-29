package com.ecommerce.OrderAandNotificationsManagement.controller;


import com.ecommerce.OrderAandNotificationsManagement.dto.OrderDTO;
import com.ecommerce.OrderAandNotificationsManagement.dto.OrderDetailDTO;
import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderDetail;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.service.CustomerService;
import com.ecommerce.OrderAandNotificationsManagement.service.OrderDetailService;
import com.ecommerce.OrderAandNotificationsManagement.service.order.CompoundOrderService;
import com.ecommerce.OrderAandNotificationsManagement.service.order.SimplerOrderService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/add-simple-order/{customer_id}")
    public ResponseEntity<List<OrderDetailDTO>> addSimpleOrderByCustomerId(
            @RequestBody List<OrderDetailDTO> orderDetailDTO,
            @PathVariable Integer customer_id) {
//        OrderEntity order = new OrderEntity();
//        order.setOrderDetails(orderDetailDTO);
//        OrderEntity placedOrder = simplerOrderService.placeOrder(order, customer_id);
        return new ResponseEntity<>(orderDetailDTO,HttpStatus.OK);
    }

    @PostMapping("/add-compound-order")
    public void addCompoundOrderBy(@RequestBody List<OrderEntity> orders) {
        compoundOrderService.addOrder(orders);
    }
    @PutMapping("/pay-simple-order/{order_id}")
    public void paySimpleOrderWithOrderId(@PathVariable Integer order_id){
        simplerOrderService.payOrderById(order_id);
    }
    @PutMapping("/pay-compound-order/{order_id}")
    public void payCompountOrderById(@PathVariable Integer order_id){
        List<OrderEntity>Orders = compoundOrderService.getCompoundOrderById(order_id);
        compoundOrderService.payOrdersCompoundOrder(Orders);
    }
    @GetMapping("/get-simple-order/{id}")
    public OrderEntity getSimpleOrderById(@PathVariable Integer id){
        return simplerOrderService.getOrderById(id);
    }
    @GetMapping("/get-compound-order/{id}")
    public List<OrderEntity> getCompoundOrderById(@PathVariable Integer id){
        return compoundOrderService.getCompoundOrderById(id);
    }

    @DeleteMapping("/cancel-simple-order/{id}")
    public void cancelSimpleOrder(@PathVariable Integer id){
        simplerOrderService.CanelOrderByOrderId(id);
    }
}
