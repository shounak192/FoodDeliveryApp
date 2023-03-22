package com.example.fooddeliveryapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.fooddeliveryapp.dto.FoodDto;
import com.example.fooddeliveryapp.dto.HotelDto;
import com.example.fooddeliveryapp.exceptions.HotelNotFoundException;
import com.example.fooddeliveryapp.models.Food;
import com.example.fooddeliveryapp.models.Hotel;
import com.example.fooddeliveryapp.repository.IFoodRepository;
import com.example.fooddeliveryapp.repository.IHotelRepository;
import com.example.fooddeliveryapp.service.implementations.HotelServiceImpl;
import com.example.fooddeliveryapp.util.convertor.HotelConvertor;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class HotelServiceTest {

	@InjectMocks
	private HotelServiceImpl hotelService;

	@Mock
	private IFoodRepository foodRepository;

	@Mock
	private IHotelRepository hotelRepository;

	@Mock
	private HotelConvertor hotelConvertor;

	private HotelDto hotelDto;

	private Hotel hotel;

	private FoodDto foodDto;

	private Food food;

	private List<Hotel> hotelList = new ArrayList<>();

	@BeforeEach
	void setup() {
		hotelDto = new HotelDto("name", "cuisine", new ArrayList<>());
		hotelDto.setId(1);
		hotel = new Hotel("name", "cuisine", new ArrayList<>());
		hotel.setId(1);
		hotelList.add(hotel);

		foodDto = new FoodDto("name", new ArrayList<>());
		foodDto.setId(1);
		food = new Food("name", new ArrayList<>());
		food.setId(1);
		when(hotelConvertor.convert(hotelDto)).thenReturn(hotel);
	}

	@Test
	void createHotelTest() {

		when(hotelRepository.save(hotel)).thenReturn(hotel);
		assertEquals(hotel, hotelService.createHotel(hotelDto));
	}

	@Test
	void viewHotelTest() {

		when(hotelRepository.findById(1)).thenReturn(Optional.ofNullable(hotel));
		assertEquals(hotel, hotelService.viewHotel(1));
	}

	@Test
	void InvalidViewHotelTest() {

		when(hotelRepository.findById(2)).thenReturn(Optional.ofNullable(null));
		assertThrows(HotelNotFoundException.class, () -> hotelService.viewHotel(2));
	}

	@Test
	void viewAllHotelsTest() {

		when(hotelRepository.findAll()).thenReturn(hotelList);
		assertEquals(hotelList, hotelService.viewAllHotels());
	}

	@Test
	void updateHotelTest() {

		when(hotelRepository.findById(1)).thenReturn(Optional.ofNullable(hotel));
		when(hotelRepository.save(hotel)).thenReturn(hotel);
		assertEquals(hotel, hotelService.updateHotel(1, hotelDto));
	}

	@Test
	void deleteHotelTest() {

		when(hotelRepository.findById(1)).thenReturn(Optional.ofNullable(hotel));
		assertEquals(hotel, hotelService.deleteHotel(1));
	}

	@Test
	void InvalidDeleteHotelTest() {

		when(hotelRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		assertThrows(HotelNotFoundException.class, () -> hotelService.deleteHotel(1));
	}

	@Test
	void addFoodTest() {

		when(hotelRepository.findById(1)).thenReturn(Optional.ofNullable(hotel));
		when(foodRepository.findById(1)).thenReturn(Optional.ofNullable(food));
		assertEquals(hotel, hotelService.addFood(1, 1));
	}

	@Test
	void removeFoodTest() {

		when(hotelRepository.findById(1)).thenReturn(Optional.ofNullable(hotel));
		when(foodRepository.findById(1)).thenReturn(Optional.ofNullable(food));
		assertEquals(hotel, hotelService.removeFood(1, 1));
	}

}
