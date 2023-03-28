package com.example.fooddeliveryapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fooddeliveryapp.dto.HotelDto;
import com.example.fooddeliveryapp.models.Hotel;

@Service
public interface IHotelService {
	
	public Hotel createHotel(HotelDto hotelDto);
	
	public Hotel viewHotel(Integer id);
	
	public List<Hotel> viewAllHotels();
	
	public Hotel updateHotel(Integer id, HotelDto hotelDto);
	
	public Hotel deleteHotel(Integer id);
	
	public Hotel addFood(Integer hotelId, Integer foodId);
	
	public Hotel removeFood(Integer hotelId, Integer foodId);

	public List<Hotel> findAllHotelsByCuisineType(String cuisineType);
	

}
