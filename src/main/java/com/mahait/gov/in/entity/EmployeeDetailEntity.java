package com.mahait.gov.in.entity;



import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_details_no_approval")
public class EmployeeDetailEntity implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;

	@Column(name = "SEVAARTH_ID")
	private String sevaarthId;


	@Column(name = "GENDER")
	private Character gender;

	@Column(name = "SALUTATION")
	private Long salutation;

	@Column(name = "EMPLOYEE_FULL_NAME_EN")
	private String employeeFullNameEn;

	@Column(name = "EMPLOYEE_F_NAME_EN")
	private String employeeFNameEn;

	@Column(name = "EMPLOYEE_M_NAME_EN")
	private String employeeMNameEn;

	@Column(name = "EMPLOYEE_L_NAME_EN")
	private String employeeLNameEn;


	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DOB")
	private Date dob;
	

	@Column(name = "MOBILE_NO1")
	private Long mobileNo1;

	@Column(name = "MOBILE_NO2")
	private Long mobileNo2;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	
	@Column(name = "EID_NO")
	private String eidNo;
	
	

	@Column(name = "UID_NO")
	private String uidNo;
	
	

	@Column(name = "PAN_NO")
	private String panNo;

	@Column(name = "ADDRESS1")
	private String address1;

	@Column(name = "ADDRESS2")
	private String address2;

	@Column(name = "ADDRESS3")
	private String address3;

	@Column(name = "PINCODE")
	private Long pinCode;
	

	@Column(name = "DISTRICT_CODE")
	private Long districtCode;


	@Column(name = "STATE_CODE")
	private Long stateCode;

	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "appointment_date")
	private Date dtInitialAppointmentParentInst;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DOJ")
	private Date doj;

	@Column(name = "USER_ID")
	private Long userId;
	

	@Column(name = "CADRE_CODE")
	private Long cadreCode;


	@Column(name = "FIRST_DESIGNATION_CODE")
	private Long firstDesignationCode;

	
	@Column(name = "DESIGNATION_CODE")
	private Long designationCode;

