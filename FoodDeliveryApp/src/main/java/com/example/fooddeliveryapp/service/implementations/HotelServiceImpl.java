package com.example.fooddeliveryapp.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fooddeliveryapp.dto.HotelDto;
import com.example.fooddeliveryapp.exceptions.FoodNotFoundException;
import com.example.fooddeliveryapp.exceptions.HotelNotFoundException;
import com.example.fooddeliveryapp.models.Food;
import com.example.fooddeliveryapp.models.Hotel;
import com.example.fooddeliveryapp.repository.IFoodRepository;
import com.example.fooddeliveryapp.repository.IHotelRepository;
import com.example.fooddeliveryapp.service.IHotelService;
import com.example.fooddeliveryapp.util.ModelMapperGenerator;
import com.example.fooddeliveryapp.util.convertor.FoodConvertor;
import com.example.fooddeliveryapp.util.convertor.HotelConvertor;

@Service
public class HotelServiceImpl implements IHotelService {

	private IHotelRepository hotelRepository;

	private IFoodRepository foodRepository;

	private HotelConvertor hotelConvertor;

	@Autowired
	private HotelServiceImpl(IHotelRepository hotelRepository, IFoodRepository foodRepository,
			HotelConvertor hotelConvertor) {
		super();
		this.hotelRepository = hotelRepository;
		this.foodRepository = foodRepository;
		this.hotelConvertor = hotelConvertor;
	}

	@Autowired
	public void setFoodRepository(IFoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}

	@Override
	public Hotel createHotel(HotelDto hotelDto) {

		Hotel hotel = hotelConvertor.convert(hotelDto);
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel viewHotel(Integer id) {

		Optional<Hotel> optionalHotel = hotelRepository.findById(id);
		if (optionalHotel.isEmpty())
			throw new HotelNotFoundException("Hotel not found.");

		return hotelRepository.findById(id).get();
	}

	@Override
	public List<Hotel> viewAllHotels() {

		return hotelRepository.findAll();
	}

	@Override
	public Hotel updateHotel(Integer id, HotelDto hotelDto) {

		Optional<Hotel> optionalHotel = hotelRepository.findById(id);
		if (optionalHotel.isEmpty())
			throw new HotelNotFoundException("Hotel not found.");

		Hotel hotel = hotelRepository.findById(id).get().setName(hotelDto.getName())
				.setCuisineType(hotelDto.getCuisineType());

		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel deleteHotel(Integer id) {

		Optional<Hotel> optionalHotel = hotelRepository.findById(id);
		if (optionalHotel.isEmpty())
			throw new HotelNotFoundException("Hotel not found.");

		hotelRepository.deleteById(id);
		return optionalHotel.get();
	}

	@Override
	public Hotel addFood(Integer hotelId, Integer foodId) {

		Hotel hotel = hotelRepository.findById(hotelId)
				.orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

		Food food = foodRepository.findById(foodId).orElseThrow(() -> new FoodNotFoundException("Food not found"));

		food.getHotelList().add(hotel);
		hotel.getFoodList().add(food);

		foodRepository.save(food);
		hotelRepository.save(hotel);

		return hotel;

	}

	@Override
	public Hotel removeFood(Integer hotelId, Integer foodId) {

		Hotel hotel = hotelRepository.findById(hotelId)
				.orElseThrow(() -> new HotelNotFoundException("Hotel not found"));
		Food food = foodRepository.findById(foodId).orElseThrow(() -> new FoodNotFoundException("Food not found"));

		hotel.getFoodList().remove(food);
		hotelRepository.save(hotel);

		return hotel;
	}

	@Override
	public List<Hotel> findAllHotelsByCuisineType(String cuisineType) {

		return hotelRepository.findAllByCuisineType(cuisineType);
	}

}
