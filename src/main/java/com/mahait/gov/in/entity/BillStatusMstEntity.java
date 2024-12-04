package com.mahait.gov.in.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="BILL_STATUS_MST",schema="public")
public class BillStatusMstEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BILL_STATUS_ID")
	private Integer billStatusId;
	
	@Column(name="BILL_STATUS_CODE")
	private Integer billStatusCode;

	@Column(name="STATUS_NAME")
	private String  statusName;

	@Column(name="status_name_marathi")
	private String statusNameMarathi;
	
	@Column(name="status_description")
	private String statusDescription;
	
	@Column(name="is_active")
	private char isActive;
	
	@Column(name = "created_user_id")
	private int createdUserId;
	
	@Column(name = "created_date")
	private Date createdDate;

	public Integer getBillStatusId() {
		return billStatusId;
	}

	public void setBillStatusId(Integer billStatusId) {
		this.billStatusId = billStatusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusNameMarathi() {
		return statusNameMarathi;
	}

	public void setStatusNameMarathi(String statusNameMarathi) {
		this.statusNameMarathi = statusNameMarathi;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
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

	public Integer getBillStatusCode() {
		return billStatusCode;
	}

	public void setBillStatusCode(Integer billStatusCode) {
		this.billStatusCode = billStatusCode;
	}

	

}