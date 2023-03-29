package com.example.fooddeliveryapp.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@ManyToMany(mappedBy = "foodList")
	@JsonBackReference
	private List<Hotel> hotelList;

	public Food() {
	}

	public Food(String name, List<Hotel> hotelList) {
		super();
		
		this.name = name;
		this.hotelList = hotelList;
	}

	public Integer getId() {
		return id;
	}

	public Food setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Food setName(String name) {
		this.name = name;
		return this;
	}

	public List<Hotel> getHotelList() {
		return hotelList;
	}

	public Food setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
		return this;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", hotelList=" + hotelList + "]";
	}

}
