package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/*@Entity
@Table(name="admin_office_mst",schema="public")*/
public class MstAdminOffice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ADMIN_OFFICE_ID")
    private int adminOfficeId;  
	
	@Column(name="ADMIN_OFFICE_CODE1")
    private String adminOfficeCode;
	
	@Column(name="ADMIN_OFFICE_NAME")
	private String adminOfficeName;
	
	@Column(name = "ADMIN_OFFICE_NAME_MH")
	private String adminOfficeNameMh;
	
	@Column(name = "CREATED_USER_ID")
	private Integer createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	public int getAdminOfficeId() {
		return adminOfficeId;
	}

	public void setAdminOfficeId(int adminOfficeId) {
		this.adminOfficeId = adminOfficeId;
	}

	public String getAdminOfficeCode() {
		return adminOfficeCode;
	}

	public void setAdminOfficeCode(String adminOfficeCode) {
		this.adminOfficeCode = adminOfficeCode;
	}

	public String getAdminOfficeName() {
		return adminOfficeName;
	}

	public void setAdminOfficeName(String adminOfficeName) {
		this.adminOfficeName = adminOfficeName;
	}

	public String getAdminOfficeNameMh() {
		return adminOfficeNameMh;
	}

	public void setAdminOfficeNameMh(String adminOfficeNameMh) {
		this.adminOfficeNameMh = adminOfficeNameMh;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Integer getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Integer updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	
	
}
