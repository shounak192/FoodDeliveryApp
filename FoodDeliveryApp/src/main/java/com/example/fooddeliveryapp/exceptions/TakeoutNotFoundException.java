package com.example.fooddeliveryapp.exceptions;

public class TakeoutNotFoundException extends RuntimeException{
	
	private final String message;

	public TakeoutNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	
	
	

}
