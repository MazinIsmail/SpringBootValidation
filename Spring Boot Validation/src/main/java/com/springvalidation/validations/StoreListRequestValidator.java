package com.springvalidation.validations;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springvalidation.domain.InnerStoreRequest;
import com.springvalidation.domain.StoreListRequest;

@Component
public class StoreListRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return StoreListRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StoreListRequest storeListRequest = (StoreListRequest) target;
		for (int i = 0; i < storeListRequest.getInnerStoreRequestList().size(); i++) {
			InnerStoreRequest innerStoreRequest = storeListRequest.getInnerStoreRequestList().get(i);
			if (StringUtils.isEmpty(innerStoreRequest.getCheckInner())) {
				errors.rejectValue("innerStoreRequest[" + i + "].checkInner", "Should not be empty.");
			}
		}
	}
}
