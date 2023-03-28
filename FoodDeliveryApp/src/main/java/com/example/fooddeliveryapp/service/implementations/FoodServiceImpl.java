package com.example.fooddeliveryapp.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fooddeliveryapp.dto.FoodDto;
import com.example.fooddeliveryapp.exceptions.FoodNotFoundException;
import com.example.fooddeliveryapp.exceptions.HotelNotFoundException;
import com.example.fooddeliveryapp.models.Food;
import com.example.fooddeliveryapp.models.Hotel;
import com.example.fooddeliveryapp.repository.IFoodRepository;
import com.example.fooddeliveryapp.service.IFoodService;
import com.example.fooddeliveryapp.util.ModelMapperGenerator;
import com.example.fooddeliveryapp.util.convertor.FoodConvertor;

@Service
public class FoodServiceImpl implements IFoodService {

	private IFoodRepository foodRepository;

	private FoodConvertor foodConvertor;

	@Autowired
	private FoodServiceImpl(IFoodRepository foodRepository, FoodConvertor foodConvertor) {
		super();
		this.foodRepository = foodRepository;
		this.foodConvertor = foodConvertor;
	}

	@Override
	public Food createFood(FoodDto foodDto) {

		Food food = foodConvertor.convert(foodDto);
		return foodRepository.save(food);
	}

	@Override
	public Food viewFood(Integer id) {

		Optional<Food> optionalFood = Optional
				.ofNullable(foodRepository.findById(id).orElseThrow(() -> new FoodNotFoundException("Food not found")));
		return optionalFood.get();
	}

	@Override
	public List<Food> viewAllFoods() {

		return foodRepository.findAll();
	}

	@Override
	public Food updateFood(Integer id, FoodDto foodDto) {

		Food food = foodRepository.findById(id).orElseThrow(() -> new FoodNotFoundException("Food not found"))
				.setName(foodDto.getName());
		return foodRepository.save(food);

	}

	@Override
	public Food deleteFood(Integer id) {

		Optional<Food> optionalFood = Optional
				.ofNullable(foodRepository.findById(id).orElseThrow(() -> new FoodNotFoundException("Food not found")));
		foodRepository.deleteById(id);
		return optionalFood.get();

	}

}
