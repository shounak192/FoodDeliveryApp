package com.example.fooddeliveryadmin.util;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.fooddeliveryadmin.exceptions.DuplicateAdminPresent;
import com.example.fooddeliveryadmin.exceptions.InvalidAdminException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { DuplicateAdminPresent.class, InvalidAdminException.class, Exception.class })
	public ResponseEntity<ErrorResponse> handleGlobalException(WebRequest request, Exception e) {

		ErrorResponse response = new ErrorResponse();
		response.setError(e.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.name());
		response.setTimeStamp(LocalDate.now());
		response.setPath(request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
