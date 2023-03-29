package com.example.fooddeliveryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fooddeliveryapp.dto.HotelDto;
import com.example.fooddeliveryapp.models.Hotel;
import com.example.fooddeliveryapp.service.IHotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	private IHotelService hotelService;

	@Autowired
	private HotelController(IHotelService hotelService) {
		super();
		this.hotelService = hotelService;
	}

	@PostMapping("/create")
	public ResponseEntity<Hotel> createHotel(@RequestBody HotelDto hotelDto) {

		Hotel hotel = hotelService.createHotel(hotelDto);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);

	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Hotel> viewHotel(@PathVariable Integer id) {

		Hotel hotel = hotelService.viewHotel(id);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);

	}

	@GetMapping("/viewall")
	public ResponseEntity<List<Hotel>> viewAllHotels() {

		List<Hotel> hotelList = hotelService.viewAllHotels();
		return new ResponseEntity<List<Hotel>>(hotelList, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {

		Hotel hotel = hotelService.updateHotel(id, hotelDto);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Hotel> deleteHotel(@PathVariable Integer id) {

		Hotel hotel = hotelService.deleteHotel(id);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}

	@PostMapping("/addfood/{hotelId}/{foodId}")
	public ResponseEntity<Hotel> addFood(@PathVariable Integer hotelId, @PathVariable Integer foodId) {

		Hotel hotel = hotelService.addFood(hotelId, foodId);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	@DeleteMapping("/removefood/{hotelId}/{foodId}")
	public ResponseEntity<Hotel> removeFood(@PathVariable Integer hotelId,@PathVariable Integer foodId) {
		
		Hotel hotel = hotelService.removeFood(hotelId, foodId);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	@GetMapping("/viewall/{cuisine}")
	public ResponseEntity<List<Hotel>> findAllHotelsByCuisine(@PathVariable String cuisine) {
		
		List<Hotel> hotelList = hotelService.findAllHotelsByCuisine(cuisine);
		return new ResponseEntity<>(hotelList, HttpStatus.OK);
	}

}
