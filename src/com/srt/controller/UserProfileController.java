package com.srt.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srt.exception.ApplicationException;
import com.srt.model.profile.ProfileSearchRequest;
import com.srt.model.profile.ProfileSearchResponse;
import com.srt.model.profile.Profile;
import com.srt.model.searchGrid.DataTable;
import com.srt.model.vote.Vote;
import com.srt.service.profile.StudentProfile;
import com.srt.util.ErrorResponseUtil;



/**
 * <p>This class is a REST controller to update, search profiles.
 * The implementation provides REST calls to search specific profile
 * or all profiles.
 * </p>
 *
 *<p>All methods of this call return response in JSON format</p>
 *
 * @author Ajay
 *
 */
@RestController
public class UserProfileController {

	private StudentProfile studentProfile;
	private static final Class clazz = UserProfileController.class;
	private static final Logger logger = LoggerFactory.getLogger(clazz);
	
	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}

	/**
	 * <p>This is a REST based method. This method list all profiles</p>
	 * 
	 * @param model
	 * @param rollNo
	 * @param firstName
	 * @param lastName
	 * @param sEcho
	 * @param iDisplayLength
	 * @param iDisplayStart
	 * @return
	 */
	@RequestMapping(value = "/search/profiles", method = RequestMethod.POST)
	public String getAllProfiles(Model model, 
			@RequestParam String rollNo,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String sEcho,
			@RequestParam int iDisplayLength,
			@RequestParam int iDisplayStart) {
		logger.debug("Start: getAllProfiles(): RollNo: {}, First Name: {}, Last Name: {}, sEcho: {}, iDisplayLength: {}, iDisplayStart: {}", rollNo,firstName,lastName,sEcho, iDisplayLength,iDisplayStart);
		JSONObject jsonResponse = new JSONObject();
		ProfileSearchResponse response = new ProfileSearchResponse();
		
		JSONArray data = new JSONArray();
		JSONArray row;
		
		Profile profile = new Profile();
		DataTable table = new DataTable();
		
		profile.setRollNo(rollNo.trim());
		profile.setFirstName(firstName.trim());
		profile.setLastName(lastName.trim());
		table.setiDisplayStart(iDisplayStart);
		table.setiDisplayLength(iDisplayLength);
		
		ProfileSearchRequest request = new ProfileSearchRequest();
		request.setStudent(profile);
		request.setTable(table);
		
		try {
			response = studentProfile.searchUserProfiles(request);			
			
			jsonResponse.put("sEcho", sEcho);
			
			if(null != response) {
				if(null != response.getStudent()) {
					List<Profile> profileList = response.getStudent();
					
					for(Profile st : profileList) {
						row = new JSONArray();
						row.put("");
						row.put(st.getFirstName());
						row.put(st.getLastName());
						row.put(st.getRollNo());
						row.put(st.getBranchName());
						row.put(st.getEmail());
						row.put(st.getPhone());
						row.put(st.getVotes());
						data.put(row);
					}
				}
				
				if(null != response.getDataTable()) {
					jsonResponse.put("iTotalRecords", response.getDataTable().getTotalRecords());
					jsonResponse.put("iTotalDisplayRecords", response.getDataTable().getTotalRecords());
				}
			}			
			
			jsonResponse.put("aaData", data);
		} catch (ApplicationException e) {
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);			
			return ErrorResponseUtil.getFailureResponse(e);
		}		
		
		logger.debug("End: getAllProfiles(): {}", jsonResponse);
		return jsonResponse.toString();
	}
	
	/**
	 * <p>This is a REST based method. It fetches profile of a user 
	 * based on Enrollment number</p>
	 * 
	 * @param rollNo
	 * @return
	 */
	@RequestMapping(value = "/profile/user/{rollNo}", method = RequestMethod.GET)
	public ResponseEntity<Profile> getUserProfile(@PathVariable("rollNo") String rollNo) {
		logger.debug("Start: getUserProfile(): Roll No: {}", rollNo);
		Profile profile = null;
			
		try {
			profile = studentProfile.getUserProfile(rollNo);
		} catch (ApplicationException e) {
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);
			return new ResponseEntity<Profile>(profile, HttpStatus.BAD_REQUEST);
		}
		logger.debug("End: getUserProfile(): ");
		return new ResponseEntity<Profile>(profile, HttpStatus.OK);
	}
	
	/**
	 * <p>This method is a REST based method which update profile of a user</p>
	 * 
	 * @param profile
	 * @return
	 */
	@RequestMapping(value = "/profile/update/{rollNo}", method = RequestMethod.POST)
	public String updateProfile(@PathVariable("rollNo") String rollNo, @RequestBody Profile profile) {
		logger.debug("Start: updateProfile(): Roll No: {}", rollNo);
		String response = "SUCCESS";;
		
		try {
			response = studentProfile.updateProfile(profile);
		} catch (ApplicationException e) {
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);
			return ErrorResponseUtil.getFailureResponse(e);
		}	
		logger.debug("End: updateProfile(): {}", response);
		return response;
	}
	
	/**
	 * <p>This method is a REST based method which updates vote count of a user.</p>
	 * 
	 * @param rollNo
	 * @param vote
	 * @return
	 */
	@RequestMapping(value = "/vote/update/{rollNo}", method = RequestMethod.POST)
	public String voteUpdate(@PathVariable("rollNo") String rollNo, @RequestBody Vote vote) {
		logger.debug("Start: voteUpdate(): {}", rollNo);
		vote.setRollNo(rollNo);
		String response = "SUCCESS";
		
		try {
			response = studentProfile.updateProfileVote(vote);
		} catch (ApplicationException e) {
			logger.debug("{}: Data Acess Exception :: {}", "SYS-ERR-1000", e);
			return ErrorResponseUtil.getFailureResponse(e);
		}	
		logger.debug("End: voteUpdate(): {}", response);
		return response;
	}
}
