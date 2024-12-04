package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="common_mst",schema="public")
public class MstCommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="common_id")
	private Integer commonId;

	@Column(name="common_value")
	private Integer commonValue;
	
	@Column(name="common_val_english")
	private String commonValEnglish;
	
	@Column(name="common_val_marathi")
	private String commonValMarathi;

	@Column(name="common_code")
	private String commonCode;
	
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


	public Integer getCommonId() {
		return commonId;
	}

	public void setCommonId(Integer commonId) {
		this.commonId = commonId;
	}

	public Integer getCommonValue() {
		return commonValue;
	}

	public void setCommonValue(Integer commonValue) {
		this.commonValue = commonValue;
	}

	public String getCommonValEnglish() {
		return commonValEnglish;
	}

	public void setCommonValEnglish(String commonValEnglish) {
		this.commonValEnglish = commonValEnglish;
	}

	public String getCommonValMarathi() {
		return commonValMarathi;
	}

	public void setCommonValMarathi(String commonValMarathi) {
		this.commonValMarathi = commonValMarathi;
	}

	public String getCommonCode() {
		return commonCode;
	}

	public void setCommonCode(String commonCode) {
		this.commonCode = commonCode;
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
