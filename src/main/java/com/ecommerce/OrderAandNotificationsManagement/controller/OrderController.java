package com.ecommerce.OrderAandNotificationsManagement.controller;


import com.ecommerce.OrderAandNotificationsManagement.dto.OrderDTO;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.service.CustomerService;
import com.ecommerce.OrderAandNotificationsManagement.service.order.CompoundOrderService;
import com.ecommerce.OrderAandNotificationsManagement.service.order.SimplerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<HttpStatus> addSimpleOrderByCustomerId(
            @RequestBody OrderDTO orderDTO,
            @PathVariable Integer customer_id) {
        try {
            OrderEntity placedOrder = simplerOrderService.placeOrder(orderDTO, customer_id);

            if (placedOrder != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // If placedOrder is null, it means the customer was not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle other exceptions during order placement
            e.printStackTrace();  // Log the exception for debugging purposes
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
