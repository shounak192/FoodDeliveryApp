package com.example.fooddeliveryapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fooddeliveryapp.dto.FoodDto;
import com.example.fooddeliveryapp.models.Food;

@Service
public interface IFoodService {
	
	public Food createFood(FoodDto foodDto);
	
	public Food viewFood(Integer id);
	
	public List<Food> viewAllFoods();
	
	public Food updateFood(Integer id, FoodDto foodDto);
	
	public Food deleteFood(Integer id);
	
	

}
