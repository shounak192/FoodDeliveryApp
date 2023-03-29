package com.example.fooddeliveryapp.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fooddeliveryapp.dto.TakeoutDto;
import com.example.fooddeliveryapp.exceptions.TakeoutNotFoundException;
import com.example.fooddeliveryapp.models.Takeout;
import com.example.fooddeliveryapp.repository.ITakeoutRepository;
import com.example.fooddeliveryapp.service.ITakeoutService;
import com.example.fooddeliveryapp.util.convertor.TakeoutConvertor;

@Service
public class TakeoutServiceImpl implements ITakeoutService {

	private ITakeoutRepository takeoutRepository;

	private TakeoutConvertor takeoutConvertor;

	@Autowired
	private TakeoutServiceImpl(ITakeoutRepository takeoutRepository, TakeoutConvertor takeoutConvertor) {
		super();
		this.takeoutRepository = takeoutRepository;
		this.takeoutConvertor = takeoutConvertor;
	}

	@Override
	public Takeout placeOrder(TakeoutDto takeoutDto) {

		Takeout takeout = takeoutConvertor.convert(takeoutDto);
		return takeoutRepository.save(takeout);
	}

	@Override
	public Takeout cancelOrder(Integer id) {

		Takeout takeout = takeoutRepository.findById(id)
				.orElseThrow(() -> new TakeoutNotFoundException("No takeout found"));

		takeoutRepository.deleteById(id);
		return takeout;

	}

	@Override
	public Takeout viewOrder(Integer id) {

		return takeoutRepository.findById(id).orElseThrow(() -> new TakeoutNotFoundException("No takeout found"));
	}

	@Override
	public List<Takeout> viewAllOrders() {

		return takeoutRepository.findAll();
	}

	@Override
	public List<Takeout> viewAllOrdersByCustomer(Integer customerId) {

		return takeoutRepository.getAllByCustomerId(customerId);
	}

}
