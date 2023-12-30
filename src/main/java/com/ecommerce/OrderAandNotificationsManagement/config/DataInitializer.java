package com.ecommerce.OrderAandNotificationsManagement.config;

import com.ecommerce.OrderAandNotificationsManagement.entity.Account;
import com.ecommerce.OrderAandNotificationsManagement.entity.Category;
import com.ecommerce.OrderAandNotificationsManagement.entity.Customer;
import com.ecommerce.OrderAandNotificationsManagement.entity.Product;
import com.ecommerce.OrderAandNotificationsManagement.repository.AccountRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.CategoryRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.CustomerRepository;
import com.ecommerce.OrderAandNotificationsManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public void run(String... args) {
        // Generate and insert 10 categories
        List<Category> categories = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Category category = new Category(null, "Category " + i, 0, null);
            categories.add(category);
        }
        categoryRepository.saveAll(categories);

        // Generate and insert 100 products with 10 different categories
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            int categoryId = (i % 10) + 1; // Assigning categories in a loop
            Category category = categoryRepository.findById(categoryId).orElse(null);

            if (category != null) {
                Product product = new Product(null, "SN" + i, "Product " + i, "Vendor " + i, i * 10, category, null);
                products.add(product);
            }
        }
        productRepository.saveAll(products);

        // Inserting Customer data
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(null, "John Doe", "john.doe@example.com", "+1234567890", null, null,null));
        customers.add(new Customer(null, "Alice Smith", "alice.smith@example.com", "+9876543210", null, null,null));
        customers.add(new Customer(null, "Bob Johnson", "bob.johnson@example.com", "+1112223333", null, null,null));
        customers.add(new Customer(null, "Eva Williams", "eva.williams@example.com", "+4445556666", null, null,null));
        customers.add(new Customer(null, "David Davis", "david.davis@example.com", "+7778889999", null, null,null));
        customers.add(new Customer(null, "Grace Brown", "grace.brown@example.com", "+3332221111", null, null,null));
        customers.add(new Customer(null, "Mohammed Yasser", "mohammedyasser0110@gamil.com", "+201009575293", null, null,null));

        customerRepository.saveAll(customers);
        // Create accounts
        List<Account> accounts = new ArrayList<>();

       accounts.add(new Account(null, 5000,null));
        accounts.add(new Account(null, 5000,null));
        accounts.add(new Account(null, 5000,null));
        accounts.add(new Account(null, 5000,null));
        accounts.add(new Account(null, 5000,null));
        accounts.add(new Account(null, 5000,null));


        accountRepository.saveAll(accounts);

        // Inserting Customer data
        for(int i = 0;i < 6;++i){
            customers.get(i).setAccount(accounts.get(i));
        }
        customerRepository.saveAll(customers);
    }
}