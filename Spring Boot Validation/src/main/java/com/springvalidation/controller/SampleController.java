package com.springvalidation.controller;

import java.sql.SQLException;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.springvalidation.domain.StoreCrossRequest;
import com.springvalidation.domain.StoreListRequest;
import com.springvalidation.domain.StoreRequest;
import com.springvalidation.domain.StoreResponse;
import com.springvalidation.exceptions.BackendServerException;
import com.springvalidation.exceptions.CustomerNotFoundException;

@RestController
public class SampleController {

	@RequestMapping(value = "/exception/{exceptionType}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<StoreResponse> searchByNickNameGet(@PathVariable String exceptionType,
			HttpServletRequest httprequest, HttpServletResponse response)
			throws SQLException, HttpMediaTypeNotSupportedException, NoHandlerFoundException,
			HttpRequestMethodNotSupportedException, ServiceUnavailableException {
		return new ResponseEntity<>(processRequest(exceptionType), HttpStatus.OK);
	}

	@PostMapping(value = "/people")
	public ResponseEntity<StoreResponse> store(@Valid @RequestBody final StoreRequest request) {
		StoreResponse response = new StoreResponse();
		response.setSuccess(true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/peopleList")
	public ResponseEntity<StoreResponse> storeList(@Valid @RequestBody final StoreListRequest storeListRequest) {
		StoreResponse response = new StoreResponse();
		response.setSuccess(true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/crossField")
	public ResponseEntity<StoreResponse> storeList(@Valid @RequestBody final StoreCrossRequest storeCrossRequest) {
		StoreResponse response = new StoreResponse();
		response.setSuccess(true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private StoreResponse processRequest(String exceptionName) throws SQLException, NoHandlerFoundException,
			HttpRequestMethodNotSupportedException, HttpMediaTypeNotSupportedException, ServiceUnavailableException {
		if ("sql".equals(exceptionName)) {
			throw new SQLException();
		} else if ("bc".equals(exceptionName)) {
			throw new BackendServerException("Service Down...");
		} else if ("custom404".equals(exceptionName)) {
			throw new CustomerNotFoundException("'" + exceptionName + ": CustomerNotFoundException");
		} else if ("404".equals(exceptionName)) {
			throw new NoHandlerFoundException("'" + exceptionName + ": NoHandlerFoundException", exceptionName, null);
		} else if ("405".equals(exceptionName)) {
			throw new HttpRequestMethodNotSupportedException(
					"'" + exceptionName + ": HttpRequestMethodNotSupportedException");
		} else if ("415".equals(exceptionName)) {
			throw new HttpMediaTypeNotSupportedException("'" + exceptionName + ": HttpMediaTypeNotSupportedException");
		} else if ("503".equals(exceptionName)) {
			throw new ServiceUnavailableException("'" + exceptionName + ": ServiceUnavailableException");
		} else {

		}
		return null;
	}
}
