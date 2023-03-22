package com.example.fooddeliveryapp.service.implementations;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fooddeliveryapp.dto.CustomerDto;
import com.example.fooddeliveryapp.exceptions.CustomerNotFoundException;
import com.example.fooddeliveryapp.exceptions.DuplicateCustomerException;
import com.example.fooddeliveryapp.models.Customer;
import com.example.fooddeliveryapp.repository.ICustomerRepository;
import com.example.fooddeliveryapp.service.ICustomerService;
import com.example.fooddeliveryapp.util.ModelMapperGenerator;
import com.example.fooddeliveryapp.util.convertor.CustomerConvertor;

@Service
public class CustomerServiceImpl implements ICustomerService {

	private ICustomerRepository customerRepository;
	
	private CustomerConvertor customerConvertor;

	@Autowired
	public CustomerServiceImpl(ICustomerRepository customerRepository, CustomerConvertor customerConvertor) {
		super();
		this.customerRepository = customerRepository;
		this.customerConvertor= customerConvertor;
	}

	@Override
	public Customer registerCustomer(CustomerDto customerDto) {

		Customer customer = customerConvertor.convert(customerDto);

		Example<Customer> exampleCustomer = Example.of(customer);
		Optional<Customer> foundCustomer = customerRepository.findOne(exampleCustomer);
		if (foundCustomer.isPresent())
			throw new DuplicateCustomerException("Customer already present");

		return customerRepository.save(customer);

	}

	@Override
	public Customer loginCustomer(CustomerDto customerDto) {

		Customer customer = customerConvertor.convert(customerDto);

		Example<Customer> exampleCustomer = Example.of(customer);
		Optional<Customer> foundCustomer = customerRepository.findOne(exampleCustomer);
		if (foundCustomer.isEmpty())
			throw new CustomerNotFoundException("Customer not found");

		return foundCustomer.get();

	}

}
