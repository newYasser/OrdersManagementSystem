package com.ecommerce.OrderAandNotificationsManagement.controller;


import com.ecommerce.OrderAandNotificationsManagement.entity.Product;
import com.ecommerce.OrderAandNotificationsManagement.repository.ProductRepo;
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
    ProductRepo productRepo;

    @GetMapping("/get-all-products")
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            List<Product> productList =  new ArrayList<>();
            productRepo.findAll().forEach(productList::add);
            if(productList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productList,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
       Optional<Product> productOptional = productRepo.findById(id);
       if(productOptional.isPresent()){
           return new ResponseEntity<>(productOptional.get(),HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product newProduct = productRepo.save(product);
        System.out.println("hello");
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }
    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Integer id, @RequestBody Product newProductData){
        Optional<Product>productOptional = productRepo.findById(id);
        if(productOptional.isPresent()){
            productOptional.get().setName(newProductData.getName());
            productOptional.get().setCategory(newProductData.getCategory());
            productOptional.get().setVendor(newProductData.getVendor());
            productOptional.get().setPrice(newProductData.getPrice());
            productOptional.get().setSerialNumber(newProductData.getSerialNumber());
            Product updatedProduct = productOptional.get();
            productRepo.save(updatedProduct);
            return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("delete-product/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Integer id){
        productRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

