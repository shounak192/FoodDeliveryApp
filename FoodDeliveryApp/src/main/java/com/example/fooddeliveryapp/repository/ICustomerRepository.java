package com.example.fooddeliveryapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fooddeliveryapp.models.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByUsername(String username);

}
