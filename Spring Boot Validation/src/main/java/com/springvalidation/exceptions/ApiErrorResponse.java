package com.springvalidation.exceptions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiErrorResponse {

	private String errCode;

	private String errMsg;

	private boolean hasMultipleErrors;

	private String debugMessage;

	private List<ApiError> errors;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	public ApiErrorResponse(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.hasMultipleErrors = false;
		this.timestamp = generateDateTime();
	}

	public ApiErrorResponse(String errCode, String errMsg, String debugMessage) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.hasMultipleErrors = false;
		this.debugMessage = debugMessage;
		this.timestamp = generateDateTime();
	}

	public ApiErrorResponse(String errCode, String errMsg, List<ApiError> errors) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.hasMultipleErrors = true;
		this.errors = errors;
		this.timestamp = generateDateTime();
	}

	private LocalDateTime generateDateTime() {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = new Date();
		Instant instant = date.toInstant();
		return instant.atZone(defaultZoneId).toLocalDateTime();
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public boolean isHasMultipleErrors() {
		return hasMultipleErrors;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public List<ApiError> getErrors() {
		return errors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((debugMessage == null) ? 0 : debugMessage.hashCode());
		result = prime * result + ((errCode == null) ? 0 : errCode.hashCode());
		result = prime * result + ((errMsg == null) ? 0 : errMsg.hashCode());
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		result = prime * result + (hasMultipleErrors ? 1231 : 1237);
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiErrorResponse other = (ApiErrorResponse) obj;
		if (debugMessage == null) {
			if (other.debugMessage != null)
				return false;
		} else if (!debugMessage.equals(other.debugMessage))
			return false;
		if (errCode == null) {
			if (other.errCode != null)
				return false;
		} else if (!errCode.equals(other.errCode))
			return false;
		if (errMsg == null) {
			if (other.errMsg != null)
				return false;
		} else if (!errMsg.equals(other.errMsg))
			return false;
		if (errors == null) {
			if (other.errors != null)
				return false;
		} else if (!errors.equals(other.errors))
			return false;
		if (hasMultipleErrors != other.hasMultipleErrors)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApiErrorResponse [errCode=").append(errCode).append(", errMsg=").append(errMsg)
				.append(", hasMultipleErrors=").append(hasMultipleErrors).append(", debugMessage=").append(debugMessage)
				.append(", errors=").append(errors).append(", timestamp=").append(timestamp).append("]");
		return builder.toString();
	}

}
