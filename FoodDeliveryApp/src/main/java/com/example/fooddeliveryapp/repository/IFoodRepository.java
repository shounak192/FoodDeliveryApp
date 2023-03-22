package com.example.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fooddeliveryapp.models.Food;

@Repository
public interface IFoodRepository extends JpaRepository<Food, Integer>{

}
