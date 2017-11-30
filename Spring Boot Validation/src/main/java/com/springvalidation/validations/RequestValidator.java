package com.springvalidation.validations;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.springvalidation.domain.StoreCrossRequest;

public class RequestValidator implements ConstraintValidator<RequestCrossFieldValidation, StoreCrossRequest> {

	private String firstFieldName;
	private String secondFieldName;
	private String errorMessage;

	@Override
	public void initialize(final RequestCrossFieldValidation constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		errorMessage = constraintAnnotation.errorMessage();
	}

	@Override
	public boolean isValid(StoreCrossRequest storeCrossRequest, ConstraintValidatorContext context) {
		boolean toReturn = true;
		try {
			Object baseFieldValue = getFieldValue(storeCrossRequest, firstFieldName);
			Object matchFieldValue = getFieldValue(storeCrossRequest, secondFieldName);
			if (baseFieldValue.toString().equalsIgnoreCase("Mazin")) {
				if (!matchFieldValue.toString().equalsIgnoreCase("Ismail")) {
					toReturn = false;
				}
			}
		} catch (Exception e) {
			toReturn = false;
		}

		if (!toReturn) {
			context.disableDefaultConstraintViolation();
			// In the initialiaze method you get the errorMessage:
			// constraintAnnotation.message();
			context.buildConstraintViolationWithTemplate(errorMessage).addNode(firstFieldName).addConstraintViolation();
		}

		return toReturn;
	}

	private Object getFieldValue(Object object, String fieldName) throws Exception {
		Class<?> clazz = object.getClass();
		Field passwordField = clazz.getDeclaredField(fieldName);
		passwordField.setAccessible(true);
		return passwordField.get(object);
	}

}
