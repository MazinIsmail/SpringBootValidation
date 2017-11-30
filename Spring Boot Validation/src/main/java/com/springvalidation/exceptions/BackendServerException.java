package com.springvalidation.exceptions;

public class BackendServerException extends RuntimeException {

	private static final long serialVersionUID = 2740469833611912050L;

	public BackendServerException(String message) {
		super(message);
	}
}
