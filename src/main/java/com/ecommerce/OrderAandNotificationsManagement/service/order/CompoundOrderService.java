package com.ecommerce.OrderAandNotificationsManagement.service.order;


import com.ecommerce.OrderAandNotificationsManagement.dto.CompoundOrderDTO;
import com.ecommerce.OrderAandNotificationsManagement.dto.OrderDTO;
import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderDetail;
import com.ecommerce.OrderAandNotificationsManagement.entity.OrderEntity;
import com.ecommerce.OrderAandNotificationsManagement.repository.OrderDetailRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CompoundOrderService extends OrderService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    public CompoundOrderService(@Qualifier("shippingPaymentCompoundOrderStrategy") ShippingPaymentStrategy shippingPaymentStrategy) {
        super(shippingPaymentStrategy);
    }

    @Override
    public long calculateShippingCost() {
       return shippingPaymentStrategy.calaculateShippingFees();
    }

    public List<OrderEntity> placeOrder(CompoundOrderDTO compoundOrderDTO) {
        List<OrderEntity> orders = new ArrayList<>();
         orders.add(placeOrder(compoundOrderDTO.getMainOrder(),compoundOrderDTO.getMainOrder().getCustomer_id()));
        for(OrderDTO order : compoundOrderDTO.getCompoundOrder()){
            orders.add(placeOrder(order, order.getCustomer_id()));
        }
        return orders;
    }

    public List<OrderEntity>getCompoundOrderById(Integer id){
        List<OrderEntity>orders = orderRepository.findAll();
        List<OrderEntity>compoundOrder = new ArrayList<>();
        for(OrderEntity order:orders){
            if(order.getId().equals(id)){
                compoundOrder.add(order);
            }
        }
        return compoundOrder;
    }

    public void payOrdersCompoundOrder(List<OrderEntity>orders) {
        for(OrderEntity order : orders){
            payOrderById(order.getId());
        }
    }

}
