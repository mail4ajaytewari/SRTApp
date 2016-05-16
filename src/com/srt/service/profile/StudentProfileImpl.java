package com.srt.service.profile;

import java.util.List;

import com.srt.dao.profile.StudentProfileDAO;
import com.srt.exception.ApplicationException;
import com.srt.model.profile.Student;
import com.srt.model.profile.Vote;

public class StudentProfileImpl implements StudentProfile {
	private StudentProfileDAO studentProfileDAO;
	
	public StudentProfileDAO getStudentProfileDAO() {
		return studentProfileDAO;
	}

	public void setStudentProfileDAO(StudentProfileDAO studentProfileDAO) {
		this.studentProfileDAO = studentProfileDAO;
	}

	@Override
	public List<Student> searchUserProfiles(Student student) throws ApplicationException {
		// TODO Auto-generated method stub
		return studentProfileDAO.searchUserProfiles(student);
	}

	@Override
	public Student getUserProfile(String rollNo) throws ApplicationException {
		// TODO Auto-generated method stub
		return studentProfileDAO.getUserProfile(rollNo);
	}

	@Override
	public int getUserProfileCount(Student student) throws ApplicationException{
		// TODO Auto-generated method stub
		return studentProfileDAO.getUserProfileCount(student);
	}

	@Override
	public String updateProfileVote(Vote vote) throws ApplicationException {
		// TODO Auto-generated method stub
		return studentProfileDAO.updateProfileVote(vote);
	}

	@Override
	public String updateProfile(Student student) throws ApplicationException {
		// TODO Auto-generated method stub
		return studentProfileDAO.updateProfile(student);
	}

}
