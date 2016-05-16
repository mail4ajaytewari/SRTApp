package com.srt.exception;

/**
 * <p>This class handles all checked/unchecked exceptions
 * and provide various constructors to log the exceptions.</p>
 * @author Ajay
 *
 */
public class ApplicationException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param source
	 * @param message
	 */
	public ApplicationException(Class source, String message) {
		super(source, message);
	}

	/**
	 * @param source
	 * @param message
	 * @param errorCode
	 * @param cause
	 */
	public ApplicationException(Class source, String message, String errorCode, Throwable cause) {
		super(source, message, errorCode, cause);
	}

	/* (non-Javadoc)
	 * @see com.srt.exception.BaseException#getSeverity()
	 */
	@Override
	public String getSeverity() {
		// TODO Auto-generated method stub
		return SeverityLevel.ERROR.toString();
	}	
}
