package com.example.fooddeliveryapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fooddeliveryapp.dto.CustomerDto;
import com.example.fooddeliveryapp.models.Customer;
import com.example.fooddeliveryapp.service.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private ICustomerService customerService;

	@Autowired
	public CustomerController(ICustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDto customerDto) {

		Customer customer = customerService.registerCustomer(customerDto);
		return new ResponseEntity<>(customer, HttpStatus.OK);

	}

	@GetMapping("/login")
	public ResponseEntity<Customer> loginCustomer(@RequestBody CustomerDto customerDto) {

		Customer customer = customerService.loginCustomer(customerDto);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

}
