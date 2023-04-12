package com.example.fooddeliveryadmin.exceptions;

public class DuplicateAdminPresent extends RuntimeException {
	
	private final String message;

	public DuplicateAdminPresent(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
	

}
