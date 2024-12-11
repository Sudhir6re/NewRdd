package com.mahait.gov.in.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="department_allowdeduc_mpg_hst",schema="public")
public class MpgDdoAllowDeducHstEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPARTMENT_ALLOWDEDUC_MPG_HST_ID")
	private Integer department_allowdeduc_mpg_hst_id;
	
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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "with_effective_date")
	private Date wefDate;

	
}
