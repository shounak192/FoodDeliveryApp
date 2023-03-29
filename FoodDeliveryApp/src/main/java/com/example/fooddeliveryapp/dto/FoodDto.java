package com.example.fooddeliveryapp.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class FoodDto {

	private Integer id;

	@NotBlank(message = "Food name should not be blank.")
	private String name;

	private List<HotelDto> hotelList;

	public FoodDto(String name, List<HotelDto> hotelList) {
		super();

		this.name = name;
		this.hotelList = hotelList;
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

	public List<HotelDto> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<HotelDto> hotelList) {
		this.hotelList = hotelList;
	}

	@Override
	public String toString() {
		return "FoodDto [id=" + id + ", name=" + name + ", hotelList=" + hotelList + "]";
	}

}
