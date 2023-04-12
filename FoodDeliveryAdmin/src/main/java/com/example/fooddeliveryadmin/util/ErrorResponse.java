package com.example.fooddeliveryadmin.util;

import java.time.LocalDate;

public class ErrorResponse {

	private String status;
	private String error;
	private LocalDate timeStamp;
	private String path;

	public ErrorResponse(String status, String error, LocalDate timeStamp, String path) {
		super();
		this.status = status;
		this.error = error;
		this.timeStamp = timeStamp;
		this.path = path;
	}

	public ErrorResponse() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
