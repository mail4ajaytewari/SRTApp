package com.srt.dao.login;

import com.srt.exception.ApplicationException;
import com.srt.model.Login;

/**
 * <p>This interface provide method contract to fetch user credentials</p>
 * @author Ajay
 *
 */
public interface LoginDAO {
	public Login getUserCredentials(String username) throws ApplicationException;
}
