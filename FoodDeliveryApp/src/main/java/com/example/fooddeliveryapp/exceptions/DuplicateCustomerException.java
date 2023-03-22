package com.example.fooddeliveryapp.exceptions;

public class DuplicateCustomerException extends RuntimeException {

	private final String message;

	public DuplicateCustomerException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
