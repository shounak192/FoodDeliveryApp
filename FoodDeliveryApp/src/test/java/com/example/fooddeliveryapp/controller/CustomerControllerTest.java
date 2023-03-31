package com.example.fooddeliveryapp.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fooddeliveryapp.dto.CustomerDto;
import com.example.fooddeliveryapp.exceptions.DuplicateCustomerException;
import com.example.fooddeliveryapp.models.Customer;
import com.example.fooddeliveryapp.service.implementations.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@MockBean
	private CustomerServiceImpl customerService;

	private CustomerDto customerDto;

	private Customer customer;

	@Autowired
	public CustomerControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
		super();
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@BeforeEach
	void setup() {
		customerDto = new CustomerDto("username", "password");
		customer = new Customer("username", "password");
	}

	@Test
	void registerCustomertest() throws Exception {

		when(customerService.registerCustomer(customerDto)).thenReturn(customer);
		mockMvc.perform(post("/customer/register").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customerDto))).andExpect(status().isOk());
	}

	@Test
	void registerDuplicateCustomerTest() throws Exception {

		when(customerService.registerCustomer(customerDto)).thenThrow(DuplicateCustomerException.class);
		mockMvc.perform(post("/customer/register").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customerDto))).andExpect(status().isOk());

	}

	@Test
	void loginCustomerTest() throws Exception {
		given(customerService.loginCustomer(customerDto)).willReturn(customer);
		mockMvc.perform(get("/customer/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customerDto))).andExpect(status().isOk());
	}

}
