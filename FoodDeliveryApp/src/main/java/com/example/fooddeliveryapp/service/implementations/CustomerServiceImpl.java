package com.example.fooddeliveryapp.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.fooddeliveryapp.dto.CustomerDto;
import com.example.fooddeliveryapp.exceptions.CustomerNotFoundException;
import com.example.fooddeliveryapp.exceptions.DuplicateCustomerException;
import com.example.fooddeliveryapp.models.Customer;
import com.example.fooddeliveryapp.repository.ICustomerRepository;
import com.example.fooddeliveryapp.service.ICustomerService;
import com.example.fooddeliveryapp.util.convertor.CustomerConvertor;

@Service
public class CustomerServiceImpl implements ICustomerService {//, UserDetailsService {

	private ICustomerRepository customerRepository;

	private CustomerConvertor customerConvertor;

	@Autowired
	public CustomerServiceImpl(ICustomerRepository customerRepository, CustomerConvertor customerConvertor) {
		super();
		this.customerRepository = customerRepository;
		this.customerConvertor = customerConvertor;
	}

	@Override
	public Customer registerCustomer(CustomerDto customerDto) {

		Customer customer = customerConvertor.convert(customerDto);
		Optional<Customer> foundCustomer = customerRepository.findByUsername(customerDto.getUsername());
		if (foundCustomer.isPresent())
			throw new DuplicateCustomerException("Duplicate Customer");

		return customerRepository.save(customer);

	}

	@Override
	public Customer loginCustomer(CustomerDto customerDto) {

		Customer customer = customerConvertor.convert(customerDto);

		Example<Customer> exampleCustomer = Example.of(customer);
		Optional<Customer> foundCustomer = Optional.ofNullable(customerRepository.findOne(exampleCustomer)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found")));

		return foundCustomer.get();

	}

	@Override
	public Optional<Customer> findCustomerByUsername(String username) {

		return Optional.ofNullable(customerRepository.findByUsername(username)
				.orElseThrow(() -> new CustomerNotFoundException("customer not found")));
	}

	/*
	 * loadUserByUsername() used for Spring security Authentication & Authorization.
	 */
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);
//
//		if (optionalCustomer.isEmpty()) {
//			throw new CustomerNotFoundException("Customer not found");
//		}
//
//		return new org.springframework.security.core.userdetails.User(optionalCustomer.get().getUsername(),
//				optionalCustomer.get().getPassword(), Collections.emptyList());
//
//	}

}
