package com.srt.model;

/**
 * <p>This model class represents vote object to capture votes by users.</p>
 * @author Ajay
 *
 */
public class Vote {
	private String rollNo;
	private String voteCount;

	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(String voteCount) {
		this.voteCount = voteCount;
	}
}
