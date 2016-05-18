package com.srt.model;

/**
 * <p>This model class encapsulates Profile details of user.</p>
 * @author Ajay
 *
 */
public class Profile {
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String votes;
	private String branchName;
	private String rollNo;
	
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVotes() {
		return votes;
	}
	public void setVotes(String votes) {
		this.votes = votes;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
