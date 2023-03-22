package com.example.fooddeliveryapp.models;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String cuisineType;

	@JsonManagedReference
	@ManyToMany( cascade = CascadeType.ALL)
	@JoinTable(name = "hotel_food", joinColumns = {
			@JoinColumn(name = "hotel_id", referencedColumnName = "id") }, inverseJoinColumns = 
					@JoinColumn(name = "food_id", referencedColumnName = "id"))
	private List<Food> foodList;

	public Hotel() {
	}

	public Hotel(String name, String cuisineType, List<Food> foodList) {
		super();
		
		this.name = name;
		this.cuisineType = cuisineType;
		this.foodList = foodList;
	}

	public Integer getId() {
		return id;
	}

	public Hotel setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Hotel setName(String name) {
		this.name = name;
		return this;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public Hotel setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
		return this;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public Hotel setFoodList(List<Food> foodList) {
		this.foodList = foodList;
		return this;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", cuisineType=" + cuisineType + ", foodList=" + foodList + "]";
	}

}
