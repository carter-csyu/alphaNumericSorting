package com.wmp.assignment.common;

public class ErrorResponse {
	public static final int DEFAULT_CODE = -1;
	
	private int errCode;
	private String errMsg;
	
	public ErrorResponse() {}
	public ErrorResponse(String errMsg) {
		this.errMsg = errMsg;
		this.errCode = DEFAULT_CODE;
	}
	public ErrorResponse(int errCode, String errMsg) {
		this(errMsg);
		this.errCode = errCode;
	}
	
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errorCode) {
		this.errCode = errorCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errorMessage) {
		this.errMsg = errorMessage;
	}
}
