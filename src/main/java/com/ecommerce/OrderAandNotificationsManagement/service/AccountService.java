package com.ecommerce.OrderAandNotificationsManagement.service;

import com.ecommerce.OrderAandNotificationsManagement.entity.Account;
import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import com.ecommerce.OrderAandNotificationsManagement.repository.AccountRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Customer addAccountWithCustomerId(Integer customer_id, Account account){
       Optional<Customer> customer =  customerRepository.findById(customer_id);
       if(customer.isPresent()){
           Account newAccunt = accountRepository.save(account);
           customer.get().setAccount(newAccunt);
           customerRepository.save(customer.get());
           return customer.get();
       }
       return null;
    }
}
