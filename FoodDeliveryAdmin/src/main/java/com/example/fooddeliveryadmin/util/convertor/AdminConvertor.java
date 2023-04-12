package com.example.fooddeliveryadmin.util.convertor;

import org.springframework.stereotype.Component;

import com.example.fooddeliveryadmin.dto.AdminDto;
import com.example.fooddeliveryadmin.models.Admin;
import com.example.fooddeliveryadmin.util.ModelMapperGenerator;

@Component
public class AdminConvertor {
	
	public Admin convert(AdminDto adminDto) {
		return ModelMapperGenerator.getMapper().map(adminDto, Admin.class);
	}

}
