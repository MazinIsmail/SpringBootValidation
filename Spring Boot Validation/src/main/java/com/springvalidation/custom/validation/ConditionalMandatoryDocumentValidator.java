package com.springvalidation.custom.validation;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.springvalidation.domain.Document;
import com.springvalidation.domain.ValidationMessages;

public class ConditionalMandatoryDocumentValidator
		implements ConstraintValidator<ConditionalMandatoryDocumentConstraint, Document> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidationValidator.class);

	public void initialize(final ConditionalMandatoryDocumentConstraint constraintAnnotation) {
		// Nothing to initialize
	}

	public boolean isValid(final Document document, ConstraintValidatorContext context) {
		boolean flag = true;
		Class<Document> aClass = Document.class;
		try {
			if (!validateTransactionAmount(document, context, aClass) | !validateCurrency(document, context, aClass)) {
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

	private boolean validateTransactionAmount(final Document document, ConstraintValidatorContext context,
			Class<Document> aClass) throws NoSuchFieldException {
		boolean flag = true;
		String fieldValueDocumentType = document.getDocumentType();
		String fieldValueTransactionAmount = document.getTransactionAmount();
		Field fieldTransactionAmount = aClass.getDeclaredField("transactionAmount");
		if (fieldValueDocumentType != null && fieldValueDocumentType.equalsIgnoreCase("RCT")
				&& StringUtils.isEmpty(fieldValueTransactionAmount)) {
			flag = false;
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ValidationMessages.EMPTY_MESSAGE)
					.addNode(fieldTransactionAmount.getName()).addConstraintViolation();
		}
		return flag;
	}

	private boolean validateCurrency(final Document document, ConstraintValidatorContext context,
			Class<Document> aClass) throws NoSuchFieldException {
		boolean flag = true;
		String fieldValueDocumentType = document.getDocumentType();
		String fieldValueCurrency = document.getCurrency();
		Field fieldCurrency = aClass.getDeclaredField("currency");
		if (fieldValueDocumentType != null && fieldValueDocumentType.equalsIgnoreCase("RCT")
				&& StringUtils.isEmpty(fieldValueCurrency)) {
			flag = false;
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ValidationMessages.EMPTY_MESSAGE)
					.addNode(fieldCurrency.getName()).addConstraintViolation();
		}
		return flag;
	}

}
