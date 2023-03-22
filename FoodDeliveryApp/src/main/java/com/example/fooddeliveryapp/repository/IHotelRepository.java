package com.example.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fooddeliveryapp.models.Hotel;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Integer>{

}
