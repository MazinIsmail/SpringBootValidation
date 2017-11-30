package com.springvalidation.exceptions;

public abstract class ApiError {

	private String errCode;
	private String errMsg;

	public ApiError() {
		super();
	}

	public ApiError(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errCode == null) ? 0 : errCode.hashCode());
		result = prime * result + ((errMsg == null) ? 0 : errMsg.hashCode());
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
		ApiError other = (ApiError) obj;
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
		return true;
	}

}
