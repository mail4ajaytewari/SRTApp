package com.srt.service.profile;

import com.srt.exception.ApplicationException;
import com.srt.model.profile.ProfileSearchRequest;
import com.srt.model.profile.ProfileSearchResponse;
import com.srt.model.vote.Vote;
import com.srt.model.profile.Profile;

public interface StudentProfile {
	public ProfileSearchResponse searchUserProfiles(ProfileSearchRequest request) throws ApplicationException;
	public Profile getUserProfile(String rollNo) throws ApplicationException;
	public int getUserProfileCount(Profile profile) throws ApplicationException;
	public String updateProfileVote(Vote vote) throws ApplicationException;
	public String updateProfile(Profile profile) throws ApplicationException;
}
