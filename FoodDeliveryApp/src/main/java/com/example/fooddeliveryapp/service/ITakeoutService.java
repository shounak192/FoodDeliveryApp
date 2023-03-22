package com.example.fooddeliveryapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fooddeliveryapp.dto.TakeoutDto;
import com.example.fooddeliveryapp.models.Takeout;

@Service
public interface ITakeoutService {

	public Takeout placeOrder(TakeoutDto takeoutDto);

	public Takeout cancelOrder(Integer id);

	public Takeout viewOrder(Integer id);

	public List<Takeout> viewAllOrders();

	public List<Takeout> viewAllOrdersByCustomer(Integer customerId);

}
