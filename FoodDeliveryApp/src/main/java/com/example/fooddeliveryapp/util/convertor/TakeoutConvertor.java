package com.example.fooddeliveryapp.util.convertor;

import org.springframework.stereotype.Component;

import com.example.fooddeliveryapp.dto.TakeoutDto;
import com.example.fooddeliveryapp.models.Takeout;
import com.example.fooddeliveryapp.util.ModelMapperGenerator;

@Component
public class TakeoutConvertor {

	public Takeout convert(TakeoutDto takeoutDto) {
		return ModelMapperGenerator.getModelMapper().map(takeoutDto, Takeout.class);
	}

}