/*	@Column(name = "PARENT_ADMIN_DEPARTMENT_CODE")
	private Long parentAdminDepartmentCode;

	@Column(name = "PARENT_FIELD_DEPARTMENT_CODE")
	private Long parentFieldDepartmentCode;

	@Column(name = "ADMIN_DEPARTMENT_CODE")
	private Long adminDepartmentCode;

	@Column(name = "SUB_CORPORATION_CODE")
	private Long subCorporationId;*/

	@Column(name = "FIELD_DEPARTMENT_CODE")
	private Long fieldDepartmentCode;
	
	@Column(name = "PAY_SCALE_CODE")
	private Long payScaleCode;
	

	@Column(name = "PAY_IN_PAY_BAND")
	private Long payInPayBand;
	
	
	@Column(name = "GRADE_PAY")
	private Long gradePay;

	@Column(name = "BANK_CODE")
	private Long bankCode;

	@Column(name = "IFSC_CODE")
	private String ifscCode;

	@Column(name = "BANK_ACNT_NO")
	private Long bankAcntNo;

	@Column(name = "BANK_BRANCH_CODE")
	private Long bankBranchCode;



	@Column(name = "DDO_CODE")
	private String ddoCode;

	@Column(name = "APPROVAL_BY_DDO_DATE")
	private Date approvalByDdoDate;

	@Column(name = "BILLGROUP_ID")
	private Long billGroupId;

	@Column(name = "BASIC_PAY")
	private Double basicPay;

	@Column(name = "PERCENTAGE_OF_BASIC")
	private Long percentageOfBasic;
	

	@Column(name = "HEAD_ACT_CODE")
	private String headActCode;
	
	@Column(name = "EMPLOYEE_TYPE")
	private Character employeeType;

	@Column(name = "IS_ACTIVE")
	private Long isActive;

	@Column(name = "EMP_SERVICE_END_DATE")
	private Date empServiceEndDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "SUPER_ANN_DATE")
	private Date superAnnDate;

	@Column(name = "WITH_EFFECT_FROM_DATE")
	private Date withEffectFromDate;

	@Column(name = "GRADE_ID")
	private String gradeId;

	@Column(name = "PHOTO_ATTACHMENT_ID")
	private String photoAttachmentId;

	@Column(name = "SIGNATURE_ATTACHMENT_ID")
	private String signatureAttachmentId;
	
	

	@Column(name = "CREATED_USER_ID")
	private Long createdUserId;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "UPDATED_USER_ID")
	private Long updatedUserId;
	
	@Column(name = "PAY_SCALE_LEVEL_ID")
	private String payscalelevelId;
	
	@Column(name = "post_detail_id")
	private Long postdetailid;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "state_matrix_7pc_id")
	private Long svnthpaybasic;

	@Column(name = "pyhical_handicapped")
	private String physicallyHandicapped;


	@Column(name = "dcps_gpf_flag")
	private String dcpsgpfflag;
	

	@Column(name = "pran_no")
	private String pranNo;
	
	@Column(name = "pran_status")
	private String pranStatus;
	
	
	@Column(name = "pran_remarks")
	private String pranRemarks;
	

	@Column(name = "PAY_COMMISSION_CODE")
	private Long payCommissionCode;

	
	@Column(name = "CURRENT_OFFICE_CODE")
	private Long currentOfficeCode;
	
	
	@Column(name = "pay_scale_id_old")
	private Long payScaleIdOld;
	
	
	@Column(name = "billgroup_id_old")
	private Long billgroupIdOld;
	
	

	@Column(name = "seven_pc_basic")
	private Double sevenPcBasic;


	@Column(name = "seven_pc_level")
	private Long sevenPcLevel;
	
	@Column(name = "pay_scale_id")
	private Long payScaleId;
	
	
	@Column(name = "ac_dcps_maintained_by ")
	private Long acDcpsMaintedBy;
	


	@Column(name = "buckle_no")
	private Long buckleNo;

	
	
	@Column(name = "emp_type")
	private String empType;

	
	
	@Column(name = "reason_for_change_parent_field_dept ")
	private String reasonForChngParentFieldDept;
	
	
	@Column(name = "city_class")
	private String cityClass;
	
	

	@Column(name = "REASON_FOR_CHANGEDTLS")
	private String reasonForChangedtls;
	
	@Column(name = "EMP_CLASS")
	private Long empClass;


	@Column(name = "accMaintainedByOther")
	private String accMaintainedByOther;
	

	@Column(name = "locality")
	private String locality;
	
	@Column(name = "instName")
	private String instName;
	
	
	@Column(name = "Inst_email")
	private String instemail;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "curr_post_joining_date")
	private Date currPostJoiningDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DtJoinCurrentPost")
	private Date dtJoinCurrentPost;
	
	
	@Column(name = "accountmaintainby")
	private String accountmaintainby;

	@Column(name = "dcpsaccountmaintainby")
	private String dcpsaccountmaintainby;


	@Column(name = "gisapplicable")
	private String gisapplicable;

	
	@Column(name = "gisgroup")
	private String gisgroup;


	@Column(name = "indiApproveOrderNo")
	private String indiApproveOrderNo;

	
	

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "membership_date")
	private Date membership_date;


	@Column(name = "pfacno")
	private String pfacno;
	
	@Column(name = "pfdescription")
	private String pfdescription;
	

	@Column(name = "pfseries")
	private String pfseries;
	
	
	@Column(name = "dcps_no")
	private String dcpsNo;

	@Column(name = "headOfAccCode")
	private String headOfAccCode;
	
	@Column(name = "APPOINTMENT")
	private String appointment;
	
	
	@Column(name = "EMP_TYPES")
	private String teaching;
	
	@Column(name = "SEC_QUALIFICATION")
	private String secqualification;
	
	
	@Column(name = "MORE_QUALIFICATION")
	private String morequalification;

	
}
