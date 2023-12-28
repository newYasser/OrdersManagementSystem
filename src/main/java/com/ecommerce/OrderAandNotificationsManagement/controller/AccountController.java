package com.ecommerce.OrderAandNotificationsManagement.controller;

import com.ecommerce.OrderAandNotificationsManagement.entity.Account;
import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import com.ecommerce.OrderAandNotificationsManagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/add-account/{id}")
    public Customer addAccountbyCustomerId(@PathVariable Integer customer_id,@RequestBody Account account){
        return accountService.addAccountWithCustomerId(customer_id,account);
    }
}
