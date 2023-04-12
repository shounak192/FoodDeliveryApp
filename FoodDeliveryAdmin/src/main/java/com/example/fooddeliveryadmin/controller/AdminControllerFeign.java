package com.example.fooddeliveryadmin.controller;

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

import com.example.fooddeliveryadmin.dto.AdminDto;
import com.example.fooddeliveryadmin.dto.FoodDto;
import com.example.fooddeliveryadmin.dto.HotelDto;
import com.example.fooddeliveryadmin.dto.TakeoutDto;
import com.example.fooddeliveryadmin.feignclient.IFoodDeliveryClient;
import com.example.fooddeliveryadmin.models.Admin;
import com.example.fooddeliveryadmin.service.IAdminService;

@RestController
@RequestMapping("/adminf")
public class AdminControllerFeign {

	private IAdminService adminService;

	private IFoodDeliveryClient foodDeliveryClient;

	@Autowired
	private AdminControllerFeign(IAdminService adminService, IFoodDeliveryClient foodDeliveryClient) {
		this.adminService = adminService;
		this.foodDeliveryClient = foodDeliveryClient;
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

		return foodDeliveryClient.createFood(foodDto);
	}

	@GetMapping("/food/view/{id}")
	public ResponseEntity<FoodDto> viewFood(@PathVariable Integer id) {

		return foodDeliveryClient.viewFood(id);
	}

	@GetMapping("/food/viewall")
	public ResponseEntity<List<FoodDto>> viewAllFood() {

		return foodDeliveryClient.viewAllFood();
	}

	@PutMapping("/food/update/{id}")
	public ResponseEntity<FoodDto> updateFood(@PathVariable Integer id, @RequestBody FoodDto foodDto) {

		return foodDeliveryClient.updateFood(id, foodDto);
	}

	@DeleteMapping("/food/delete/{id}")
	public ResponseEntity<FoodDto> deleteFood(@PathVariable Integer id) {

		return foodDeliveryClient.deleteFood(id);
	}

	@PostMapping("/hotel/create")
	public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto) {

		return foodDeliveryClient.createHotel(hotelDto);
	}

	@GetMapping("/hotel/view/{id}")
	public ResponseEntity<HotelDto> viewHotel(@PathVariable Integer id) {

		return foodDeliveryClient.viewHotel(id);
	}

	@GetMapping("/hotel/viewall")
	public ResponseEntity<List<HotelDto>> viewAllHotel() {

		return foodDeliveryClient.viewAllHotel();
	}

	@PutMapping("/hotel/update/{id}")
	public ResponseEntity<HotelDto> updateHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {

		return foodDeliveryClient.updateHotel(id, hotelDto);
	}

	@DeleteMapping("/hotel/delete/{id}")
	public ResponseEntity<HotelDto> deleteHotel(@PathVariable Integer id) {

		return foodDeliveryClient.deleteHotel(id);
	}

	@PostMapping("/hotel/addfood/{hotelId}/{foodId}")
	public ResponseEntity<HotelDto> addFood(@PathVariable Integer hotelId, @PathVariable Integer foodId) {

		return foodDeliveryClient.addFood(hotelId, foodId);
	}

	@DeleteMapping("/hotel/removefood/{hotelId}/{foodId}")
	public ResponseEntity<HotelDto> removeFood(@PathVariable Integer hotelId, @PathVariable Integer foodId) {

		return foodDeliveryClient.removeFood(hotelId, foodId);
	}

	@GetMapping("/hotel/viewall/{cuisine}")
	public ResponseEntity<List<HotelDto>> findAllHotelsByCuisine(@PathVariable String cuisine) {

		return foodDeliveryClient.findAllHotelsByCuisine(cuisine);
	}

	@GetMapping("/takeout/viewall")
	public ResponseEntity<List<TakeoutDto>> viewAllOrders() {

		return foodDeliveryClient.viewAllOrders();
	}

	@GetMapping("/takeout/viewall/{customerId}")
	public ResponseEntity<List<TakeoutDto>> viewAllOrdersByCustomer(@PathVariable Integer customerId) {

		return foodDeliveryClient.viewAllOrdersByCustomer(customerId);
	}

}
