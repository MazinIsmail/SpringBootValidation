package com.springvalidation.domain;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class StoreListRequest {

	@NotBlank
	private String name;

	@Valid
	private List<InnerStoreRequest> innerStoreRequestList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<InnerStoreRequest> getInnerStoreRequestList() {
		return innerStoreRequestList;
	}

	public void setInnerStoreRequestList(List<InnerStoreRequest> innerStoreRequestList) {
		this.innerStoreRequestList = innerStoreRequestList;
	}

}
