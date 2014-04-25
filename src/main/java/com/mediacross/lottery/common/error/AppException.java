package com.mediacross.lottery.common.error;

/**
 * 业务级别异常。
 * 
 * @author qaohao
 */
public class AppException extends Exception {
	private int errorCode;
	private String errorMsg;
	public AppException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
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