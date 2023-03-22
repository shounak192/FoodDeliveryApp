package com.example.fooddeliveryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fooddeliveryapp.models.Takeout;

@Repository
public interface ITakeoutRepository extends JpaRepository<Takeout, Integer>{
	
	public List<Takeout> getAllByCustomerId(Integer customerId);

}
