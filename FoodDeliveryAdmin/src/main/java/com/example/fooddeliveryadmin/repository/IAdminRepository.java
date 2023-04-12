package com.example.fooddeliveryadmin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fooddeliveryadmin.models.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByUsername(String username);
}
