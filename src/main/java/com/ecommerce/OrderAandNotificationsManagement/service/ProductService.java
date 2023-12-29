package com.ecommerce.OrderAandNotificationsManagement.service;

import com.ecommerce.OrderAandNotificationsManagement.entity.Product;
import com.ecommerce.OrderAandNotificationsManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Integer id){
        Optional<Product>product = productRepository.findById(id);
        return product.orElse(null);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
