package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="department_allowdeduc_mpg",schema="public")
public class MpgDdoAllowDeducEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPARTMENT_ALLOWDEDUC_MPG_ID")
	private Integer department_allowdeduc_mpg_id;
	
	@Column(name = "DEPARTMENT_CODE")
	private Integer department_code;
	
	@Column(name = "DEPARTMENT_ALLOWDEDUC_CODE")
	private Integer department_allowdeduc_code;
	
	@Column(name = "IS_ACTIVE")
	private Character isActive;
	
	@Column(name = "UPDATED_USER_ID")
	private Character updatedUserId;
	
	@Column(name = "CREATED_USER_ID")
	private Integer createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "DDO_CODE")
	private String ddoCode;
	
	@Column(name = "LOCATION_CODE")
	private Long locCode;

	
}
