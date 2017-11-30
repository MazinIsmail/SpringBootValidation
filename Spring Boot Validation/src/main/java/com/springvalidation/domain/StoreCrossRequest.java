package com.springvalidation.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.springvalidation.validations.RequestCrossFieldValidation;

@RequestCrossFieldValidation.List({
		@RequestCrossFieldValidation(first = "name", second = "nameDependent", errorMessage = "Namd does not match with standards") })
public class StoreCrossRequest {

	@NotNull(message = "Task name must not be null!")
	private String name;

	@NotEmpty(message = "Task name must not be blank!")
	private String nameDependent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameDependent() {
		return nameDependent;
	}

	public void setNameDependent(String nameDependent) {
		this.nameDependent = nameDependent;
	}

}
