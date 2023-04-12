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

	private Optional<Customer> optionalCustomer;

//	private UserDetails user;

	@BeforeEach
	void setup() {
		customerDto = new CustomerDto("username", "password");
		customerDto.setId(1);
		customer = new Customer("username", "password");
		optionalCustomer = Optional.ofNullable(customer);
//		user = new User(customer.getUsername(), customer.getPassword(), Collections.emptyList());
		when(customerConvertor.convert(customerDto)).thenReturn(customer);
	}

	@Test
	void registerCustomertest() {

		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.registerCustomer(customerDto));
	}

	@Test
	void InvalidRegisterCustomerTest() {

		when(customerRepository.findByUsername(customerDto.getUsername())).thenReturn(optionalCustomer);
		assertThrows(DuplicateCustomerException.class, () -> customerService.registerCustomer(customerDto));
	}

	@Test
	void InvalidLoginCustomerTest() {

		when(customerRepository.findByUsername(customer.getUsername())).thenReturn(Optional.ofNullable(null));
		assertThrows(CustomerNotFoundException.class, () -> customerService.loginCustomer(customerDto));
	}

	@Test
	void findCustomerByUsernameTest() {
		when(customerRepository.findByUsername(customer.getUsername())).thenReturn(optionalCustomer);
		assertEquals(optionalCustomer, customerService.findCustomerByUsername(customer.getUsername()));
	}

	@Test
	void InvalidFindCustomerByUsernameTest() {
		when(customerRepository.findByUsername(customer.getUsername())).thenReturn(Optional.ofNullable(null));
		assertThrows(CustomerNotFoundException.class,
				() -> customerService.findCustomerByUsername(customer.getUsername()));
	}

//	@Test
//	void loadUserByUsernameTest() {
//		when(customerRepository.findByUsername(customer.getUsername())).thenReturn(optionalCustomer);
//		assertEquals(user, customerService.loadUserByUsername(customer.getUsername()));
//	}
//
//	@Test
//	void InvalidLoadUserByUsernameTest() {
//		when(customerRepository.findByUsername(customer.getUsername())).thenReturn(Optional.ofNullable(null));
//		assertThrows(CustomerNotFoundException.class, () -> customerService.loadUserByUsername(customer.getUsername()));
//	}

}
