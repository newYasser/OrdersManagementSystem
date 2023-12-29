package com.ecommerce.OrderAandNotificationsManagement.service.order;



import com.ecommerce.OrderAandNotificationsManagement.dto.OrderDTO;
import com.ecommerce.OrderAandNotificationsManagement.dto.OrderDetailDTO;
import com.ecommerce.OrderAandNotificationsManagement.entity.*;
import com.ecommerce.OrderAandNotificationsManagement.repository.*;
import com.ecommerce.OrderAandNotificationsManagement.service.OrderDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public abstract class OrderService {

    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected OrderRepository orderRepository;
    @Autowired
    protected OrderDetailRepository orderDetailRepository;
    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected ShipmentRepository shipmentRepository;

    protected final ShippingPaymentStrategy shippingPaymentStrategy;

    @Autowired
    public OrderService(ShippingPaymentStrategy shippingPaymentStrategy) {
        this.shippingPaymentStrategy = shippingPaymentStrategy;
    }

    public OrderEntity placeOrder(OrderDTO orderDTO, Integer customer_id){
        OrderEntity order = new OrderEntity();
        Optional<Customer> customerOptional = customerRepository.findById(customer_id);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            order.setCustomer(customer);
            order.setTime(Time.valueOf(LocalTime.now()));
            order.setDate(Date.valueOf(LocalDate.now()));
            List<OrderDetail>orderDetails = new ArrayList<>();
            for(OrderDetailDTO orderDetailDTO :orderDTO.getOrderDetails()){
                Integer product_id = orderDetailDTO.getProductId();
                Optional<Product> productOptional = productRepository.findById(product_id);
                OrderDetail orderDetail = new OrderDetail() ;
                orderDetail.setProduct(productOptional.get());
                orderDetail.setQuantity(orderDetailDTO.getQuantity());
                orderDetails.add(orderDetail);

            }
            order.setOrderDetails(orderDetails);
            orderDetailRepository.saveAll(order.getOrderDetails());
            order.setOrderDetails(orderDetails);
            OrderEntity orderDB = orderRepository.save(order);
            for(OrderDetail orderDetail: order.getOrderDetails()){
                orderDetail.setOrder(orderDB);
            }
            OrderEntity finalOrder = orderRepository.save(orderDB);
            return finalOrder;
        }
        return null;
    }


    public OrderEntity getOrderById(Integer id){
        return orderRepository.findById(id).get();
    }
    public double calculateTotalCost(Integer order_id){
        long finalFees = 0;
        Optional<OrderEntity> orderOptional = orderRepository.findById(order_id);

        for(OrderDetail orderDetail : orderOptional.get().getOrderDetails()){
            finalFees += orderDetail.getProduct().getPrice() * orderDetail.getQuantity();
        }
        return finalFees;
    }
    protected boolean hasEnoughMoneyOnHisAccount(Integer order_id, long totalCost) {
        OrderEntity order = getOrderById(order_id);
        if(order.getCustomer().getAccount().getBalance() - totalCost >= 0) return true;
        else return false;
    }

    public void payOrderById(Integer order_id) {
        long totalCost = 0;

        totalCost += calculateTotalCost(order_id);

        if(hasEnoughMoneyOnHisAccount(order_id,totalCost)){
            Customer customer = getOrderById(order_id).getCustomer();
            long cuurentBalance = customer.getAccount().getBalance();
            customer.getAccount().setBalance(cuurentBalance - totalCost);
            customerRepository.save(customer);
        }
    }

    public void  payShippingFees(Integer order_id){
        long shippingFees = 0;
        shippingFees += calculateShippingCost();
        if(hasEnoughMoneyOnHisAccount(order_id,shippingFees)){
            Customer customer = getOrderById(order_id).getCustomer();
            long cuurentBalance = customer.getAccount().getBalance();
            customer.getAccount().setBalance(cuurentBalance - shippingFees);
            customerRepository.save(customer);
        }
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

    public void cancelSimpleOrderPlacment(Integer order_id){
        Optional<OrderEntity>orderOptional = orderRepository.findById(order_id);
        if(orderOptional.isPresent()){
            OrderEntity order = orderOptional.get();
            long orderFees = 0;
            for(OrderDetail orderDetail:order.getOrderDetails()){
                orderFees += orderDetail.getProduct().getPrice() * orderDetail.getQuantity();
            }
            Customer customer = order.getCustomer();
            long currentBalance = customer.getAccount().getBalance();
            customer.getAccount().setBalance(currentBalance + orderFees);
            order.setCustomer(customer);
            orderRepository.save(order);
            orderRepository.deleteById(order_id);
        }
    }

    public void cancelSimpleOrderShipment(Integer order_id){
        Optional<OrderEntity>orderOptional = orderRepository.findById(order_id);
        if(orderOptional.isPresent()) {
            OrderEntity order = orderOptional.get();
            Optional<Shipment>shipmentOptional = shipmentRepository.findById(order.getShipment().getId());
            if(shipmentOptional.isPresent()){
                Shipment shipment = shipmentOptional.get();
                shipmentRepository.deleteById(shipment.getId());
            }
        }
    }

    public abstract long  calculateShippingCost();

}
