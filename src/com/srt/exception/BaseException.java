package com.srt.exception;

/**
 * @author Ajay
 *
 */
public abstract class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExceptionInfo exInfo;
	private ExceptionUtility delegatedException;
	public abstract String getSeverity();
	
	public ExceptionInfo getExInfo() {
		return exInfo;
	}

	public void setExInfo(ExceptionInfo exInfo) {
		this.exInfo = exInfo;
	}

	public BaseException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param source
	 * @param message
	 */
	public BaseException(Class source, String message) {
		super(message);

		this.exInfo = new ExceptionInfo(ErrorCodes.UNKNOWN_EXCEPTION.toString(), message);
		
		this.delegatedException = new ExceptionUtility(source, this.getSeverity(), ErrorCodes.UNKNOWN_EXCEPTION.toString() + ":" + message, null);
        this.delegatedException.handleException();
	}

	/**
	 * @param source
	 * @param message
	 * @param erroCode
	 * @param cause
	 */
	public BaseException(Class source, String message, String erroCode, Throwable cause) {
		super(message);

		this.exInfo = new ExceptionInfo(erroCode, message);
		this.delegatedException = new ExceptionUtility(source, this.getSeverity(), erroCode + ":" + message, cause);
        this.delegatedException.handleException();
	}

	public ExceptionUtility getDelegatedException() {
		return delegatedException;
	}

	public void setDelegatedException(ExceptionUtility delegatedException) {
		this.delegatedException = delegatedException;
	}
}
