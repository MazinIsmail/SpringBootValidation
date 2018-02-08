package com.springvalidation.custom.validation;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springvalidation.domain.ARequest;
import com.springvalidation.domain.ValidationMessages;

public class RequestValidationValidator implements ConstraintValidator<RequestValidationConstraint, ARequest> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidationValidator.class);

	public void initialize(final RequestValidationConstraint constraintAnnotation) {
		// Nothing to initialize
	}

	public boolean isValid(final ARequest aWDeClaimRequest, ConstraintValidatorContext context) {
		boolean flag = true;
		Class<ARequest> aClass = ARequest.class;
		try {
			if (!validateTransactionDateTime(aWDeClaimRequest, context, aClass)
					| !validateDateOfBirth(aWDeClaimRequest, context, aClass)) {
				flag = false;
			}
		} catch (SecurityException e) {
			LOGGER.error(e.toString());
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.toString());
		} catch (NoSuchFieldException e) {
			LOGGER.error(e.toString());
		}
		return flag;
	}

	private boolean validateDateOfBirth(final ARequest aWDeClaimRequest, ConstraintValidatorContext context,
			Class<ARequest> aClass) throws NoSuchFieldException {
		boolean flag = true;
		String fieldValue = aWDeClaimRequest.getDateOfBirth();
		Field field = aClass.getDeclaredField("dateOfBirth");
		if (fieldValue != null && !dateTimePatternDateOfBirthValidation(fieldValue)) {
			flag = false;
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ValidationMessages.INVALID_DATA_MESSAGE)
					.addNode(field.getName()).addConstraintViolation();
		}
		return flag;
	}

	private boolean validateTransactionDateTime(final ARequest aWDeClaimRequest, ConstraintValidatorContext context,
			Class<ARequest> aClass) throws NoSuchFieldException {
		boolean flag = true;
		String fieldValue = aWDeClaimRequest.getTransactionDateTime();
		Field field = aClass.getDeclaredField("transactionDateTime");
		if (fieldValue != null && !dateTimePatternValidation(fieldValue)) {
			flag = false;
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ValidationMessages.INVALID_DATA_MESSAGE)
					.addNode(field.getName()).addConstraintViolation();
		}
		return flag;
	}

	private static boolean dateTimePatternValidation(String dataTime) {
		boolean flag = true;
		SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			desiredFormat.setLenient(false);
			desiredFormat.parse(dataTime);
		} catch (ParseException e) {
			flag = false;
		}
		return flag;
	}

	private static boolean dateTimePatternDateOfBirthValidation(String dataTime) {
		boolean flag = true;
		SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			desiredFormat.setLenient(false);
			desiredFormat.parse(dataTime);
		} catch (ParseException e) {
			flag = false;
		}
		return flag;
	}

}
