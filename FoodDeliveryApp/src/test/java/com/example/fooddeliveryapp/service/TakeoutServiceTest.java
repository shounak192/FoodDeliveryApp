package com.example.fooddeliveryapp.service;

import static org.junit.jupiter.api.Assertions.*;
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

import com.example.fooddeliveryapp.dto.TakeoutDto;
import com.example.fooddeliveryapp.exceptions.HotelNotFoundException;
import com.example.fooddeliveryapp.exceptions.TakeoutNotFoundException;
import com.example.fooddeliveryapp.models.Takeout;
import com.example.fooddeliveryapp.repository.ITakeoutRepository;
import com.example.fooddeliveryapp.service.implementations.TakeoutServiceImpl;
import com.example.fooddeliveryapp.util.convertor.TakeoutConvertor;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TakeoutServiceTest {
	
	@InjectMocks
	private TakeoutServiceImpl takeoutService;
	
	@Mock
	private ITakeoutRepository takeoutRepository;
	
	@Mock
	private TakeoutConvertor takeConvertor;
	
	private TakeoutDto takeoutDto;
	
	private Takeout takeout;
	
	private List<Takeout> takeoutList= new ArrayList<>();
	
	@BeforeEach
	public void setup() {
		takeoutDto = new TakeoutDto(1, 1, 1);
		takeoutDto.setId(1);
		takeout = new Takeout(1, 1, 1);
		takeout.setId(1);
		takeoutList.add(takeout);
		when(takeConvertor.convert(takeoutDto)).thenReturn(takeout);
	}

	@Test
	void placeOrderTest() {
		
		when(takeoutRepository.save(takeout)).thenReturn(takeout);
		assertEquals(takeout, takeoutService.placeOrder(takeoutDto));
	}
	
	@Test
	void cancelOrderTest() {
		
		when(takeoutRepository.findById(1)).thenReturn(Optional.ofNullable(takeout));
		assertEquals(takeout, takeoutService.cancelOrder(1));
	}
	
	@Test
	void InvalidCancelOrderTest() {
		
		when(takeoutRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		assertThrows(TakeoutNotFoundException.class,()->  takeoutService.cancelOrder(1));
	}
	
	@Test
	void viewOrderTest() {
		
		when(takeoutRepository.findById(1)).thenReturn(Optional.ofNullable(takeout));
		assertEquals(takeout, takeoutService.viewOrder(1));
	}
	
	@Test
	void InvalidViewOrderTest() {
		
		when(takeoutRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		assertThrows(TakeoutNotFoundException.class,()->  takeoutService.viewOrder(1));
	}
	
	@Test
	void viewAllOrdersTest() {
		
		when(takeoutRepository.findAll()).thenReturn(takeoutList);
		assertEquals(takeoutList, takeoutService.viewAllOrders());
	}
	
	@Test
	void viewAllOrdersByCustomerTest() {
		
		when(takeoutRepository.getAllByCustomerId(1)).thenReturn(takeoutList);
		assertEquals(takeoutList, takeoutService.viewAllOrdersByCustomer(1));
	}

}
