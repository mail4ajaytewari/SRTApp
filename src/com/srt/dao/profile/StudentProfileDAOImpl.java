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
import com.srt.model.profile.Student;
import com.srt.model.profile.Vote;
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
	public List<Student> searchUserProfiles(Student student) throws ApplicationException {
		logger.debug("Start: searchUserProfiles");
		int iDisplayStart = student.getiDisplayStart();
		int iDisplayLength = student.getiDisplayLength();
		List<Student> listOfProfiles = null;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rollNo", student.getRollNo());
		paramMap.put("firstName", "%" + student.getFirstName() + "%");
		paramMap.put("lastName", "%" + student.getLastName() + "%");
		paramMap.put("start", iDisplayStart);
		paramMap.put("end", iDisplayLength);
		
		
		String query = QueryUtil.queryDispenserForRecords(student.getFirstName(), student.getLastName(), student.getRollNo());
		logger.debug("Query: {}", query);
		try {
			listOfProfiles = getNamedParameterJdbcTemplate().query(query, paramMap, new RowMapper<Student>(){
				@Override
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Student student = new Student();
					student.setFirstName(rs.getString("firstName"));
					student.setLastName(rs.getString("lastName"));
					student.setRollNo(rs.getString("rollNo"));
					student.setBranchName(rs.getString("branchName"));
					student.setEmail(rs.getString("email"));
					student.setPhone(rs.getString("phone"));
					student.setVotes(rs.getString("votes"));
					return student;
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
	public Student getUserProfile(String rollNo) throws ApplicationException {
		logger.debug("Start: getUserProfile {}", rollNo);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rollNo", rollNo);
		List<Student> listOfProfiles = null;
		
		try {
			listOfProfiles = getNamedParameterJdbcTemplate().query(QueryConstants.GET_USER_PROFILE_FOR_ROLLNO, paramMap, new RowMapper<Student>(){
				@Override
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Student student = new Student();
					student.setFirstName(rs.getString("firstName"));
					student.setLastName(rs.getString("lastName"));
					student.setRollNo(rs.getString("rollNo"));
					student.setBranchName(rs.getString("branchName"));
					student.setEmail(rs.getString("email"));
					student.setPhone(rs.getString("phone"));
					student.setVotes(rs.getString("votes"));
					return student;
				}			
			});
		}catch(DataAccessException e) {
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);
			throw new ApplicationException(clazz,"Data Access Exception", "SYS-ERR-1000", e);
		}
		
		if(null != listOfProfiles && null != listOfProfiles.get(0)) {
			Student profile = listOfProfiles.get(0);
			return profile;
		}
		
		logger.debug("End: getUserProfile");
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.srt.dao.profile.StudentProfileDAO#getUserProfileCount(com.srt.model.profile.Student)
	 */
	@Override
	public int getUserProfileCount(Student student) throws ApplicationException {
		logger.debug("Start: getUserProfileCount {}", student.getRollNo());
		
		int totalRows = 0;
		List<Integer> countOfProfiles = null;
		String query = QueryUtil.queryDispenserForRecordsCount(student.getFirstName(), student.getLastName(), student.getRollNo());
		logger.debug("Query:", query);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("firstName", "%" + student.getFirstName() + "%");
		paramMap.put("lastName", "%" + student.getLastName() + "%");
		paramMap.put("rollNo", "%" + student.getRollNo() + "%");
		
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
	public String updateProfile(Student student) throws ApplicationException {
		logger.debug("Start: updateProfile: {}" , student.getRollNo());
		
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rollNo", student.getRollNo());
		paramMap.put("firstName", student.getFirstName());
		paramMap.put("lastName", student.getLastName());
		paramMap.put("email", student.getEmail());
		paramMap.put("phone", student.getPhone());
		
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
