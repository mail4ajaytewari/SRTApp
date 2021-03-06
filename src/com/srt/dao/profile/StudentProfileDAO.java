package com.srt.dao.profile;

import java.util.List;

import com.srt.exception.ApplicationException;
import com.srt.model.ProfileSearchRequest;
import com.srt.model.Profile;
import com.srt.model.Vote;

/**
 * <p>This interface provide method signature to perform operations
 * on user profile for DAO layer.</p>
 * @author Ajay
 *
 */
public interface StudentProfileDAO {
	public List<Profile> searchUserProfiles(ProfileSearchRequest request) throws ApplicationException;
	public Profile getUserProfile(String rollNo) throws ApplicationException;
	public int getUserProfileCount(Profile profile) throws ApplicationException;
	public String updateProfileVote(Vote vote) throws ApplicationException;
	public String updateProfile(Profile profile) throws ApplicationException;
}
