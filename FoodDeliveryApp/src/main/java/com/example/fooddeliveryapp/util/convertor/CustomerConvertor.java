package com.example.fooddeliveryapp.util.convertor;

import org.springframework.stereotype.Component;

import com.example.fooddeliveryapp.dto.CustomerDto;
import com.example.fooddeliveryapp.models.Customer;
import com.example.fooddeliveryapp.util.ModelMapperGenerator;

@Component
public class CustomerConvertor {
	
	public Customer convert(CustomerDto customerDto) {
		return ModelMapperGenerator.getModelMapper().map(customerDto, Customer.class);
	}

}
