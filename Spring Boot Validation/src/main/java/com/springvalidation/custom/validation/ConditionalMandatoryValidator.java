package com.springvalidation.custom.validation;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.springvalidation.domain.ARequest;
import com.springvalidation.domain.ValidationMessages;

public class ConditionalMandatoryValidator
		implements ConstraintValidator<ConditionalMandatoryConstraint, ARequest> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidationValidator.class);

	public void initialize(final ConditionalMandatoryConstraint constraintAnnotation) {
		// Nothing to initialize
	}

	public boolean isValid(final ARequest aWDeClaimRequest, ConstraintValidatorContext context) {
		boolean flag = true;
		Class<ARequest> aClass = ARequest.class;
		try {
			if (!validateHasSecondClaimInternalSecondClaimGroupNumber(aWDeClaimRequest, context, aClass)
					| !validateSystemCodeWith(aWDeClaimRequest, context, aClass)
					| !validateInternalSecondClaimSubGroupNumber(aWDeClaimRequest, context, aClass)
					| !validateInternalSecondClaimCertificateNumber(aWDeClaimRequest, context, aClass)) {
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

	private boolean validateHasSecondClaimInternalSecondClaimGroupNumber(final ARequest aWDeClaimRequest,
			ConstraintValidatorContext context, Class<ARequest> aClass) throws NoSuchFieldException {
		boolean flag = true;
		String fieldValueHasSecondClaim = aWDeClaimRequest.getHasSecondClaim();
		String fieldValueInternalSecondClaimGroupNumber = aWDeClaimRequest.getInternalSecondClaimGroupNumber();
		Field fieldInternalSecondClaimGroupNumber = aClass.getDeclaredField("internalSecondClaimGroupNumber");
		if (fieldValueHasSecondClaim != null && fieldValueHasSecondClaim.equalsIgnoreCase("Y")
				&& StringUtils.isEmpty(fieldValueInternalSecondClaimGroupNumber)) {
			flag = false;
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ValidationMessages.EMPTY_MESSAGE)
					.addNode(fieldInternalSecondClaimGroupNumber.getName()).addConstraintViolation();
		}
		return flag;
	}

	private boolean validateSystemCodeWith(final ARequest aWDeClaimRequest, ConstraintValidatorContext context,
			Class<ARequest> aClass) throws NoSuchFieldException {
		boolean flag = true;
		String systemCode = aWDeClaimRequest.getSystemCode();
		String clientProfile = aWDeClaimRequest.getClientProfile();
		String staffIndicator = aWDeClaimRequest.getStaffIndicator();
		Field fieldClientProfile = aClass.getDeclaredField("clientProfile");
		Field fieldstaffIndicator = aClass.getDeclaredField("staffIndicator");
		if (systemCode != null && systemCode.equalsIgnoreCase("GLH")) {
			if (StringUtils.isEmpty(clientProfile)) {
				flag = false;
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ValidationMessages.EMPTY_MESSAGE)
						.addNode(fieldClientProfile.getName()).addConstraintViolation();
			}
			if (StringUtils.isEmpty(staffIndicator)) {
				flag = false;
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(ValidationMessages.EMPTY_MESSAGE)
						.addNode(fieldstaffIndicator.getName()).addConstraintViolation();
			}
		}
		return flag;
	}

	private boolean validateInternalSecondClaimSubGroupNumber(final ARequest aWDeClaimRequest,
			ConstraintValidatorContext context, Class<ARequest> aClass) throws NoSuchFieldException {
		boolean flag = true;
		String fieldValueSystemCode = aWDeClaimRequest.getSystemCode();
		String fieldValueHasSecondClaim = aWDeClaimRequest.getHasSecondClaim();
		String fieldValueInternalSecondClaimSubGroupNumber = aWDeClaimRequest.getInternalSecondClaimSubGroupNumber();
		Field fieldInternalSecondClaimSubGroupNumber = aClass.getDeclaredField("internalSecondClaimSubGroupNumber");
		if (fieldValueSystemCode != null && fieldValueSystemCode.equalsIgnoreCase("GLH")
				&& fieldValueHasSecondClaim != null && fieldValueHasSecondClaim.equalsIgnoreCase("Y")
				&& StringUtils.isEmpty(fieldValueInternalSecondClaimSubGroupNumber)) {
			flag = false;
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ValidationMessages.EMPTY_MESSAGE)
					.addNode(fieldInternalSecondClaimSubGroupNumber.getName()).addConstraintViolation();
		}
		return flag;

	}

	private boolean validateInternalSecondClaimCertificateNumber(final ARequest aWDeClaimRequest,
			ConstraintValidatorContext context, Class<ARequest> aClass) throws NoSuchFieldException {
		boolean flag = true;
		String fieldValueSystemCode = aWDeClaimRequest.getSystemCode();
		String fieldValueHasSecondClaim = aWDeClaimRequest.getHasSecondClaim();
		String fieldValueInternalSecondClaimCertificateNumber = aWDeClaimRequest
				.getInternalSecondClaimCertificateNumber();
		Field fieldInternalSecondClaimCertificateNumber = aClass
				.getDeclaredField("internalSecondClaimCertificateNumber");
		if (fieldValueSystemCode != null && fieldValueSystemCode.equalsIgnoreCase("GLH")
				&& fieldValueHasSecondClaim != null && fieldValueHasSecondClaim.equalsIgnoreCase("Y")
				&& StringUtils.isEmpty(fieldValueInternalSecondClaimCertificateNumber)) {
			flag = false;
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(ValidationMessages.EMPTY_MESSAGE)
					.addNode(fieldInternalSecondClaimCertificateNumber.getName()).addConstraintViolation();
		}
		return flag;
	}

}
