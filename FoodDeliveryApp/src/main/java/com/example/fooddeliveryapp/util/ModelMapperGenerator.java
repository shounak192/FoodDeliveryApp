package com.example.fooddeliveryapp.util;

import org.modelmapper.ModelMapper;

public class ModelMapperGenerator {
	
	public static ModelMapper modelMapper; 
	
	private ModelMapperGenerator() {
	}
	
	public static ModelMapper getModelMapper() {
		if(modelMapper==null)
			modelMapper= new ModelMapper();
		return modelMapper;
	}
	
	
	
}
