package com.springvalidation.exceptions.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.springvalidation.exceptions.ApiError;
import com.springvalidation.exceptions.ApiErrorResponse;
import com.springvalidation.exceptions.ApiValidationError;
import com.springvalidation.exceptions.BackendServerException;
import com.springvalidation.exceptions.CustomerNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	private static final String APPEND_CAUSE = "\n Cause:\n";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		List<ApiValidationError> apiValidationErrorList = new ArrayList<>();
		List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			ApiValidationError apiValidationError = new ApiValidationError(fieldError.getField(),
					fieldError.getDefaultMessage());
			apiValidationErrorList.add(apiValidationError);
		}
		List<ApiError> apiErrorList = new ArrayList<>();
		for (ApiValidationError apiValidationErrorTemnp : apiValidationErrorList) {
			ApiError apiError = apiValidationErrorTemnp;
			apiErrorList.add(apiError);
		}
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse("400", HttpStatus.BAD_REQUEST.toString(),
				apiErrorList);
		return buildResponseEntity(HttpStatus.BAD_REQUEST, apiErrorResponse);
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	public ResponseEntity<ApiErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		if (ex.getCause() != null && (ex.getCause() instanceof UnrecognizedPropertyException)) {
			String exceptionStackTrace = ex.getCause().toString();
			LOG.error(exceptionStackTrace);
			ApiErrorResponse response = new ApiErrorResponse("400", "Bad Request", ex.getCause().getLocalizedMessage());
			return buildResponseEntity(HttpStatus.BAD_REQUEST, response);
		} else {
			String exceptionStackTrace = ex.toString();
			LOG.error(exceptionStackTrace);
			ApiErrorResponse response = new ApiErrorResponse("400", "Bad Request", ex.getLocalizedMessage());
			return buildResponseEntity(HttpStatus.BAD_REQUEST, response);
		}
	}

	@ExceptionHandler(value = { CustomerNotFoundException.class })
	public ResponseEntity<ApiErrorResponse> customerNotFoundException(RuntimeException ex) {
		String exceptionMessage = ex.getMessage();
		LOG.warn(exceptionMessage);
		ApiErrorResponse response = new ApiErrorResponse("404", exceptionMessage);
		return buildResponseEntity(HttpStatus.NOT_FOUND, response);
	}

	@ExceptionHandler(value = { NoHandlerFoundException.class })
	public ResponseEntity<ApiErrorResponse> noHandlerFoundException(NoHandlerFoundException ex) {
		String exceptionStackTrace = ex.toString();
		LOG.error(exceptionStackTrace);
		ApiErrorResponse response = new ApiErrorResponse("404", "Requested Resource Not Found",
				ex.getLocalizedMessage());
		return buildResponseEntity(HttpStatus.NOT_FOUND, response);
	}

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<ApiErrorResponse> httpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException ex) {
		String exceptionStackTrace = ex.toString();
		LOG.error(exceptionStackTrace);
		ApiErrorResponse response = new ApiErrorResponse("405", "Requested Method Not Supported",
				ex.getLocalizedMessage());
		return buildResponseEntity(HttpStatus.METHOD_NOT_ALLOWED, response);
	}

	@ExceptionHandler(value = { HttpMediaTypeNotSupportedException.class })
	public ResponseEntity<ApiErrorResponse> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		String exceptionStackTrace = ex.toString();
		LOG.error(exceptionStackTrace);
		ApiErrorResponse response = new ApiErrorResponse("415", "Unsupported Media Type", ex.getLocalizedMessage());
		return buildResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response);
	}

	@ExceptionHandler(value = { BackendServerException.class })
	public ResponseEntity<ApiErrorResponse> sqlException(BackendServerException ex) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(ex.toString());
		if (ex.getCause() != null) {
			strBuilder.append(APPEND_CAUSE);
			strBuilder.append(ex.getCause().toString());
		}
		String exceptionStackTrace = strBuilder.toString();
		LOG.error(exceptionStackTrace);
		ApiErrorResponse response = new ApiErrorResponse("500", "Backend System Error",
				"Error while connecting to Backend System");
		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, response);
	}

	@ExceptionHandler(value = { SQLException.class })
	public ResponseEntity<ApiErrorResponse> sqlException(SQLException ex) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(ex.toString());
		if (ex.getCause() != null) {
			strBuilder.append(APPEND_CAUSE);
			strBuilder.append(ex.getCause().toString());
		}
		String exceptionStackTrace = strBuilder.toString();
		LOG.error(exceptionStackTrace);
		ApiErrorResponse response = new ApiErrorResponse("500", "Database Error", ex.getLocalizedMessage());
		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, response);
	}
	
	@ExceptionHandler(value = { ServiceUnavailableException.class })
	public ResponseEntity<ApiErrorResponse> serviceUnavailableException(Exception ex) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(ex.toString());
		if (ex.getCause() != null) {
			strBuilder.append(APPEND_CAUSE);
			strBuilder.append(ex.getCause().toString());
		}
		String exceptionStackTrace = strBuilder.toString();
		LOG.error(exceptionStackTrace);
		ApiErrorResponse response = new ApiErrorResponse("503", "Service Unavailable", ex.getLocalizedMessage());
		return buildResponseEntity(HttpStatus.SERVICE_UNAVAILABLE, response);
	}
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ApiErrorResponse> exception(Exception ex) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(ex.toString());
		if (ex.getCause() != null) {
			strBuilder.append(APPEND_CAUSE);
			strBuilder.append(ex.getCause().toString());
		}
		String exceptionStackTrace = strBuilder.toString();
		LOG.error(exceptionStackTrace);
		ApiErrorResponse response = new ApiErrorResponse("500", "Unknown Server Error", ex.getLocalizedMessage());
		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, response);
	}
	
	

	private ResponseEntity<ApiErrorResponse> buildResponseEntity(HttpStatus httpStatus,
			ApiErrorResponse errorResponse) {
		return new ResponseEntity<>(errorResponse, httpStatus);
	}
}
