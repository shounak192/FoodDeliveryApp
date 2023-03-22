package com.example.fooddeliveryapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Example;

import com.example.fooddeliveryapp.dto.CustomerDto;
import com.example.fooddeliveryapp.exceptions.CustomerNotFoundException;
import com.example.fooddeliveryapp.exceptions.DuplicateCustomerException;
import com.example.fooddeliveryapp.models.Customer;
import com.example.fooddeliveryapp.repository.ICustomerRepository;
import com.example.fooddeliveryapp.service.implementations.CustomerServiceImpl;
import com.example.fooddeliveryapp.util.convertor.CustomerConvertor;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private ICustomerRepository customerRepository;

	@Mock
	private CustomerConvertor customerConvertor;

	private CustomerDto customerDto;

	private Customer customer;

	private Example<Customer> exampleCustomer;

	@BeforeEach
	void setup() {
		customerDto = new CustomerDto("username", "password");
		customerDto.setId(1);
		customer = new Customer("username", "password");
		exampleCustomer = Example.of(customer);
		when(customerConvertor.convert(customerDto)).thenReturn(customer);
	}

	@Test
	void registerCustomertest() {

		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.registerCustomer(customerDto));
	}

	@Test
	void InvalidRegisterCustomerTest() {

		when(customerRepository.findOne(exampleCustomer)).thenReturn(Optional.ofNullable(customer));
		assertThrows(DuplicateCustomerException.class, () -> customerService.registerCustomer(customerDto));
	}

	@Test
	void loginCustomerTest() {

		when(customerRepository.findOne(exampleCustomer)).thenReturn(Optional.ofNullable(customer));
		assertEquals(customer, customerService.loginCustomer(customerDto));
	}

	@Test
	void InvalidLoginCustomerTest() {

		when(customerRepository.findOne(exampleCustomer)).thenReturn(Optional.ofNullable(null));
		assertThrows(CustomerNotFoundException.class, () -> customerService.loginCustomer(customerDto));
	}

}
