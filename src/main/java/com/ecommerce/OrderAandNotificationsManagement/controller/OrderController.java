package com.ecommerce.OrderAandNotificationsManagement.controller;


import com.ecommerce.OrderAandNotificationsManagement.dto.OrderDTO;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.service.ShipmentService;
import com.ecommerce.OrderAandNotificationsManagement.service.notification.EmailNotificationService;
import com.ecommerce.OrderAandNotificationsManagement.service.notification.SMSNotificationService;
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
    private EmailNotificationService emailNotificationService;
    @Autowired
    private SMSNotificationService smsNotificationService;
    @Autowired
    private ShipmentService shipmentServices;

    @PostMapping("/add-simple-order")
    public ResponseEntity<HttpStatus> addSimpleOrderByCustomerId(
            @RequestBody OrderDTO orderDTO) {
        try {
            OrderEntity placedOrder = simplerOrderService.placeOrder(orderDTO, orderDTO.getCustomer_id());

            if (placedOrder != null) {
                smsNotificationService.notifyOrderPlacement(placedOrder);
                emailNotificationService.notifyOrderShipping(placedOrder);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list-simple-order/{id}")
    public ResponseEntity<OrderEntity>listSimpleOrder(@PathVariable Integer id){
        OrderEntity order = simplerOrderService.listOrder(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
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
    @PostMapping ("/ship-simple-order/{order_id}")
    public ResponseEntity<HttpStatus>shipSimpleOrder(@PathVariable Integer order_id){
        OrderEntity order = simplerOrderService.getOrderById(order_id);
        shipmentServices.shipSimpleOrder(order_id);
        simplerOrderService.payShippingFees(order_id);
        emailNotificationService.notifyOrderShipping(order);
        smsNotificationService.notifyOrderShipping(order);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/cancel-simple-order/{id}")
    public void cancelSimpleOrder(@PathVariable Integer id){
        simplerOrderService.CanelOrderByOrderId(id);
    }


}
