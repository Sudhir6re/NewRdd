package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name="taluka_mst",schema="public")
public class MstTalukaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="TALUKA_ID_generator", sequenceName = "TALUKA_ID")  
	@Column(name="TALUKA_ID")
	private Integer TalukaId;
	
	@Column(name="country_code")
	private Integer countryCode;
	
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name="district_code")
	private Integer districtCode;
	
	@Column(name="taluka_code")
	private Integer talukaCode;
	
	@Column(name="taluka_name_en")
	private String talukaNameEn;
	
	@Column(name="taluka_name_mr")
	private String talukaNameMr;
	
	@Column(name = "is_active")
	private char isActive;
	
	@Column(name = "CREATED_USER_ID")
	private Integer createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	public Integer getTalukaId() {
		return TalukaId;
	}

	public void setTalukaId(Integer talukaId) {
		TalukaId = talukaId;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public Integer getTalukaCode() {
		return talukaCode;
	}

	public void setTalukaCode(Integer talukaCode) {
		this.talukaCode = talukaCode;
	}

	public String getTalukaNameEn() {
		return talukaNameEn;
	}

	public void setTalukaNameEn(String talukaNameEn) {
		this.talukaNameEn = talukaNameEn;
	}

	public String getTalukaNameMr() {
		return talukaNameMr;
	}

	public void setTalukaNameMr(String talukaNameMr) {
		this.talukaNameMr = talukaNameMr;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
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
