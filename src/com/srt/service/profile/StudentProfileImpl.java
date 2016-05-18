package com.srt.service.profile;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.srt.dao.profile.StudentProfileDAO;
import com.srt.exception.ApplicationException;
import com.srt.model.ProfileSearchRequest;
import com.srt.model.ProfileSearchResponse;
import com.srt.model.DataTable;
import com.srt.model.Profile;
import com.srt.model.Vote;

/**
 * <p>This class provide service layer to fetch, search or update profile details</p>
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

	/* 
	 * <p>This method fetches all profiles. The results are cacheable
	 * </p>
	 * (non-Javadoc)
	 * @see com.srt.service.profile.StudentProfile#searchUserProfiles(com.srt.model.profile.Student)
	 */
	@Override
	@Transactional
	public ProfileSearchResponse searchUserProfiles(ProfileSearchRequest request) throws ApplicationException {
		// TODO Auto-generated method stub
		ProfileSearchResponse response = new ProfileSearchResponse();
		DataTable table = new DataTable();
		
		int totalRecords = studentProfileDAO.getUserProfileCount(request.getStudent());
		table.setTotalRecords(totalRecords);
		
		List<Profile> studentList = studentProfileDAO.searchUserProfiles(request);
		
		response.setStudent(studentList);
		response.setDataTable(table);
		
		return response;
	}

	/* (non-Javadoc)
	 * @see com.srt.service.profile.StudentProfile#getUserProfile(java.lang.String)
	 */
	@Override
	public Profile getUserProfile(String rollNo) throws ApplicationException {
		// TODO Auto-generated method stub
		return studentProfileDAO.getUserProfile(rollNo);
	}

	/* (non-Javadoc)
	 * @see com.srt.service.profile.StudentProfile#getUserProfileCount(com.srt.model.profile.Student)
	 */
	@Override
	public int getUserProfileCount(Profile profile) throws ApplicationException{
		// TODO Auto-generated method stub
		return studentProfileDAO.getUserProfileCount(profile);
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
	public String updateProfile(Profile profile) throws ApplicationException {
		// TODO Auto-generated method stub
		return studentProfileDAO.updateProfile(profile);
	}

}
