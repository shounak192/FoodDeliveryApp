package com.example.fooddeliveryadmin.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fooddeliveryadmin.dto.AdminDto;
import com.example.fooddeliveryadmin.models.Admin;

@Service
public interface IAdminService {
	
	public Admin register(AdminDto adminDto);
	
	public Admin login(AdminDto adminDto);
	
	public Optional<Admin> findAdminByUsername(String username);

}
