package com.ecommerce.OrderAandNotificationsManagement.service.order;



import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderDetail;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.entity.Product;
import com.ecommerce.OrderAandNotificationsManagement.repository.CustomerRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.OrderDetailRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.OrderRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.ProductRepository;
import com.ecommerce.OrderAandNotificationsManagement.service.OrderDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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

    protected final ShippingPaymentStrategy shippingPaymentStrategy;

    @Autowired
    public OrderService(ShippingPaymentStrategy shippingPaymentStrategy) {
        this.shippingPaymentStrategy = shippingPaymentStrategy;
    }

    public OrderEntity placeOrder(OrderEntity order,Integer customer_id){
        Optional<Customer> customerOptional = customerRepository.findById(customer_id);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            order.setCustomer(customer);
            order.setTime(Time.valueOf(LocalTime.now()));
            order.setDate(Date.valueOf(LocalDate.now()));
            for(OrderDetail orderDetail:order.getOrderDetails()){
                Integer product_id = orderDetail.getProduct().getId();
                Optional<Product> productOptional = productRepository.findById(product_id);
                orderDetail.setProduct(productOptional.get());
            }
            List<OrderDetail>orderDetails = orderDetailRepository.saveAll(order.getOrderDetails());
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



    public OrderEntity addOrder(OrderEntity order) {
        return orderRepository.save(order);
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
        if(order.getCustomer().getAccount().getBalance() - totalCost <= 0) return true;
        else return false;
    }

    public void payOrderById(Integer order_id) {
        long totalCost = 0;

        totalCost += calculateTotalCost(order_id);
        totalCost += calculateShippingCost();

        if(hasEnoughMoneyOnHisAccount(order_id,totalCost)){
            Customer customer = getOrderById(order_id).getCustomer();
            long cuurentBalance = customer.getAccount().getBalance();
            customer.getAccount().setBalance(cuurentBalance - totalCost);
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

    public abstract long  calculateShippingCost();

}
