package com.mediacross.lottery.common.error;

/**
 * 业务级别异常。
 * 
 * @author qaohao
 */
public class AppException extends Exception {
	private String errorCode;
	private String errorMsg;
	public AppException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppException [errorCode=" + errorCode + ", errorMsg="
				+ errorMsg + "]";
	}
	
}