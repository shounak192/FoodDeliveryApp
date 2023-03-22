package com.example.fooddeliveryapp.util.convertor;

import org.springframework.stereotype.Component;

import com.example.fooddeliveryapp.dto.HotelDto;
import com.example.fooddeliveryapp.models.Hotel;
import com.example.fooddeliveryapp.util.ModelMapperGenerator;

@Component
public class HotelConvertor {
	
	public Hotel convert(HotelDto hotelDto) {
		return ModelMapperGenerator.getModelMapper().map(hotelDto, Hotel.class);
	}

}
