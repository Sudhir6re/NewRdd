package com.mahait.gov.in.model;

public class FlagStatusModel {
	private int billStatusId;
	private int billStatusCode;
	private int isActive;
	private String billDescription;
	private String billName;
	public int getBillStatusId() {
		return billStatusId;
	}
	public void setBillStatusId(int billStatusId) {
		this.billStatusId = billStatusId;
	}
	public int getBillStatusCode() {
		return billStatusCode;
	}
	public void setBillStatusCode(int billStatusCode) {
		this.billStatusCode = billStatusCode;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getBillDescription() {
		return billDescription;
	}
	public void setBillDescription(String billDescription) {
		this.billDescription = billDescription;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	
	
}
