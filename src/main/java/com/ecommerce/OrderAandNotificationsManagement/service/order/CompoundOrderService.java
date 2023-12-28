package com.ecommerce.OrderAandNotificationsManagement.service.order;


import com.ecommerce.OrderAandNotificationsManagement.entity.OrderDetail;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import java.util.List;
import java.util.Optional;

@Service
public class CompoundOrderService extends OrderService {

    @Autowired
    public CompoundOrderService(@Qualifier("shippingPaymentCompoundOrderStrategy") ShippingPaymentStrategy shippingPaymentStrategy) {
        super(shippingPaymentStrategy);
    }
    public List<OrderEntity> placeOrdersByCustomerId(Integer id, List<OrderEntity> orderEntities) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        List<OrderEntity> newOrders = null;
        if(customerOptional.isPresent()){
            for(OrderEntity order : orderEntities){
                OrderEntity newOrder = orderRepository.save(order);
                orderEntities.add(newOrder);
            }
        }
        return newOrders;
    }
    public long payOrders(List<OrderEntity>orders) {
        long finalFees = 0;
        for(OrderEntity order : orders){
            for(OrderDetail orderDetail : order.getOrderDetails()){
                finalFees += orderDetail.getProduct().getPrice() * orderDetail.getQunaitity();
            }
            finalFees += shippingPaymentStrategy.calaculateShippingFees(order);
        }
        return 0;
    }

    @Override
    public void addOrder(OrderEntity order) {

    }

    @Override
    public void deleteOrderById(Integer id) {

    }

    @Override
    public double calculateTotalCost() {
        return 0;
    }
}
