package com.ecommerce.OrderAandNotificationsManagement.service.order;



import com.ecommerce.OrderAandNotificationsManagement.entity.OrderDetail;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SimplerOrderService extends OrderService{


    @Autowired
    public SimplerOrderService(@Qualifier("shippingPaymentSimpleOrderStrategy") ShippingPaymentStrategy shippingPaymentStrategy) {
        super(shippingPaymentStrategy);
    }

    public OrderEntity placeOrderByCustomerId(Integer id, OrderEntity order) {
        // customer from database
         Optional<Customer> customerOptional = customerRepository.findById(id);
         OrderEntity newOrder = null;
         if(customerOptional.isPresent()){
             newOrder = orderRepository.save(order);
             // setting notification
         }
        return newOrder;
    }

    public void cancelOrderByOrderId(Integer order_id) {
        orderRepository.deleteById(order_id);
    }

    public long calculateOrderFeesByOrderId(Integer order_id) {
        long finalFees = 0;

        Optional<OrderEntity> orderOptional = orderRepository.findById(order_id);

        for(OrderDetail orderDetail : orderOptional.get().getOrderDetails()){
            finalFees += orderDetail.getProduct().getPrice() * orderDetail.getQunaitity();
        }

        finalFees += shippingPaymentStrategy.calaculateShippingFees(orderOptional.get());

        return finalFees;
    }

    @Override
    public void addOrder(OrderEntity order) {
        orderRepository.save(order);
    }



    @Override
    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public double calculateTotalCost() {
        return 0;
    }
}
