package com.srt.model;

/**
 * <p>This model object represent login details of user.</p>
 * @author Ajay
 *
 */
public class Login {
	private String username;
	private String password;
	private String rollNo;
	
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
