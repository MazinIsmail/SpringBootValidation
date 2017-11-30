package com.springvalidation.exceptions;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5875652134408858446L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
