package com.example.fooddeliveryapp.util;

import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.fooddeliveryapp.exceptions.CustomerNotFoundException;
import com.example.fooddeliveryapp.exceptions.DuplicateCustomerException;
import com.example.fooddeliveryapp.exceptions.FoodNotFoundException;
import com.example.fooddeliveryapp.exceptions.HotelNotFoundException;
import com.example.fooddeliveryapp.exceptions.TakeoutNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { CustomerNotFoundException.class, DuplicateCustomerException.class,
			FoodNotFoundException.class, HotelNotFoundException.class, TakeoutNotFoundException.class,
			MethodArgumentNotValidException.class, ConstraintViolationException.class })
	public ResponseEntity<ErrorResponse> handleGlobalException(WebRequest request, Exception e) {

		ErrorResponse response = new ErrorResponse();
		response.setError(e.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.name());
		response.setTimeStamp(LocalDate.now());
		response.setPath(request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
