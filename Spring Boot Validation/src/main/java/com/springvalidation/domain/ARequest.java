package com.springvalidation.domain;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.springvalidation.custom.validation.ConditionalMandatoryConstraint;
import com.springvalidation.custom.validation.RequestValidationConstraint;

@RequestValidationConstraint
@ConditionalMandatoryConstraint
public class ARequest {

	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	private String transactionDateTime;
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	@Pattern(regexp = "\\d{0,12}", message = ValidationMessages.INVALID_DATA_MESSAGE)
	private String transactionReferenceNumber;
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	private String systemCode;
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	private String groupNumber;
	@Size(max = 10, message = ValidationMessages.INVALID_DATA_MESSAGE)
	private String certificateNumber;
	@Size(max = 30, message = ValidationMessages.INVALID_DATA_MESSAGE)
	private String legalDocumentId;
	private String dateOfBirth;
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	@Pattern(regexp = "(?)Y|N", message = ValidationMessages.INVALID_DATA_MESSAGE)
	private String hasSecondClaim;
	private String internalSecondClaimGroupNumber;
	private String internalSecondClaimSubGroupNumber;
	private String internalSecondClaimCertificateNumber;
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	@Pattern(regexp = "(?)Y|N", message = ValidationMessages.INVALID_DATA_MESSAGE)
	private String isSecondClaim;
	private String clientProfile;
	private String staffIndicator;
	@Valid
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	private List<Document> document;
	@Valid
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	private List<Treatment> treatment;

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getTransactionReferenceNumber() {
		return transactionReferenceNumber;
	}

	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public String getLegalDocumentId() {
		return legalDocumentId;
	}

	public void setLegalDocumentId(String legalDocumentId) {
		this.legalDocumentId = legalDocumentId;
	}

	public void setCertificateNumber(String certificateNumber) {
		if (certificateNumber != null) {
			this.certificateNumber = certificateNumber.trim();
		}
	}

	public String getHasSecondClaim() {
		return hasSecondClaim;
	}

	public void setHasSecondClaim(String hasSecondClaim) {
		this.hasSecondClaim = hasSecondClaim;
	}

	public String getInternalSecondClaimGroupNumber() {
		return internalSecondClaimGroupNumber;
	}

	public void setInternalSecondClaimGroupNumber(String internalSecondClaimGroupNumber) {
		this.internalSecondClaimGroupNumber = internalSecondClaimGroupNumber;
	}

	public String getInternalSecondClaimSubGroupNumber() {
		return internalSecondClaimSubGroupNumber;
	}

	public void setInternalSecondClaimSubGroupNumber(String internalSecondClaimSubGroupNumber) {
		this.internalSecondClaimSubGroupNumber = internalSecondClaimSubGroupNumber;
	}

	public String getInternalSecondClaimCertificateNumber() {
		return internalSecondClaimCertificateNumber;
	}

	public void setInternalSecondClaimCertificateNumber(String internalSecondClaimCertificateNumber) {
		this.internalSecondClaimCertificateNumber = internalSecondClaimCertificateNumber;
	}

	public String getIsSecondClaim() {
		return isSecondClaim;
	}

	public void setIsSecondClaim(String isSecondClaim) {
		this.isSecondClaim = isSecondClaim;
	}

	public String getClientProfile() {
		return clientProfile;
	}

	public void setClientProfile(String clientProfile) {
		this.clientProfile = clientProfile;
	}

	public String getStaffIndicator() {
		return staffIndicator;
	}

	public void setStaffIndicator(String staffIndicator) {
		this.staffIndicator = staffIndicator;
	}

	public List<Document> getDocument() {
		return document;
	}

	public void setDocument(List<Document> document) {
		this.document = document;
	}

	public List<Treatment> getTreatment() {
		return treatment;
	}

	public void setTreatment(List<Treatment> treatment) {
		this.treatment = treatment;
	}

}
