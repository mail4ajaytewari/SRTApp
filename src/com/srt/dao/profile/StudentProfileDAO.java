package com.srt.dao.profile;

import java.util.List;

import com.srt.exception.ApplicationException;
import com.srt.model.profile.ProfileSearchRequest;
import com.srt.model.profile.Profile;
import com.srt.model.searchGrid.DataTable;
import com.srt.model.vote.Vote;

public interface StudentProfileDAO {
	public List<Profile> searchUserProfiles(ProfileSearchRequest request) throws ApplicationException;
	public Profile getUserProfile(String rollNo) throws ApplicationException;
	public int getUserProfileCount(Profile profile) throws ApplicationException;
	public String updateProfileVote(Vote vote) throws ApplicationException;
	public String updateProfile(Profile profile) throws ApplicationException;
}
