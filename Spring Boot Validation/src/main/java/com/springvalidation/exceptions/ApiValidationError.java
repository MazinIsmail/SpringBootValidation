package com.springvalidation.exceptions;

public class ApiValidationError extends ApiError {

	private String field;

	public ApiValidationError(String errCode, String errMessage, String field) {
		super(errCode, errMessage);
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public ApiValidationError(String field, String errMessage) {
		this.setErrMsg(errMessage);
		this.field = field;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiValidationError other = (ApiValidationError) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApiValidationError [field=").append(field).append(", getErrCode()=").append(getErrCode())
				.append(", getErrMsg()=").append(getErrMsg()).append("]");
		return builder.toString();
	}

}
