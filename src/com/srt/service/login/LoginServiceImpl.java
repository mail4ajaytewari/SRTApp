package com.srt.service.login;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.srt.dao.login.LoginDAO;
import com.srt.exception.ApplicationException;
import com.srt.model.profile.Login;
import com.srt.util.SessionManagementUtil;

/**
 * @author Ajay
 *
 */
public class LoginServiceImpl implements LoginService {

	private static final Class clazz = LoginServiceImpl.class;
	private static final Logger logger = LoggerFactory.getLogger(clazz);
	
	private LoginDAO loginDAO;
	private Login login;
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
	    authList.add(new SimpleGrantedAuthority("ROLE_USER"));
	    
	    try {
			login = loginDAO.getUserCredentials(username);
			SessionManagementUtil.getSession().setAttribute("rollNo", login.getRollNo());
		} catch (ApplicationException e) {
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);
		}

		UserDetails user = new User(username, login.getPassword(), true, true, true, true, authList);
        return user;
	}

}
