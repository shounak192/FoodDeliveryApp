package com.example.fooddeliveryapp.exceptions;

public class HotelNotFoundException extends RuntimeException {
	
	private final String message;

	public HotelNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
