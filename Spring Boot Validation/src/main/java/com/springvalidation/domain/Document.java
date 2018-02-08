package com.springvalidation.domain;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.springvalidation.custom.validation.ConditionalMandatoryDocumentConstraint;

@ConditionalMandatoryDocumentConstraint
public class Document {
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	private String documentType;
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	@Size(max = 100, message = ValidationMessages.INVALID_DATA_MESSAGE)
	private String fileKey;
	@Pattern(regexp = "^\\d{0,10}(\\.\\d{0,2})?$", message = ValidationMessages.INVALID_DATA_MESSAGE)
	private String transactionAmount;
	private String currency;

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		if (transactionAmount != null) {
			this.transactionAmount = transactionAmount.trim();
		}
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Document [documentType=" + documentType + ", fileKey=" + fileKey + ", transactionAmount="
				+ transactionAmount + ", currency=" + currency + "]";
	}

}
