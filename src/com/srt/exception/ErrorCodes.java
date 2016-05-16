package com.srt.exception;

/**
 * @author Ajay
 *
 */
public enum ErrorCodes {
	DATABASE_NOT_REACHABLE("DATABASE_NOT_REACHABLE"), 
	UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION"), 
	INCORRECT_EMAIL_FORMAT("INCORRECT_EMAIL_FORMAT"), 
	PROFILE_NOT_FOUND("PROFILE_NOT_FOUND"), 
	SEARCH_FAILURE("SEARCH_FAILURE"),
	APPLICATION_EXCEPTION("APPLICATION_EXCEPTION"),
	NULL_CHECK_FAILURE("NULL_CHECK_FAILURE"),
	LOGIN_FAILURE("LOGIN_FAILURE"),
	NETWORK_FAILURE("NETWORK_FAILURE");
	
	public String getCode() {
		return code;
	}

	private final String code;
	
	private ErrorCodes(String code) {
		this.code = code;
	}
	
	@Override
    public String toString() {
        return code;
    }

}
