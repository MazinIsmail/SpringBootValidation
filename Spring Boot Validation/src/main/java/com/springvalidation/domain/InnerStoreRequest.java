package com.springvalidation.domain;

import javax.validation.constraints.NotNull;

public class InnerStoreRequest {

	@NotNull(message = "CheckInner must not be blank")
	private String checkInner;

	public String getCheckInner() {
		return checkInner;
	}

	public void setCheckInner(String checkInner) {
		this.checkInner = checkInner;
	}

}
