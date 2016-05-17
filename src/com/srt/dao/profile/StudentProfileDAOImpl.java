package com.srt.dao.profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.srt.exception.ApplicationException;
import com.srt.model.profile.ProfileSearchRequest;
import com.srt.model.profile.Profile;
import com.srt.model.searchGrid.DataTable;
import com.srt.model.vote.Vote;
import com.srt.util.QueryUtil;
import com.srt.util.constants.QueryConstants;

/**
 * <p>This class provided API to fetch, update profile details from database
 * based on various criteria</p>
 * @author Ajay
 *
 */
public class StudentProfileDAOImpl implements StudentProfileDAO {
	
	private static final Class clazz = StudentProfileDAOImpl.class;
	private static final Logger logger = LoggerFactory.getLogger(clazz);
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/* (non-Javadoc)
	 * @see com.srt.dao.profile.StudentProfileDAO#searchUserProfiles(com.srt.model.profile.Student)
	 */
	@Override
	public List<Profile> searchUserProfiles(ProfileSearchRequest request) throws ApplicationException {
		logger.debug("Start: searchUserProfiles");
		Profile profile = request.getStudent();
		DataTable table = request.getTable();
		
		int iDisplayStart = table.getiDisplayStart();
		int iDisplayLength = table.getiDisplayLength();
		List<Profile> listOfProfiles = null;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rollNo", profile.getRollNo());
		paramMap.put("firstName", "%" + profile.getFirstName() + "%");
		paramMap.put("lastName", "%" + profile.getLastName() + "%");
		paramMap.put("start", iDisplayStart);
		paramMap.put("end", iDisplayLength);
		
		
		String query = QueryUtil.queryDispenserForRecords(profile.getFirstName(), profile.getLastName(), profile.getRollNo());
		logger.debug("Query: {}", query);
		try {
			listOfProfiles = getNamedParameterJdbcTemplate().query(query, paramMap, new RowMapper<Profile>(){
				@Override
				public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Profile profile = new Profile();
					profile.setFirstName(rs.getString("firstName"));
					profile.setLastName(rs.getString("lastName"));
					profile.setRollNo(rs.getString("rollNo"));
					profile.setBranchName(rs.getString("branchName"));
					profile.setEmail(rs.getString("email"));
					profile.setPhone(rs.getString("phone"));
					profile.setVotes(rs.getString("votes"));
					return profile;
				}			
			});
		}catch(DataAccessException e){
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);
			throw new ApplicationException(clazz,"Data Access Exception", "SYS-ERR-1000", e);
		}
		
		logger.debug("Start: searchUserProfiles");
		return listOfProfiles;
	}

	
	
	/* (non-Javadoc)
	 * @see com.srt.dao.profile.StudentProfileDAO#getUserProfile(java.lang.String)
	 */
	@Override
	public Profile getUserProfile(String rollNo) throws ApplicationException {
		logger.debug("Start: getUserProfile {}", rollNo);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rollNo", rollNo);
		List<Profile> listOfProfiles = null;
		
		try {
			listOfProfiles = getNamedParameterJdbcTemplate().query(QueryConstants.GET_USER_PROFILE_FOR_ROLLNO, paramMap, new RowMapper<Profile>(){
				@Override
				public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Profile profile = new Profile();
					profile.setFirstName(rs.getString("firstName"));
					profile.setLastName(rs.getString("lastName"));
					profile.setRollNo(rs.getString("rollNo"));
					profile.setBranchName(rs.getString("branchName"));
					profile.setEmail(rs.getString("email"));
					profile.setPhone(rs.getString("phone"));
					profile.setVotes(rs.getString("votes"));
					return profile;
				}			
			});
		}catch(DataAccessException e) {
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);
			throw new ApplicationException(clazz,"Data Access Exception", "SYS-ERR-1000", e);
		}
		
		if(null != listOfProfiles && null != listOfProfiles.get(0)) {
			Profile profile = listOfProfiles.get(0);
			return profile;
		}
		
		logger.debug("End: getUserProfile");
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.srt.dao.profile.StudentProfileDAO#getUserProfileCount(com.srt.model.profile.Student)
	 */
	@Override
	public int getUserProfileCount(Profile profile) throws ApplicationException {
		logger.debug("Start: getUserProfileCount {}", profile.getRollNo());
		
		int totalRows = 0;
		List<Integer> countOfProfiles = null;
		String query = QueryUtil.queryDispenserForRecordsCount(profile.getFirstName(), profile.getLastName(), profile.getRollNo());
		logger.debug("Query:", query);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("firstName", "%" + profile.getFirstName() + "%");
		paramMap.put("lastName", "%" + profile.getLastName() + "%");
		paramMap.put("rollNo", "%" + profile.getRollNo() + "%");
		
		try {
			countOfProfiles = getNamedParameterJdbcTemplate().query(query, paramMap, new RowMapper<Integer>(){

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub						
					return new Integer(rs.getString("count"));
				}			
			});
		}catch(DataAccessException e) {
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);
			throw new ApplicationException(clazz,"Data Access Exception", "SYS-ERR-1000", e);
		}
		
		if(null != countOfProfiles && null != countOfProfiles.get(0)) {
			totalRows = countOfProfiles.get(0).intValue();
			return totalRows;
		}
		
		logger.debug("End: getUserProfileCount: Total records - {}", totalRows);
		return totalRows;
	}

	/* (non-Javadoc)
	 * @see com.srt.dao.profile.StudentProfileDAO#updateProfileVote(com.srt.model.profile.Vote)
	 */
	@Override
	public String updateProfileVote(Vote vote) throws ApplicationException{
		logger.debug("Start: updateProfileVote:");
		
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rollNo", vote.getRollNo());
		paramMap.put("voteCount", vote.getVoteCount());
		int response = 0;
		
		try {
			response = getNamedParameterJdbcTemplate().update(QueryConstants.UPDATE_VOTES, paramMap);
			// To test the exceptions 
			//String obj = null;
			//obj.toString();
		}catch(DataAccessException | NullPointerException e) {
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);
			throw new ApplicationException(clazz,"Data Access Exception", "SYS-ERR-1000", e);
		}
		
		logger.debug("End: updateProfileVote: {}" , response);
		return String.valueOf(response);
	}

	/* (non-Javadoc)
	 * @see com.srt.dao.profile.StudentProfileDAO#updateProfile(com.srt.model.profile.Student)
	 */
	@Override
	public String updateProfile(Profile profile) throws ApplicationException {
		logger.debug("Start: updateProfile: {}" , profile.getRollNo());
		
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rollNo", profile.getRollNo());
		paramMap.put("firstName", profile.getFirstName());
		paramMap.put("lastName", profile.getLastName());
		paramMap.put("email", profile.getEmail());
		paramMap.put("phone", profile.getPhone());
		
		int response = 0;
		
		try {
			response = getNamedParameterJdbcTemplate().update(QueryConstants.UPDATE_PROFILE, paramMap);
		}catch(DataAccessException | NullPointerException e) {
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);
			throw new ApplicationException(clazz,"Data Access Exception", "SYS-ERR-1000", e);
		}
		logger.debug("End: updateProfile: {}" , response);
		return String.valueOf(response);
	}

}
