package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="religion_mst",schema="public")
public class ReligionMstEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="religion_id")
	private Integer religionId;
	
	@Column(name="religion_name_english")
	private String religionNameEnglish;
	
	@Column(name="religion_name_marathi")
	private String religionNameMarathi;
	
	@Column(name = "is_active")
	private Character isActive;
	
	@Column(name = "CREATED_USER_ID")
	private Integer createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	public Integer getReligionId() {
		return religionId;
	}

	public void setReligionId(Integer religionId) {
		this.religionId = religionId;
	}

	public String getReligionNameEnglish() {
		return religionNameEnglish;
	}

	public void setReligionNameEnglish(String religionNameEnglish) {
		this.religionNameEnglish = religionNameEnglish;
	}

	public String getReligionNameMarathi() {
		return religionNameMarathi;
	}

	public void setReligionNameMarathi(String religionNameMarathi) {
		this.religionNameMarathi = religionNameMarathi;
	}

	public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Integer updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	


}
