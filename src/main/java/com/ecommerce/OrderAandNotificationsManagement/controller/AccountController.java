package com.ecommerce.OrderAandNotificationsManagement.controller;

import com.ecommerce.OrderAandNotificationsManagement.dto.AccountDTO;
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

    @PostMapping("/add-account/{customer_id}")
    public void addAccountByCustomerId(@PathVariable Integer customer_id, @RequestBody AccountDTO accountDTO){
        Account account = new Account(null,accountDTO.getBalance(),null);
        accountService.addAccountWithCustomerId(customer_id,account);
    }
}
