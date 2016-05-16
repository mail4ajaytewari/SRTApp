package com.srt.util;

import com.mysql.jdbc.StringUtils;
import com.srt.util.constants.QueryConstants;

/**
 * <p>This util class dispense queries based on filter criteria</p>
 * @author Ajay
 *
 */
public class QueryUtil {
	/**
	 * @param firstName
	 * @param lastName
	 * @param rollNo
	 * @return
	 */
	public static String queryDispenserForRecords(String firstName, String lastName, String rollNo) {
		String query = "";
		if(!StringUtils.isNullOrEmpty(rollNo)) {
			query = QueryConstants.GET_USER_PROFILE_FOR_ROLLNO;
		}else if(!StringUtils.isNullOrEmpty(firstName)){
			query = QueryConstants.GET_USER_PROFILE_FOR_FIRST_NAME;
		}else if(!StringUtils.isNullOrEmpty(lastName)){
			query = QueryConstants.GET_USER_PROFILE_FOR_LAST_NAME;
		}else if(StringUtils.isNullOrEmpty(rollNo) && StringUtils.isNullOrEmpty(firstName) && StringUtils.isNullOrEmpty(lastName)) {
			query = QueryConstants.GET_ALL_PROFILES;
		}
		return query;
	}
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param rollNo
	 * @return
	 */
	public static String queryDispenserForRecordsCount(String firstName, String lastName, String rollNo) {
		String query = "";
		if(!StringUtils.isNullOrEmpty(firstName)){
			query = QueryConstants.GET_PROFILES_COUNT_FOR_FIRST_NAME;
		}else if(!StringUtils.isNullOrEmpty(lastName)){
			query = QueryConstants.GET_PROFILES_COUNT_FOR_LAST_NAME;
		}else if(!StringUtils.isNullOrEmpty(rollNo)){
			query = QueryConstants.GET_PROFILES_COUNT_FOR_ROLLNO;
		}else if(StringUtils.isNullOrEmpty(rollNo) && StringUtils.isNullOrEmpty(firstName) && StringUtils.isNullOrEmpty(lastName)) {
			query = QueryConstants.GET_PROFILES_COUNT_FOR_ALL;
		}
		return query;
	}
}
