package com.example.fooddeliveryapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerDto {

	private Integer id;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]{3,10}$", message = "Username must be alphanumeric of length between 3 and 10")
	private String username;

	@NotNull
	private String password;

	public CustomerDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}
