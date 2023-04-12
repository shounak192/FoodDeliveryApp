package com.example.fooddeliveryadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.fooddeliveryadmin.dto.AdminDto;
import com.example.fooddeliveryadmin.dto.FoodDto;
import com.example.fooddeliveryadmin.dto.HotelDto;
import com.example.fooddeliveryadmin.dto.TakeoutDto;
import com.example.fooddeliveryadmin.models.Admin;
import com.example.fooddeliveryadmin.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private IAdminService adminService;

	@Autowired
	@Qualifier("getFoodDeliveryAppWebClient")
	private WebClient foodDeliveryAppWebClientBuilder;

	@Autowired
	public AdminController(IAdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("/register")
	public ResponseEntity<Admin> register(@RequestBody AdminDto adminDto) {

		Admin admin = adminService.register(adminDto);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<Admin> login(@RequestBody AdminDto adminDto) {

		Admin admin = adminService.login(adminDto);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@PostMapping("/food/create")
	public ResponseEntity<FoodDto> createFood(@RequestBody FoodDto foodDto) {

		FoodDto food = foodDeliveryAppWebClientBuilder.post().uri("/food/create").accept(MediaType.APPLICATION_JSON)
				.bodyValue(foodDto).retrieve().bodyToMono(FoodDto.class).block();
		return new ResponseEntity<>(food, HttpStatus.OK);
	}

	@GetMapping("/food/view/{id}")
	public ResponseEntity<FoodDto> viewFood(@PathVariable Integer id) {

		FoodDto food = foodDeliveryAppWebClientBuilder.get().uri("/food/view/{id}", id).retrieve()
				.bodyToMono(FoodDto.class).block();
		return new ResponseEntity<>(food, HttpStatus.OK);
	}

	@GetMapping("/food/viewall")
	public ResponseEntity<List<FoodDto>> viewAllFood() {

		List<FoodDto> foodList = foodDeliveryAppWebClientBuilder.get().uri("/food/viewall").retrieve()
				.bodyToFlux(FoodDto.class).collectList().block();
		return new ResponseEntity<>(foodList, HttpStatus.OK);

	}

	@PutMapping("/food/update/{id}")
	public ResponseEntity<FoodDto> updateFood(@PathVariable Integer id, @RequestBody FoodDto foodDto) {

		FoodDto food = foodDeliveryAppWebClientBuilder.put().uri("/food/update/{id}", id)
				.accept(MediaType.APPLICATION_JSON).bodyValue(foodDto).retrieve().bodyToMono(FoodDto.class).block();
		return new ResponseEntity<>(food, HttpStatus.OK);
	}

	@DeleteMapping("/food/delete/{id}")
	public ResponseEntity<FoodDto> deleteFood(@PathVariable Integer id) {

		FoodDto food = foodDeliveryAppWebClientBuilder.delete().uri("/food/delete/{id}", id).retrieve()
				.bodyToMono(FoodDto.class).block();
		return new ResponseEntity<>(food, HttpStatus.OK);
	}

	@PostMapping("/hotel/create")
	public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto) {

		HotelDto hotel = foodDeliveryAppWebClientBuilder.post().uri("/hotel/create").accept(MediaType.APPLICATION_JSON)
				.bodyValue(hotelDto).retrieve().bodyToMono(HotelDto.class).block();
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

	@GetMapping("/hotel/view/{id}")
	public ResponseEntity<HotelDto> viewHotel(@PathVariable Integer id) {

		HotelDto hotel = foodDeliveryAppWebClientBuilder.get().uri("/food/view/{id}", id).retrieve()
				.bodyToMono(HotelDto.class).block();
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

	@GetMapping("/hotel/viewall")
	public ResponseEntity<List<HotelDto>> viewAllHotel() {

		List<HotelDto> hotelList = foodDeliveryAppWebClientBuilder.get().uri("/hotel/viewall").retrieve()
				.bodyToFlux(HotelDto.class).collectList().block();
		return new ResponseEntity<>(hotelList, HttpStatus.OK);

	}

	@PutMapping("/hotel/update/{id}")
	public ResponseEntity<HotelDto> updateHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {

		HotelDto hotel = foodDeliveryAppWebClientBuilder.put().uri("/hotel/update/{id}", id)
				.accept(MediaType.APPLICATION_JSON).bodyValue(hotelDto).retrieve().bodyToMono(HotelDto.class).block();
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

	@DeleteMapping("/hotel/delete/{id}")
	public ResponseEntity<HotelDto> deleteHotel(@PathVariable Integer id) {

		HotelDto hotel = foodDeliveryAppWebClientBuilder.delete().uri("/hotel/delete/{id}", id).retrieve()
				.bodyToMono(HotelDto.class).block();
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

	@PostMapping("/hotel/addfood/{hotelId}/{foodId}")
	public ResponseEntity<HotelDto> addFood(@PathVariable Integer hotelId, @PathVariable Integer foodId) {

		HotelDto hotel = foodDeliveryAppWebClientBuilder.post().uri("/hotel/addfood/{hotelId}/{foodId}", hotelId, foodId)
				.retrieve().bodyToMono(HotelDto.class).block();
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

	@DeleteMapping("/hotel/removefood/{hotelId}/{foodId}")
	public ResponseEntity<HotelDto> removeFood(@PathVariable Integer hotelId, @PathVariable Integer foodId) {

		HotelDto hotel = foodDeliveryAppWebClientBuilder.delete()
				.uri("/hotel/removeFood/{hotelId}/{foodId}", hotelId, foodId).retrieve().bodyToMono(HotelDto.class)
				.block();
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

	@GetMapping("/hotel/viewall/{cuisine}")
	public ResponseEntity<List<HotelDto>> findAllHotelsByCuisine(@PathVariable String cuisine) {

		List<HotelDto> hotelList = foodDeliveryAppWebClientBuilder.get().uri("/hotel/viewall/{cuisine}", cuisine)
				.retrieve().bodyToFlux(HotelDto.class).collectList().block();
		return new ResponseEntity<>(hotelList, HttpStatus.OK);
	}
	
	@GetMapping("/takeout/viewall")
	public ResponseEntity<List<TakeoutDto>> viewAllOrders() {

		List<TakeoutDto> takeoutList = foodDeliveryAppWebClientBuilder.get().uri("/takeout/viewall")
				.retrieve().bodyToFlux(TakeoutDto.class).collectList().block();
		return new ResponseEntity<>(takeoutList, HttpStatus.OK);
	}
	
	@GetMapping("/takeout/viewall/{customerId}")
	public ResponseEntity<List<TakeoutDto>> viewAllOrdersByCustomer(@PathVariable Integer customerId) {

		List<TakeoutDto> takeoutList = foodDeliveryAppWebClientBuilder.get().uri("/takeout/viewall/{customerId}",customerId)
				.retrieve().bodyToFlux(TakeoutDto.class).collectList().block();
		return new ResponseEntity<>(takeoutList, HttpStatus.OK);
	}

}
