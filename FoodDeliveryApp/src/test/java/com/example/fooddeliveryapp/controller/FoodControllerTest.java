package com.example.fooddeliveryapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fooddeliveryapp.dto.FoodDto;
import com.example.fooddeliveryapp.models.Food;
import com.example.fooddeliveryapp.service.implementations.FoodServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class FoodControllerTest {
	
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;
	
	@MockBean
	private FoodServiceImpl foodService;
	
	private FoodDto foodDto;
	
	private Food food;
	
	private List<Food> foodList = new ArrayList<>();
	
	@Autowired
	private FoodControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
		super();
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@BeforeEach
	public void setup() {
		foodDto= new FoodDto("name", new ArrayList<>());
		foodDto.setId(1);
		food = new Food("name", new ArrayList<>());
		food.setId(1);
		foodList.add(food);
	}

	@Test
	void createFoodTest() throws Exception {
		when(foodService.createFood(foodDto)).thenReturn(food);
		mockMvc.perform(post("/food/create").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(foodDto))).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	void viewFoodTest() throws Exception {
		when(foodService.viewFood(1)).thenReturn(food);
		mockMvc.perform(get("/food/view/{id}",1)).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	void viewAllFoodTest() throws Exception {
		when(foodService.viewAllFoods()).thenReturn(foodList);
		mockMvc.perform(get("/food/viewall")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	void updateFoodTest() throws Exception {
		when(foodService.updateFood(1, foodDto)).thenReturn(food);
		mockMvc.perform(put("/food/update/{id}",1).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(foodDto))).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	void deleteFoodTest() throws Exception {
		when(foodService.deleteFood(1)).thenReturn(food);
		mockMvc.perform(delete("/food/delete/{id}",1)).andExpect(status().isOk());
	}

}
