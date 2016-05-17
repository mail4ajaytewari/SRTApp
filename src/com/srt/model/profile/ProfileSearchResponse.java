package com.srt.model.profile;

import java.util.List;

import com.srt.model.searchGrid.DataTable;

public class ProfileSearchResponse {
	private DataTable dataTable;
	private List<Profile> profile;
	
	public DataTable getDataTable() {
		return dataTable;
	}
	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}
	public List<Profile> getStudent() {
		return profile;
	}
	public void setStudent(List<Profile> profile) {
		this.profile = profile;
	}

}
