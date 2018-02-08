package com.springvalidation.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Treatment {
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	private String treatmentCategory;
	@NotEmpty(message = ValidationMessages.EMPTY_MESSAGE)
	private String treatmentType;

	public String getTreatmentCategory() {
		return treatmentCategory;
	}

	public void setTreatmentCategory(String treatmentCategory) {
		this.treatmentCategory = treatmentCategory;
	}

	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	@Override
	public String toString() {
		return "Treatment [treatmentCategory=" + treatmentCategory + ", treatmentType=" + treatmentType + "]";
	}

}
