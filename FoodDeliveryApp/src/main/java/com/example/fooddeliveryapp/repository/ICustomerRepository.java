package com.example.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fooddeliveryapp.models.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}
