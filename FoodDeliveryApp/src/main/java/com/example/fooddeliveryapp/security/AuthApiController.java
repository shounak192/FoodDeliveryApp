package com.example.fooddeliveryapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.fooddeliveryapp.exceptions.InvalidCredentialsException;
import com.example.fooddeliveryapp.service.implementations.CustomerServiceImpl;

@RestController
public class AuthApiController {

	private AuthenticationManager authenticationManager;

	private JwtUtil jwtTokenUtil;

	private CustomerServiceImpl customerService;

	@Autowired
	public AuthApiController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil,
			CustomerServiceImpl customerService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.customerService = customerService;
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new InvalidCredentialsException("Incorrect username or password");
		}

		final UserDetails userDetails = customerService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}