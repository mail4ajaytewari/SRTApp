package com.srt.model;

/**
 * <p>This model class represent data table for pagination</p>
 * @author Ajay
 *
 */
public class DataTable {
	private int iDisplayStart;
	private int iDisplayLength;
	private int totalRecords;
	
	
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
}
