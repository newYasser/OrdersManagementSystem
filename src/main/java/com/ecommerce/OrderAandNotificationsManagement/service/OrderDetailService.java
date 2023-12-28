package com.ecommerce.OrderAandNotificationsManagement.service;

import com.ecommerce.OrderAandNotificationsManagement.entity.OrderDetail;
import com.ecommerce.OrderAandNotificationsManagement.entity.Product;
import com.ecommerce.OrderAandNotificationsManagement.repository.OrderDetailRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    public OrderDetail addProductToOrderDetailByProductId(Integer product_id){
        OrderDetail orderDetail = new OrderDetail();
        Optional<Product> productOptional = productRepository.findById(product_id);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            orderDetail.setProduct(product);
           orderDetail =  orderDetailRepository.save(orderDetail);
        }
        return orderDetail;
    }

}