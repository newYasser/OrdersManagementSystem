package com.ecommerce.OrderAandNotificationsManagement.controller;


import com.ecommerce.OrderAandNotificationsManagement.entity.Product;
import com.ecommerce.OrderAandNotificationsManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepo;

    @GetMapping("/get-all-products")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> productList = new ArrayList<>();
            productRepo.findAll().forEach(productList::add);
            if (productList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


