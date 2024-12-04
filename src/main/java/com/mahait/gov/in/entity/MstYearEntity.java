package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="YEAR_MST",schema="public")
public class MstYearEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="YEAR_ID")
	private Integer yearId;
	
	

	@Column(name="YEAR_ENGLISH")
	private String yearEnglish;
	
	@Column(name="YEAR_MARATHI")
	private String yearMarathi;
	
	@Column(name="from_year")
	private String from_year;
	
	@Column(name="to_year")
	private String to_year;
	
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
	
	@Column(name="FINANCIAL_YEAR")
	private String financialYear;
	
	
	/*@OneToMany(mappedBy = "YEAR_ID", cascade = {
	        CascadeType.ALL
	    })
	private List<MstSchemeEntity> mstSchemeEntity = new ArrayList<>();

	public List<MstSchemeEntity> getMstSchemeEntity() {
		return mstSchemeEntity;
	}

	public void setMstSchemeEntity(List<MstSchemeEntity> mstSchemeEntity) {
		this.mstSchemeEntity = mstSchemeEntity;
	}*/

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public Integer getYearId() {
		return yearId;
	}

	public void setYearId(Integer yearId) {
		this.yearId = yearId;
	}

	public String getYearEnglish() {
		return yearEnglish;
	}

	public void setYearEnglish(String yearEnglish) {
		this.yearEnglish = yearEnglish;
	}

	public String getYearMarathi() {
		return yearMarathi;
	}

	public void setYearMarathi(String yearMarathi) {
		this.yearMarathi = yearMarathi;
	}

	
	public String getFrom_year() {
		return from_year;
	}

	public void setFrom_year(String from_year) {
		this.from_year = from_year;
	}

	public String getTo_year() {
		return to_year;
	}

	public void setTo_year(String to_year) {
		this.to_year = to_year;
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
