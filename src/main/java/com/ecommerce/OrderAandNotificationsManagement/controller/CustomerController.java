package com.ecommerce.OrderAandNotificationsManagement.controller;


import com.ecommerce.OrderAandNotificationsManagement.dto.CustomerDTO;
import com.ecommerce.OrderAandNotificationsManagement.entity.Account;
import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import com.ecommerce.OrderAandNotificationsManagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/get-customer-by-id/{id}")
    public ResponseEntity<Customer>getCustomerByid(@PathVariable Integer id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("/add-customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            Customer addedCustomer = customerService.addCustomer(customerDTO);
            return new ResponseEntity<>(addedCustomer,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



}
