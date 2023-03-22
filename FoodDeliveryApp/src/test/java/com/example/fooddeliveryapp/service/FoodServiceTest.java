package com.example.fooddeliveryapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.example.fooddeliveryapp.dto.FoodDto;
import com.example.fooddeliveryapp.exceptions.FoodNotFoundException;
import com.example.fooddeliveryapp.exceptions.HotelNotFoundException;
import com.example.fooddeliveryapp.models.Food;
import com.example.fooddeliveryapp.repository.IFoodRepository;
import com.example.fooddeliveryapp.service.implementations.FoodServiceImpl;
import com.example.fooddeliveryapp.util.convertor.FoodConvertor;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FoodServiceTest {

	@InjectMocks
	private FoodServiceImpl foodService;

	@Mock
	private IFoodRepository foodRepository;
	
	@Mock
	private FoodConvertor foodConvertor;

	private FoodDto foodDto;

	private Food food;

	private List<Food> foodList = new ArrayList<>();
	
	@BeforeEach
	public void setup() {
		foodDto = new FoodDto("name", new ArrayList<>());
		foodDto.setId(1);
		food = new Food("name", new ArrayList<>());
		food.setId(1);
		foodList.add(food);
		when(foodConvertor.convert(foodDto)).thenReturn(food);
	}

	@Test
	void createFoodTest() {

		when(foodRepository.save(food)).thenReturn(food);
		assertEquals(food, foodService.createFood(foodDto));
	}

	@Test
	void viewFoodTest() {
		
		when(foodRepository.findById(1)).thenReturn(Optional.ofNullable(food));
		assertEquals(food, foodService.viewFood(1));
	}
	
	@Test
	void InvalidViewFoodTest() {
		
		when(foodRepository.findById(2)).thenReturn(Optional.ofNullable(null));
		assertThrows(FoodNotFoundException.class,()-> foodService.viewFood(2));
	}

	@Test
	void viewAllFoodsTest() {

		when(foodRepository.findAll()).thenReturn(foodList);
		assertEquals(foodList, foodService.viewAllFoods());
	}

	@Test
	void updateFoodTest() {

		when(foodRepository.findById(1)).thenReturn(Optional.ofNullable(food));
		when(foodRepository.save(food)).thenReturn(food);
		assertEquals(food, foodService.updateFood(1, foodDto));
	}
	
	@Test
	void InvalidUpdateFoodTest() {

		when(foodRepository.findById(2)).thenReturn(Optional.ofNullable( null));
		assertThrows(FoodNotFoundException.class,()-> foodService.updateFood(2, foodDto));
	}

	@Test
	void deleteFoodTest() {

		when(foodRepository.findById(1)).thenReturn(Optional.ofNullable(food));
		assertEquals(food, foodService.deleteFood(1));
	}
	
	@Test
	void InvalidDeleteFoodTest() {

		when(foodRepository.findById(2)).thenReturn(Optional.ofNullable(null));
		assertThrows(FoodNotFoundException.class,()-> foodService.deleteFood(2));
	}

}
