package com.srt.model;

import com.srt.model.DataTable;

public class ProfileSearchRequest {
	private Profile profile;
	private DataTable table;
	public Profile getStudent() {
		return profile;
	}
	public void setStudent(Profile profile) {
		this.profile = profile;
	}
	public DataTable getTable() {
		return table;
	}
	public void setTable(DataTable table) {
		this.table = table;
	}
}
