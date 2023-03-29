package com.example.fooddeliveryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fooddeliveryapp.models.Hotel;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Integer>{

	List<Hotel> findAllByCuisine(String cuisine);

	

}
