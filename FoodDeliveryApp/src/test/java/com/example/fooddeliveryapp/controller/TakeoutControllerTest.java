package com.example.fooddeliveryapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fooddeliveryapp.dto.TakeoutDto;
import com.example.fooddeliveryapp.models.Takeout;
import com.example.fooddeliveryapp.service.implementations.TakeoutServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class TakeoutControllerTest {

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@MockBean
	private TakeoutServiceImpl takeoutService;

	private TakeoutDto takeoutDto;

	private Takeout takeout;

	private List<Takeout> takeoutList = new ArrayList<>();

	@Autowired
	private TakeoutControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
		super();
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@BeforeEach
	public void setup() {
		takeoutDto = new TakeoutDto(1, 1, 1);
		takeout = new Takeout(1, 1, 1);
	}

	@Test
//	@WithMockUser
	void placeOrderTest() throws Exception {
		when(takeoutService.placeOrder(takeoutDto)).thenReturn(takeout);
		mockMvc.perform(post("/takeout/place").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(takeoutDto))).andExpect(status().isOk());

	}

	@Test
//	@WithMockUser
	void cancelOrdertTest() throws Exception {
		when(takeoutService.cancelOrder(1)).thenReturn(takeout);
		mockMvc.perform(delete("/takeout/cancel/{id}", 1)).andExpect(status().isOk());
	}

	@Test
//	@WithMockUser
	void viewOrderTest() throws Exception {
		when(takeoutService.viewOrder(1)).thenReturn(takeout);
		mockMvc.perform(get("/takeout/view/{id}", 1)).andExpect(status().isOk());
	}

	@Test
//	@WithMockUser
	void viewAllOrdersTest() throws Exception {
		when(takeoutService.viewAllOrders()).thenReturn(takeoutList);
		mockMvc.perform(get("/takeout/viewall")).andExpect(status().isOk());
	}

	@Test
//	@WithMockUser
	void viewAllOrdersByCustomerTest() throws Exception {
		when(takeoutService.viewAllOrdersByCustomer(1)).thenReturn(takeoutList);
		mockMvc.perform(get("/takeout/viewall/{customerId}", 1)).andExpect(status().isOk());
	}

}
