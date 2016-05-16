package com.srt.dao.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.srt.exception.ApplicationException;
import com.srt.model.profile.Login;
import com.srt.util.constants.QueryConstants;

/**
 * <p>This class provided API to fetch credentials from Database.</p>
 * <p>To Do: Need to implement encrypt-decrypt logic</p>
 * @author Ajay
 *
 */
public class LoginDAOImpl implements LoginDAO {
	private static final Class clazz = LoginDAOImpl.class;
	private static final Logger logger = LoggerFactory.getLogger(clazz);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	/* (non-Javadoc)
	 * @see com.srt.dao.login.LoginDAO#getUserCredentials(java.lang.String)
	 */
	@Override
	public Login getUserCredentials(String username) throws ApplicationException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", username);
		List<Login> userCred = null;
		
		try {
			userCred = getNamedParameterJdbcTemplate().query(QueryConstants.GET_USER_CREDENTIALS, paramMap, new RowMapper<Login>(){
				@Override
				public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Login login = new Login();
					login.setUsername(rs.getString("username"));
					login.setPassword(rs.getString("password"));
					login.setRollNo(rs.getString("rollNo"));
					return login;
				}			
			});
		}catch(DataAccessException e) {
			logger.debug("{}: Data Acess Exception :: {}", "ERR-LOGIN-1000", e);
			throw new ApplicationException(clazz,"Data Access Exception", "ERR-LOGIN-1000", e);
		}
		
		if(null != userCred && null != userCred.get(0)) {
			Login loginDetails = userCred.get(0);
			return loginDetails;
		}
		return null;
	}
	
}
