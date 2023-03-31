package com.example.fooddeliveryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.example.fooddeliveryapp.dto.CustomerDto;
import com.example.fooddeliveryapp.security.SecurityConfiguration;
import com.example.fooddeliveryapp.service.ICustomerService;
import com.example.fooddeliveryapp.service.implementations.CustomerServiceImpl;

@SpringBootApplication
@Import(SecurityConfiguration.class)
public class FoodDeliveryAppApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(FoodDeliveryAppApplication.class, args);
		
		ICustomerService customerService = applicationContext.getBean(CustomerServiceImpl.class);
//		PasswordEncoder passwordEncoder = PasswordEncoderGenerator.getEncoder();
		customerService.registerCustomer(new CustomerDto("admin", "password"));
		
	}

}
