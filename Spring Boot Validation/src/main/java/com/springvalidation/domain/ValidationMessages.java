package com.springvalidation.domain;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationMessages {

	public static final String EMPTY_MESSAGE = "is missing";
	public static final String EMPTY_CODE = "10";
	public static final String INVALID_DATA_MESSAGE = "data is invalid";
	public static final String INVALID_DATA_CODE = "20";

}
