package com.example.fooddeliveryapp.exceptions;

public class InvalidCredentialsException extends RuntimeException {
	
	private final String message;

	public InvalidCredentialsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	

}
