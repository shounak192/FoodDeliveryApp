package com.example.fooddeliveryapp.util.convertor;

import org.springframework.stereotype.Component;

import com.example.fooddeliveryapp.dto.FoodDto;
import com.example.fooddeliveryapp.models.Food;
import com.example.fooddeliveryapp.util.ModelMapperGenerator;

@Component
public class FoodConvertor {
	
	public Food convert(FoodDto foodDto) {
		return ModelMapperGenerator.getModelMapper().map(foodDto, Food.class);
	}

}
