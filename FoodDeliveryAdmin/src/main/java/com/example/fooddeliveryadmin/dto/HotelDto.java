package com.example.fooddeliveryadmin.dto;

import java.util.List;

public class HotelDto {

	private Integer id;

//	@NotBlank(message = "Hotel name should not be blank.")
	private String name;

//	@NotBlank(message = "Cuisine Type should not be blank.")
	private String cuisine;

	private List<FoodDto> foodList;

	public HotelDto(String name, String cuisine, List<FoodDto> foodList) {
		super();

		this.name = name;
		this.cuisine = cuisine;
		this.foodList = foodList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public List<FoodDto> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<FoodDto> foodList) {
		this.foodList = foodList;
	}

	@Override
	public String toString() {
		return "HotelDto [id=" + id + ", name=" + name + ", cuisine=" + cuisine + ", foodList=" + foodList
				+ "]";
	}

}
