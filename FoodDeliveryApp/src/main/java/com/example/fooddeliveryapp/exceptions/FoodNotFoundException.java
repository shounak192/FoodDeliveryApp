package com.example.fooddeliveryapp.exceptions;

public class FoodNotFoundException extends RuntimeException {

	private final String message;

	public FoodNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
