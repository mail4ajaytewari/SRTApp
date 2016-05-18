package com.srt.exception;

/**
 * <p>This class represents exception object to capture error code and error messages.</p>
 * @author Ajay
 *
 */
public class ExceptionInfo {

    private String errorCode;    
    private String errorMessage;
    private Throwable cause;
    
    public ExceptionInfo() {}
    
    public ExceptionInfo(String errorCode, String errorMessage) {
    	this.errorCode = errorCode;
    	this.errorMessage = errorMessage;
    }
    
    public ExceptionInfo(Throwable cause, String errorMessage) {
    	this.cause = cause;
    	this.errorMessage = errorMessage;
    }
    
	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
