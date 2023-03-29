package com.example.fooddeliveryapp.util;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

@Component
public class ObjectsValidator<T> {

	private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = validatorFactory.getValidator();

	public Set<ConstraintViolation<T>> validate(T objectToValidate) {

		Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
		if(!violations.isEmpty()) {
			return violations;
		}
		
		return Collections.emptySet();
	}

}
