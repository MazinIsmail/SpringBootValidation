package com.springvalidation.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {

	@Override
	public void initialize(ContactNumberConstraint contactNumberConstraint) {
		// Overrided method.
	}

	@Override
	public boolean isValid(String contactNumber, ConstraintValidatorContext cxt) {
		return contactNumber != null && contactNumber.matches("[0-9]+") && contactNumber.length() == 10;
	}

}