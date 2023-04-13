package com.example.fooddeliveryadmin.feignclient;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.fooddeliveryadmin.dto.FoodDto;
import com.example.fooddeliveryadmin.dto.HotelDto;
import com.example.fooddeliveryadmin.dto.TakeoutDto;
import com.example.fooddeliveryadmin.service.implementations.FoodDeliveryServiceImpl;

//@FeignClient(name = "fooddelivery-app", url = "http://localhost:8080", fallback = FoodDeliveryServiceImpl.class)

/*
 * "url" paramter can be ignored as Food Delivery App instance will be loaded from Eureka Server Discovery,
 * instead directly from its hardcoded url.
 */
@FeignClient(name = "fooddelivery-app", fallback = FoodDeliveryServiceImpl.class)
@LoadBalancerClient(name="fooddelivery-app", configuration = FoodDeliveryServiceImpl.class)
public interface IFoodDeliveryClient {

	@PostMapping("/food/create")
	public ResponseEntity<FoodDto> createFood(@RequestBody FoodDto foodDto);

	@GetMapping("/food/view/{id}")
	public ResponseEntity<FoodDto> viewFood(@PathVariable Integer id);

	@GetMapping("/food/viewall")
	public ResponseEntity<List<FoodDto>> viewAllFood();

	@PutMapping("/food/update/{id}")
	public ResponseEntity<FoodDto> updateFood(@PathVariable Integer id, @RequestBody FoodDto foodDto);

	@DeleteMapping("/food/delete/{id}")
	public ResponseEntity<FoodDto> deleteFood(@PathVariable Integer id);

	@PostMapping("/hotel/create")
	public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto);

	@GetMapping("/hotel/view/{id}")
	public ResponseEntity<HotelDto> viewHotel(@PathVariable Integer id);

	@GetMapping("/hotel/viewall")
	public ResponseEntity<List<HotelDto>> viewAllHotel();

	@PutMapping("/hotel/update/{id}")
	public ResponseEntity<HotelDto> updateHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto);

	@DeleteMapping("/hotel/delete/{id}")
	public ResponseEntity<HotelDto> deleteHotel(@PathVariable Integer id);

	@PostMapping("/hotel/addfood/{hotelId}/{foodId}")
	public ResponseEntity<HotelDto> addFood(@PathVariable Integer hotelId, @PathVariable Integer foodId);

	@DeleteMapping("/hotel/removefood/{hotelId}/{foodId}")
	public ResponseEntity<HotelDto> removeFood(@PathVariable Integer hotelId, @PathVariable Integer foodId);

	@GetMapping("/hotel/viewall/{cuisine}")
	public ResponseEntity<List<HotelDto>> findAllHotelsByCuisine(@PathVariable String cuisine);

	@GetMapping("/takeout/viewall")
	public ResponseEntity<List<TakeoutDto>> viewAllOrders();

	@GetMapping("/takeout/viewall/{customerId}")
	public ResponseEntity<List<TakeoutDto>> viewAllOrdersByCustomer(@PathVariable Integer customerId);

}
