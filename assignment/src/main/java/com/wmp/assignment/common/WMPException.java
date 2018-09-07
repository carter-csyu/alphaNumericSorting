package com.wmp.assignment.common;

public final class WMPException extends RuntimeException {
	private static final long serialVersionUID = 4941763640999091851L;
	
	public WMPException(String message) {
		super(message);
	}
	
	public WMPException(String message, Throwable error) {
		super(message, error);
	}
	
	public WMPException(Throwable e) {
		super(e);
	}
}
