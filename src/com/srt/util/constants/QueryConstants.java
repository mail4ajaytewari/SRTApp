package com.srt.util.constants;

/**
 * <p>Utility to keep all query constants</p>
 * @author Ajay
 *
 */
public interface QueryConstants {
	final String GET_USER_PROFILE_FOR_ROLLNO = "select s.firstName firstName, s.lastName lastName, s.rollNo rollNo, b.branchName branchName, s.email email, s.phone phone, v.voteCount votes from student s, branch b, voteStastics v where s.rollNo = :rollNo and s.branchId = b.branchId and s.rollNo = v.rollNo";
	final String GET_PROFILES_COUNT_FOR_ALL = "Select count(*) count from student";
	final String GET_PROFILES_COUNT_FOR_FIRST_NAME = "select count(*) count from student s, branch b, voteStastics v where s.firstName like :firstName and s.branchId = b.branchId and s.rollNo = v.rollNo";
	final String GET_PROFILES_COUNT_FOR_LAST_NAME = "select count(*) count from student s, branch b, voteStastics v where s.lastName like :lastName and s.branchId = b.branchId and s.rollNo = v.rollNo";
	final String GET_PROFILES_COUNT_FOR_ROLLNO = "select count(*) count from student s, branch b, voteStastics v where s.rollNo like :rollNo and s.branchId = b.branchId and s.rollNo = v.rollNo";
	final String GET_ALL_PROFILES = "select s.firstName firstName, s.lastName lastName, s.rollNo rollNo,b.branchName branchName, s.email email, s.phone phone, v.voteCount votes from student s, branch b, voteStastics v where s.branchId = b.branchId and s.rollNo = v.rollNo LIMIT :start,:end";
	final String GET_USER_PROFILE_FOR_FIRST_NAME = "select s.firstName firstName, s.lastName lastName, s.rollNo rollNo, b.branchName branchName, s.email email, s.phone phone, v.voteCount votes from student s, branch b, voteStastics v where s.firstName like :firstName and s.branchId = b.branchId and s.rollNo = v.rollNo LIMIT :start,:end";
	final String GET_USER_PROFILE_FOR_LAST_NAME = "select s.firstName firstName, s.lastName lastName, s.rollNo rollNo, b.branchName branchName, s.email email, s.phone phone, v.voteCount votes from student s, branch b, voteStastics v where s.lastName like :lastName and s.branchId = b.branchId and s.rollNo = v.rollNo  LIMIT :start,:end";
	final String UPDATE_VOTES = "update voteStastics set voteCount = :voteCount where rollNo = :rollNo";
	final String UPDATE_PROFILE = "update student set firstName = :firstName, lastName = :lastName, email = :email, phone = :phone where rollNo = :rollNo";
	final String GET_USER_CREDENTIALS = "select username, password, rollNo from login where username = :username";
}
