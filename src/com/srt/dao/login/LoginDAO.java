package com.srt.dao.login;

import com.srt.exception.ApplicationException;
import com.srt.model.Login;

public interface LoginDAO {
	public Login getUserCredentials(String username) throws ApplicationException;
}
