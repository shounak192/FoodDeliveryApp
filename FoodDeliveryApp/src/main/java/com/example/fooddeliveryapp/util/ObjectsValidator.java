package com.example.fooddeliveryapp.util;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

/*
 * Objects validator class is used to validate each field in Entity. Call the validate() method of this class inside
 * each service, pass the object to validate & it'll return Set of Validation violations that the Entity has,
 * or an Empty Set if Entity has no violations.
 */
@Component
public class ObjectsValidator<T> {

	private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = validatorFactory.getValidator();

	public Set<ConstraintViolation<T>> validate(T objectToValidate) {

		Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
		if (!violations.isEmpty()) {
			return violations;
		}

		return Collections.emptySet();
	}

}
