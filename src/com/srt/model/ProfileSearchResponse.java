package com.srt.model;

import java.util.List;

import com.srt.model.DataTable;

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
