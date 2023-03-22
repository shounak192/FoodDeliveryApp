package com.example.fooddeliveryapp.service;

import org.springframework.stereotype.Service;

import com.example.fooddeliveryapp.dto.CustomerDto;
import com.example.fooddeliveryapp.models.Customer;

@Service
public interface ICustomerService {

	public Customer registerCustomer(CustomerDto customerDto);

	public Customer loginCustomer(CustomerDto customerDto);

}
