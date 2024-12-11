package com.mahait.gov.in.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cadre_mst", schema = "public")
public class MstCadreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CADRE_ID")
	private Integer cadreId;

	@Column(name = "GROUP_ID")
	private Integer cadreGroup;

	@Column(name = "CADRE_CODE")
	private Integer cadreCode;

	@Column(name = "CADRE_NAME")
	private String cadreDescription;

	@Column(name = "MINISTERIAL_FLAG")
	private String whetherMinisterial;

	@Column(name = "SUPERANNUATION_AGE")
	private BigDecimal superAnnuationAge;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "IS_ACTIVE")
	private Character isActive;

	@Column(name = "CREATED_USER_ID")
	private BigInteger createdUserId;

	public BigInteger getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(BigInteger createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Integer getCadreId() {
		return cadreId;
	}

	public void setCadreId(Integer cadreId) {
		this.cadreId = cadreId;
	}

	/*
	 * public Integer getFieldDepartmrnt() { return fieldDepartmrnt; }
	 * 
	 * public void setFieldDepartmrnt(Integer fieldDepartmrnt) {
	 * this.fieldDepartmrnt = fieldDepartmrnt; }
	 */

	public Integer getCadreGroup() {
		return cadreGroup;
	}

	public void setCadreGroup(Integer cadreGroup) {
		this.cadreGroup = cadreGroup;
	}

	public Integer getCadreCode() {
		return cadreCode;
	}

	public void setCadreCode(Integer cadreCode) {
		this.cadreCode = cadreCode;
	}

	public String getCadreDescription() {
		return cadreDescription;
	}

	public void setCadreDescription(String cadreDescription) {
		this.cadreDescription = cadreDescription;
	}

	public String getWhetherMinisterial() {
		return whetherMinisterial;
	}

	public void setWhetherMinisterial(String whetherMinisterial) {
		this.whetherMinisterial = whetherMinisterial;
	}

	public BigDecimal getSuperAnnuationAge() {
		return superAnnuationAge;
	}

	public void setSuperAnnuationAge(BigDecimal superAnnuationAge) {
		this.superAnnuationAge = superAnnuationAge;
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
}
