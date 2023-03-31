package com.example.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fooddeliveryapp.dto.TakeoutDto;
import com.example.fooddeliveryapp.models.Takeout;
import com.example.fooddeliveryapp.service.ITakeoutService;

@RestController
@RequestMapping("/takeout")
public class TakeoutController {
	
	private ITakeoutService takeoutService;

	@Autowired
	private TakeoutController(ITakeoutService takeoutService) {
		super();
		this.takeoutService = takeoutService;
	}
	
	@PostMapping("/place")
	public ResponseEntity<Takeout> placeOrder(@RequestBody TakeoutDto takeoutDto) {
	
		Takeout takeout = takeoutService.placeOrder(takeoutDto);
		return new ResponseEntity<>(takeout, HttpStatus.OK);
	}
	
	@DeleteMapping("/cancel/{id}")
	public ResponseEntity<Takeout> cancelOrder(@PathVariable Integer id) {
		
		Takeout takeout = takeoutService.cancelOrder(id);
		return new ResponseEntity<>(takeout, HttpStatus.OK);
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Takeout> viewOrder(@PathVariable Integer id) {
		
		Takeout takeout = takeoutService.viewOrder(id);
		return new ResponseEntity<>(takeout, HttpStatus.OK);
	}
	
	@GetMapping("/viewall")
	public ResponseEntity<List<Takeout>> viewAllOrders() {
		
		List<Takeout> takeoutList = takeoutService.viewAllOrders();
		return new ResponseEntity<>(takeoutList, HttpStatus.OK);
	}
	
	@GetMapping("/viewall/{customerId}")
	public ResponseEntity<List<Takeout>> viewAllOrdersByCustomer(@PathVariable Integer customerId) {
		
		List<Takeout> takeoutList = takeoutService.viewAllOrdersByCustomer(customerId);
		return new ResponseEntity<>(takeoutList, HttpStatus.OK);
	}

}
