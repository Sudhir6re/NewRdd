package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name="bank_branch_mst",schema="public")
public class MstBankBranchEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = 'SUB_DEPARTMENT_ID')*/
	@Column(name="BANK_BRANCH_ID")
	private Long  bankBranchId;
	
	@Column(name="BANK_CODE")
	private Long bankCode;
	
	@Column(name="BANK_ID")
	private Long bankid;
	

	@Column(name="BANK_BRANCH_CODE")
	private Long bankBranchCode;
	
	
	@Column(name="BANK_BRANCH_SHORT_NAME")
	private String bankBranchShortName;
	
	@Column(name="BANK_BRANCH_NAME")
	private String bankBranchName;
	
	@Column(name="BANK_BRANCH_ADDRESS")
	private String bankBranchAddress;
	
	@Column(name="IFSC_CODE")
	private String ifscCode;
	
	@Column(name="MICR_CODE")
	private String micrCode;	
	
	@Column(name="CONTACT_NAME")
	private String contactName;
	
	@Column(name="MOBILE_NO")
	private Integer mobileNo;
	
	@Column(name="LANDLINE_NO")
	private Integer landlineNo;

	@Column(name="EMAIL_ID")
	private String emailId; 
	
	@Column(name = "IS_ACTIVE")
	private Character isActive;
	
	@Column(name = "CREATED_USER_ID")
	private Long createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Transient
	String bankName;



}
