package com.example.fooddeliveryapp.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class HotelDto {

	private Integer id;
	private String name;
	private String cuisineType;

	private List<FoodDto> foodList;

	public HotelDto(String name, String cuisineType, List<FoodDto> foodList) {
		super();
		
		this.name = name;
		this.cuisineType = cuisineType;
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

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public List<FoodDto> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<FoodDto> foodList) {
		this.foodList = foodList;
	}

	@Override
	public String toString() {
		return "HotelDto [id=" + id + ", name=" + name + ", cuisineType=" + cuisineType + ", foodList=" + foodList
				+ "]";
	}

}
