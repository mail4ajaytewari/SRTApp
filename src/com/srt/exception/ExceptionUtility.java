package com.srt.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

/**
 * @author Ajay
 *
 */
public class ExceptionUtility {
	private Throwable cause;
	private String message;
	private Class exceptionSourceClass;
	private String severity;
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionUtility.class);
	
	/**
	 * @param exceptionSource
	 * @param severity
	 * @param message
	 * @param cause
	 */
	public ExceptionUtility(Class exceptionSource, String severity, String message, Throwable cause) {
        this.exceptionSourceClass = exceptionSource;
        this.severity = severity;
        this.message = message;
        this.cause = cause;
    }
	
	/**
	 * 
	 */
	public void handleException() {
		if (SeverityLevel.ERROR.toString().equalsIgnoreCase(this.severity)) {
			logger.error(MarkerFactory.getMarker("ERROR"), printStackTracesToString(this.cause));
        } else if (SeverityLevel.WARN.toString().equalsIgnoreCase(this.severity)) {
        	logger.warn(MarkerFactory.getMarker("WARN"), printStackTracesToString(this.cause));
        } else if (SeverityLevel.INFO.toString().equalsIgnoreCase(this.severity)) {
        	logger.info(MarkerFactory.getMarker("INFO"), printStackTracesToString(this.cause));
        } else if (SeverityLevel.DEBUG.toString().equalsIgnoreCase(this.severity)) {
        	logger.debug(MarkerFactory.getMarker("DEBUG"), printStackTracesToString(this.cause));
        } else {
        	logger.trace(MarkerFactory.getMarker("TRACE"), printStackTracesToString(this.cause));
        }
	}
	
	/**
	 * @param throwable
	 * @return
	 */
	public String printStackTracesToString(Throwable throwable) {
        StringBuilder buf = new StringBuilder(captureStackTrace(throwable));

        return buf.toString();
	}
	
	/**
	 * @param throwable
	 * @return
	 */
	private String captureStackTrace(Throwable throwable) {
        if (throwable == null) {
        	return "";
        } else {
            StringWriter sw = new StringWriter();
            throwable.printStackTrace(new PrintWriter(sw, true));
            return sw.toString();
        }
	}	
	
	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Class getExceptionSourceClass() {
		return exceptionSourceClass;
	}

	public void setExceptionSourceClass(Class exceptionSourceClass) {
		this.exceptionSourceClass = exceptionSourceClass;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}
}
