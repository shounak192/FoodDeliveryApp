package com.example.fooddeliveryapp.exceptions;


public class CustomerNotFoundException extends RuntimeException {
	
	private final String message;

	public CustomerNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	
	
	

}
