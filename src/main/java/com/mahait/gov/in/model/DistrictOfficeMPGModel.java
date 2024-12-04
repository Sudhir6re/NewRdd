package com.mahait.gov.in.model;

import java.io.Serializable;
import java.sql.Date;


public class DistrictOfficeMPGModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private int districtOfficeId;
    
    private int adminOfficeId;

    private String districtOfficeName;
    
    private String districtOfficeNameMr;
    
    private int createdUserId;
	
	private Date createdDate;
	
	private int updatedUserId;

	private Date updatedDate;

	public int getDistrictOfficeId() {
		return districtOfficeId;
	}

	public void setDistrictOfficeId(int districtOfficeId) {
		this.districtOfficeId = districtOfficeId;
	}

	public int getAdminOfficeId() {
		return adminOfficeId;
	}

	public void setAdminOfficeId(int adminOfficeId) {
		this.adminOfficeId = adminOfficeId;
	}

	public String getDistrictOfficeName() {
		return districtOfficeName;
	}

	public void setDistrictOfficeName(String districtOfficeName) {
		this.districtOfficeName = districtOfficeName;
	}

	public String getDistrictOfficeNameMr() {
		return districtOfficeNameMr;
	}

	public void setDistrictOfficeNameMr(String districtOfficeNameMr) {
		this.districtOfficeNameMr = districtOfficeNameMr;
	}

	public int getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(int updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
