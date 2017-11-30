package com.springvalidation.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.springvalidation.validations.ContactNumberConstraint;

public class StoreRequest {

	@NotNull(message = "Task name must not be blank!")
	@Pattern(regexp = "^[A-Za-z]*[A-Za-z-'. ]*[A-Za-z]*$")
	@Size(min = 3, max = 30)
	private String name;

	@NotNull
	@Size(min = 1, max = 1)
	@Pattern(regexp = "^[MFU]$")
	private String sex;

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOfBirth;

	@Email
	private String email;

	@ContactNumberConstraint
	private String phone;

	@NotEmpty
	@Pattern(regexp = "\\d{1,10}")
	private String int1To16Digit;

	@Valid
	private InnerStoreRequest innerStoreRequest;

	public InnerStoreRequest getInnerStoreRequest() {
		return innerStoreRequest;
	}

	public void setInnerStoreRequest(InnerStoreRequest innerStoreRequest) {
		this.innerStoreRequest = innerStoreRequest;
	}

	public String getInt1To16Digit() {
		return int1To16Digit;
	}

	public void setInt1To16Digit(String int1To16Digit) {
		this.int1To16Digit = int1To16Digit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
