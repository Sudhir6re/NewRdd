package com.mahait.gov.in.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Central_Govt_DA_MST",schema="public")
public class CentralGovtDAMasterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Central_Govt_DA_MST_ID")
	private Integer centralGovtDAId;
	
	@Column(name="department_allowdeduc_code")
	private Integer departmentAllowdeducCode;

	@Column(name="IS_TYPE")
	private Integer isType;
	
	@Column(name="AMOUNT",length =13,precision=2)
	private Double amount;
	
	@Column(name="PERCENTAGE")
	private Integer percentage;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="START_DATE")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="END_DATE")
	private Date endDate;
	
	@Column(name="pay_commission_code")
	private Integer payCommissionCode;
	
	@Column(name = "created_user_id")
	private int createdUserId;
	
	@Column(name = "updated_user_id")
	private int updatedUserId;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	
	@Column(name = "is_active")
	private char isActive;

	
	
	private transient String commisionName;
	private transient String departmentAlloDeducName;
	
	
	
	

	public String getCommisionName() {
		return commisionName;
	}


	public void setCommisionName(String commisionName) {
		this.commisionName = commisionName;
	}


	public String getDepartmentAlloDeducName() {
		return departmentAlloDeducName;
	}


	public void setDepartmentAlloDeducName(String departmentAlloDeducName) {
		this.departmentAlloDeducName = departmentAlloDeducName;
	}


	public Integer getCentralGovtDAId() {
		return centralGovtDAId;
	}


	public void setCentralGovtDAId(Integer centralGovtDAId) {
		this.centralGovtDAId = centralGovtDAId;
	}


	public Integer getDepartmentAllowdeducCode() {
		return departmentAllowdeducCode;
	}


	public void setDepartmentAllowdeducCode(Integer departmentAllowdeducCode) {
		this.departmentAllowdeducCode = departmentAllowdeducCode;
	}


	public Integer getIsType() {
		return isType;
	}


	public void setIsType(Integer isType) {
		this.isType = isType;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Integer getPercentage() {
		return percentage;
	}


	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Integer getPayCommissionCode() {
		return payCommissionCode;
	}


	public void setPayCommissionCode(Integer payCommissionCode) {
		this.payCommissionCode = payCommissionCode;
	}


	public int getCreatedUserId() {
		return createdUserId;
	}


	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}


	public int getUpdatedUserId() {
		return updatedUserId;
	}


	public void setUpdatedUserId(int updatedUserId) {
		this.updatedUserId = updatedUserId;
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


	public char getIsActive() {
		return isActive;
	}


	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	
	
	
}
