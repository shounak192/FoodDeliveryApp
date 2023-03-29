package com.example.fooddeliveryapp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String cuisine;

	/*
	 * @JsonProperty-Write Only access allows to print only necessary hotel details
	 * without its underlying food list of each hotel.
	 */
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonManagedReference
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "hotel_food", joinColumns = {
			@JoinColumn(name = "hotel_id", referencedColumnName = "id") }, inverseJoinColumns = @JoinColumn(name = "food_id", referencedColumnName = "id"))
	private List<Food> foodList;

	public Hotel() {
	}

	public Hotel(String name, String cuisine, List<Food> foodList) {
		super();

		this.name = name;
		this.cuisine = cuisine;
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

	public String getCuisine() {
		return cuisine;
	}

	public Hotel setCuisine(String cuisine) {
		this.cuisine = cuisine;
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
		return "Hotel [id=" + id + ", name=" + name + ", cuisine=" + cuisine + ", foodList=" + foodList + "]";
	}

}
