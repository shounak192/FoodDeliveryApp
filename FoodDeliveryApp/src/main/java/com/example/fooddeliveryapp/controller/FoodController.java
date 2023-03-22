package com.example.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fooddeliveryapp.dto.FoodDto;
import com.example.fooddeliveryapp.models.Food;
import com.example.fooddeliveryapp.service.IFoodService;

@RestController
@RequestMapping("/food")
public class FoodController {

	private IFoodService foodService;

	@Autowired
	private FoodController(IFoodService foodService) {
		super();
		this.foodService = foodService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Food> createFood(@RequestBody FoodDto foodDto) {

		Food food = foodService.createFood(foodDto);
		return new ResponseEntity<Food>(food, HttpStatus.OK);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Food> viewFood(@PathVariable Integer id) {

		Food food = foodService.viewFood(id);
		return new ResponseEntity<Food>(food, HttpStatus.OK);
	}

	@GetMapping("/viewall")
	public ResponseEntity<List<Food>> viewAllFood() {

		List<Food> foodList = foodService.viewAllFoods();
		return new ResponseEntity<List<Food>>(foodList, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Food> updateFood(@PathVariable Integer id, @RequestBody FoodDto foodDto) {

		Food food = foodService.updateFood(id, foodDto);
		return new ResponseEntity<Food>(food, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Food> deleteFood(@PathVariable Integer id) {

		Food food = foodService.deleteFood(id);
		return new ResponseEntity<Food>(food, HttpStatus.OK);
	}

}
