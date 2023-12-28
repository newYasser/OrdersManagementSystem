//package com.ecommerce.OrderAandNotificationsManagement.controller;
//
//
//import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
//import com.ecommerce.OrderAandNotificationsManagement.service.order.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/order")
//public class OrderController {
//
//    @Autowired
//    OrderService orderService;
//
//
//    @PostMapping("/add-order")
//    public ResponseEntity<Order> addOrder(@RequestBody OrderComponent order){
//        try {
//            Order newOrder = orderService.addOrder(order);
//            return new ResponseEntity<>(newOrder,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/get-order/{id}")
//    public ResponseEntity<List<OrderEntity>>getCustomerOrder(@PathVariable Integer id){
//        try {
//           List<OrderEntity> customerOrders = orderService.getAllOrdersByCustomerId(id);
//           return new ResponseEntity<>(customerOrders,HttpStatus.OK);
//        }catch (NullPointerException e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}
