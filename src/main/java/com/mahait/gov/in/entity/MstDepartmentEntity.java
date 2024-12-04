/**
 * 
 */
package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author parvez
 *
 */
@Entity
@Table(name="DEPARTMENT_MST",schema="public")
public class MstDepartmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DEPARTMENT_ID")
    private Integer departmentId;  
	
	
//	@OneToMany(mappedBy="mstDepartmentEntity", fetch = FetchType.LAZY,   cascade = CascadeType.ALL)
//    private Set<MstSubDepartmentEntity> mstDepartmentEntity;
    
//	public Set<MstSubDepartmentEntity> getMstDepartmentEntity() {
//		return mstDepartmentEntity;
//	}
//
//	public void setMstDepartmentEntity(Set<MstSubDepartmentEntity> mstDepartmentEntity) {
//		this.mstDepartmentEntity = mstDepartmentEntity;
//	}
	
	@Column(name="DEPARTMENT_CODE")
    private Integer departmentCode;
	
	@Column(name="DEPARTMENT_NAME_EN")
	private String departmentNameEn;
	
	@Column(name = "DEPARTMENT_NAME_MR")
	private String departmentNameMr;
	
	@Column(name = "DEPARTMENT_SHORT_NAME")
	private String departmentShortName;
	
	@Column(name = "IS_ACTIVE")
	private Character isActive;
	
	@Column(name = "CREATED_USER_ID")
	private Integer createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
    
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(Integer departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentNameEn() {
		return departmentNameEn;
	}

	public void setDepartmentNameEn(String departmentNameEn) {
		this.departmentNameEn = departmentNameEn;
	}

	public String getDepartmentNameMr() {
		return departmentNameMr;
	}

	public void setDepartmentNameMr(String departmentNameMr) {
		this.departmentNameMr = departmentNameMr;
	}

	public String getDepartmentShortName() {
		return departmentShortName;
	}

	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
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
