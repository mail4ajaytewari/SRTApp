package com.srt.service.profile;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.srt.dao.profile.StudentProfileDAO;
import com.srt.exception.ApplicationException;
import com.srt.model.profile.Student;
import com.srt.model.profile.Vote;

/**
 * @author Ajay
 *
 */
public class StudentProfileImpl implements StudentProfile {
	
	private static final Class clazz = StudentProfileImpl.class;
	private static final Logger logger = LoggerFactory.getLogger(clazz);
	
	private StudentProfileDAO studentProfileDAO;
	
	public StudentProfileDAO getStudentProfileDAO() {
		return studentProfileDAO;
	}

	public void setStudentProfileDAO(StudentProfileDAO studentProfileDAO) {
		this.studentProfileDAO = studentProfileDAO;
	}

	/* (non-Javadoc)
	 * @see com.srt.service.profile.StudentProfile#searchUserProfiles(com.srt.model.profile.Student)
	 */
	@Override
	public List<Student> searchUserProfiles(Student student) throws ApplicationException {
		// TODO Auto-generated method stub
		return studentProfileDAO.searchUserProfiles(student);
	}

	/* (non-Javadoc)
	 * @see com.srt.service.profile.StudentProfile#getUserProfile(java.lang.String)
	 */
	@Override
	public Student getUserProfile(String rollNo) throws ApplicationException {
		// TODO Auto-generated method stub
		return studentProfileDAO.getUserProfile(rollNo);
	}

	/* (non-Javadoc)
	 * @see com.srt.service.profile.StudentProfile#getUserProfileCount(com.srt.model.profile.Student)
	 */
	@Override
	public int getUserProfileCount(Student student) throws ApplicationException{
		// TODO Auto-generated method stub
		return studentProfileDAO.getUserProfileCount(student);
	}

	/* (non-Javadoc)
	 * @see com.srt.service.profile.StudentProfile#updateProfileVote(com.srt.model.profile.Vote)
	 */
	@Override
	public String updateProfileVote(Vote vote) throws ApplicationException {
		// TODO Auto-generated method stub
		return studentProfileDAO.updateProfileVote(vote);
	}

	/* (non-Javadoc)
	 * @see com.srt.service.profile.StudentProfile#updateProfile(com.srt.model.profile.Student)
	 */
	@Override
	public String updateProfile(Student student) throws ApplicationException {
		// TODO Auto-generated method stub
		return studentProfileDAO.updateProfile(student);
	}

}
