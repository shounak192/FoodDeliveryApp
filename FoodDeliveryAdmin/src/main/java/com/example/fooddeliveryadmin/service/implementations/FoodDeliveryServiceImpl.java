package com.example.fooddeliveryadmin.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.fooddeliveryadmin.dto.FoodDto;
import com.example.fooddeliveryadmin.dto.HotelDto;
import com.example.fooddeliveryadmin.dto.TakeoutDto;
import com.example.fooddeliveryadmin.feignclient.IFoodDeliveryClient;

@Service
public class FoodDeliveryServiceImpl implements IFoodDeliveryClient {

	@Override
	public ResponseEntity<FoodDto> createFood(FoodDto foodDto) {

		return new ResponseEntity<>(new FoodDto("name", new ArrayList<>()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FoodDto> viewFood(Integer id) {

		return new ResponseEntity<>(new FoodDto("name", new ArrayList<>()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<FoodDto>> viewAllFood() {

		List<FoodDto> foodList = new ArrayList<>();
		foodList.add(new FoodDto("name", new ArrayList<>()));
		return new ResponseEntity<>(foodList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FoodDto> updateFood(Integer id, FoodDto foodDto) {
		return new ResponseEntity<>(new FoodDto("name", new ArrayList<>()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FoodDto> deleteFood(Integer id) {
		return new ResponseEntity<>(new FoodDto("name", new ArrayList<>()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<HotelDto> createHotel(HotelDto hotelDto) {

		return new ResponseEntity<>(new HotelDto("name", "cuisine", new ArrayList<>()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<HotelDto> viewHotel(Integer id) {

		return new ResponseEntity<>(new HotelDto("name", "cuisine", new ArrayList<>()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<HotelDto>> viewAllHotel() {

		List<HotelDto> hotelList = new ArrayList<>();
		hotelList.add(new HotelDto("name", "cuisine", new ArrayList<>()));
		return new ResponseEntity<>(hotelList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<HotelDto> updateHotel(Integer id, HotelDto hotelDto) {

		return new ResponseEntity<>(new HotelDto("name", "cuisine", new ArrayList<>()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<HotelDto> deleteHotel(Integer id) {

		return new ResponseEntity<>(new HotelDto("name", "cuisine", new ArrayList<>()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<HotelDto> addFood(Integer hotelId, Integer foodId) {

		return new ResponseEntity<>(new HotelDto("name", "cuisine", new ArrayList<>()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<HotelDto> removeFood(Integer hotelId, Integer foodId) {

		return new ResponseEntity<>(new HotelDto("name", "cuisine", new ArrayList<>()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<HotelDto>> findAllHotelsByCuisine(String cuisine) {

		List<HotelDto> hotelList = new ArrayList<>();
		hotelList.add(new HotelDto("name", "cuisine", new ArrayList<>()));
		return new ResponseEntity<>(hotelList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TakeoutDto>> viewAllOrders() {

		List<TakeoutDto> takeoutList = new ArrayList<>();
		takeoutList.add(new TakeoutDto(1, 1, 1));
		return new ResponseEntity<>(takeoutList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TakeoutDto>> viewAllOrdersByCustomer(Integer customerId) {

		List<TakeoutDto> takeoutList = new ArrayList<>();
		takeoutList.add(new TakeoutDto(1, 1, 1));
		return new ResponseEntity<>(takeoutList, HttpStatus.OK);
	}

}
