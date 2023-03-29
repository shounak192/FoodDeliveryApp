package com.example.fooddeliveryapp.dto;

public class TakeoutDto {

	private Integer id;
	private Integer customerId;
	private Integer hotelId;
	private Integer foodId;

	public TakeoutDto(Integer customerId, Integer hotelId, Integer foodId) {
		super();

		this.customerId = customerId;
		this.hotelId = hotelId;
		this.foodId = foodId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	@Override
	public String toString() {
		return "TakeoutDto [id=" + id + ", customerId=" + customerId + ", hotelId=" + hotelId + ", foodId=" + foodId
				+ "]";
	}

}
