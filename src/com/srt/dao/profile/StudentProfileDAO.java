package com.srt.dao.profile;

import java.util.List;

import com.srt.exception.ApplicationException;
import com.srt.model.profile.Student;
import com.srt.model.profile.Vote;

public interface StudentProfileDAO {
	public List<Student> searchUserProfiles(Student student) throws ApplicationException;
	public Student getUserProfile(String rollNo) throws ApplicationException;
	public int getUserProfileCount(Student student) throws ApplicationException;
	public String updateProfileVote(Vote vote) throws ApplicationException;
	public String updateProfile(Student student) throws ApplicationException;
}
