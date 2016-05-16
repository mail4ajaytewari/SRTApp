package com.srt.util;

import org.json.JSONArray;
import org.json.JSONObject;

import com.srt.exception.ApplicationException;

/**
 * <p>This util class construct failure response in JSON format</p>
 * @author Ajay
 *
 */
public class ErrorResponseUtil {
	/**
	 * @param ApplicationException
	 * @return String
	 */
	public static String getFailureResponse(ApplicationException e) {
		JSONObject exceptionJSON = new JSONObject();
		JSONArray exceptionData = new JSONArray();
		
		exceptionJSON.put("Failure","FAILURE");
		exceptionJSON.put("errorCode", e.getExInfo().getErrorCode());
		exceptionJSON.put("message", e.getExInfo().getErrorMessage());
		
		return exceptionData.put(exceptionJSON).toString();
	}
}
