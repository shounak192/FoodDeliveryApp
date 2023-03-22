package com.example.fooddeliveryapp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fooddeliveryapp.dto.HotelDto;
import com.example.fooddeliveryapp.models.Hotel;
import com.example.fooddeliveryapp.service.implementations.HotelServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerTest {

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@MockBean
	private HotelServiceImpl hotelService;

	private HotelDto hotelDto;

	private Hotel hotel;

	private List<Hotel> hotelList = new ArrayList<>();

	@Autowired
	private HotelControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
		super();
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@BeforeEach
	void setup() {
		hotelDto = new HotelDto("name", "cuisine", new ArrayList<>());
		hotelDto.setId(1);
		hotel = new Hotel("name", "cuisine", new ArrayList<>());
		hotel.setId(1);
		hotelList.add(hotel);
	}

	@Test
	void createHotelTest() throws Exception {
		when(hotelService.createHotel(hotelDto)).thenReturn(hotel);
		mockMvc.perform(post("/hotel/create").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(hotelDto))).andExpect(status().isOk());
	}

	@Test
	void viewHotelTest() throws Exception {

		when(hotelService.viewHotel(1)).thenReturn(hotel);
		mockMvc.perform(get("/hotel/view/{id}", 1)).andExpect(status().isOk());
	}

	@Test
	void viewAllHotelsTest() throws Exception {

		when(hotelService.viewAllHotels()).thenReturn(hotelList);
		mockMvc.perform(get("/hotel/viewall")).andExpect(status().isOk());
	}

	@Test
	void updateHotelTest() throws Exception {

		when(hotelService.updateHotel(1, hotelDto)).thenReturn(hotel);
		mockMvc.perform(put("/hotel/update/{id}", 1).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(hotelDto))).andExpect(status().isOk());
	}

	@Test
	void deleteHotelTest() throws Exception {
		when(hotelService.deleteHotel(1)).thenReturn(hotel);
		mockMvc.perform(delete("/hotel/delete/{id}", 1)).andExpect(status().isOk());
	}

	@Test
	void addFoodTest() throws Exception {
		
		when(hotelService.addFood(1,1)).thenReturn(hotel);
		mockMvc.perform(post("/hotel/addfood/{hotelId}/{foodId}", 1,1)).andExpect(status().isOk());
	}

	@Test
	void removeFoodTest() throws Exception {
		
		when(hotelService.removeFood(1,1)).thenReturn(hotel);
		mockMvc.perform(delete("/hotel/removefood/{hotelId}/{foodId}", 1,1)).andExpect(status().isOk());
	}

}
