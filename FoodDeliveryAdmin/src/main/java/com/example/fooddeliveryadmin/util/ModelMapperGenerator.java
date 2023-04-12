package com.example.fooddeliveryadmin.util;

import org.modelmapper.ModelMapper;

public class ModelMapperGenerator {

	static ModelMapper modelMapper;

	private ModelMapperGenerator() {
	}

	public static ModelMapper getMapper() {
		if (modelMapper == null)
			modelMapper = new ModelMapper();
		return modelMapper;
	}

}
