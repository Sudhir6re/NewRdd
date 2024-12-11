package com.mahait.gov.in.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="country_mst",schema="public")
public class CountryMasterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@OneToMany(mappedBy="mstCountryEntity", fetch = FetchType.LAZY,   cascade = CascadeType.ALL)
	private Set<MstStateEntity> mstCountryEntity;
    
	@Column(name="COUNTRY_CODE")
	private Integer countryCode;
	
	@Column(name="COUNTRY_NAME_EN")
	private String countryNameEn;
	
	@Column(name="COUNTRY_NAME_mr")
	private String countryNameMr;
	
	@Column(name="COUNTRY_SHOT_NAME")
	private String countryShotName;
	
	@Column(name = "CREATED_USER_ID")
	private Integer createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "IS_ACTIVE")
	private Character isActive;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Set<MstStateEntity> getMstCountryEntity() {
		return mstCountryEntity;
	}

	public void setMstCountryEntity(Set<MstStateEntity> mstCountryEntity) {
		this.mstCountryEntity = mstCountryEntity;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryNameEn() {
		return countryNameEn;
	}

	public void setCountryNameEn(String countryNameEn) {
		this.countryNameEn = countryNameEn;
	}

	public String getCountryNameMr() {
		return countryNameMr;
	}

	public void setCountryNameMr(String countryNameMr) {
		this.countryNameMr = countryNameMr;
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

	public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public String getCountryShotName() {
		return countryShotName;
	}

	public void setCountryShotName(String countryShotName) {
		this.countryShotName = countryShotName;
	}
	
	


}
