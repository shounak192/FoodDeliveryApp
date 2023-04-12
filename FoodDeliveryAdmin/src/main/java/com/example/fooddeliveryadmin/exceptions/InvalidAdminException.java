package com.example.fooddeliveryadmin.exceptions;

public class InvalidAdminException extends RuntimeException {
	
	private final String message;

	public InvalidAdminException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
