package com.ecommerce.OrderAandNotificationsManagement;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderAandNotificationsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderAandNotificationsManagementApplication.class, args);
	}

}
