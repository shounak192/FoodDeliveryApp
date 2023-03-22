package com.example.fooddeliveryapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Takeout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer customerId;
	private Integer hotelId;
	private Integer foodId;

	public Takeout() {
	}

	public Takeout(Integer customerId, Integer hotelId, Integer foodId) {
		super();
		this.customerId = customerId;
		this.hotelId = hotelId;
		this.foodId = foodId;
	}

	public Integer getId() {
		return id;
	}

	public Takeout setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Takeout setCustomerId(Integer customerId) {
		this.customerId = customerId;
		return this;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public Takeout setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
		return this;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public Takeout setFoodId(Integer foodId) {
		this.foodId = foodId;
		return this;
	}

	@Override
	public String toString() {
		return "Takeout [id=" + id + ", customerId=" + customerId + ", hotelId=" + hotelId + ", foodId=" + foodId + "]";
	}

}
