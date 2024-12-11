package com.mahait.gov.in.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
//@JsonIgnoreProperties(value = { "mstNomineeDetailsEntity", "mstSubDepartmentEntity", "mstGpfDetailsEntity" })
//@Entity
//@Table(name = "Change_details_mst", schema = "public")
public class ChangeDtlsHst {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mstEmployeeEntity", orphanRemoval = true)
	private List<MstNomineeDetailsEntity> mstNomineeDetailsEntity;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "mstEmployeeEntity", orphanRemoval = true)
	private MstGpfDetailsEntity mstGpfDetailsEntity;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHANGE_DTLS_ID")
	private Integer changeDtlsId;

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

	@Column(name = "gis_remark")
	private String GisRemark;

	@Column(name = "EMPLOYEE_M_NAME_EN")
	private String employeeMNameEn;

	@Column(name = "EMPLOYEE_L_NAME_EN")
	private String employeeLNameEn;

	@Column(name = "EMPLOYEE_FULL_NAME_MR")
	private String employeeFullNameMr;

	@Column(name = "EMPLOYEE_F_NAME_MR")
	private String employeeFNameMr;

	@Column(name = "EMPLOYEE_M_NAME_MR")
	private String employeeMNameMr;

	@Column(name = "EMPLOYEE_L_NAME_MR")
	private String employeeLNameMr;

	@Column(name = "EMPLOYEE_MOTHER_NAME")
	private String employeeMotherName;

	@Column(name = "MARITAL_STATUS")
	private Character maritalStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DOB")
	private Date dob;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "membership_date")
	private Date membership_date;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DtJoinCurrentPost")
	private Date dtJoinCurrentPost;

	@Column(name = "BLOOD_GROUP")
	private Character bloodGroup;

	@Column(name = "MOBILE_NO1")
	private Long mobileNo1;

	@Column(name = "MOBILE_NO2")
	private Long mobileNo2;

	@Column(name = "LANDLINE_NO")
	private String landlineNo;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "Inst_email")
	private String instemail;

	@Column(name = "RELIGION_CODE")
	private Long religionCode;

	@Column(name = "GISCATAHORY")
	private Long giscatagory;

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

	@Column(name = "VILLAGE_CODE")
	private Long villageCode;

	@Column(name = "VILLAGE_NAME")
	private String villageName;

	@Column(name = "TALUKA_CODE")
	private Long talukaCode;

	@Column(name = "DISTRICT_CODE")
	private Long districtCode;

	@Column(name = "sub_department_id")
	private Long subDeptId;

	@Column(name = "STATE_CODE")
	private Long stateCode;

	@Column(name = "COUNTRY_CODE")
	private Long countryCode;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DOJ")
	private Date doj;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "CADRE_CODE")
	private Long cadreCode;

	@Column(name = "EMP_CLASS")
	private Long empClass;

	@Column(name = "FIRST_DESIGNATION_CODE")
	private Long firstDesignationCode;

	@Column(name = "DESIGNATION_CODE")
	private Long designationCode;

	@Column(name = "PARENT_ADMIN_DEPARTMENT_CODE")
	private Long parentAdminDepartmentCode;

	@Column(name = "PARENT_FIELD_DEPARTMENT_CODE")
	private Long parentFieldDepartmentCode;

	@Column(name = "ADMIN_DEPARTMENT_CODE")
	private Long adminDepartmentCode;

	@Column(name = "SUB_CORPORATION_CODE")
	private Long subCorporationId;

	@Column(name = "FIELD_DEPARTMENT_CODE")
	private Long fieldDepartmentCode;

	@Column(name = "CURRENT_OFFICE_CODE")
	private Long currentOfficeCode;

	@Column(name = "PAY_COMMISSION_CODE")
	private Long payCommissionCode;

	@Column(name = "PAY_SCALE_CODE")
	private Long payScaleCode;

	@Column(name = "IsApplicableforBeams", length = 1)
	private Character IsApplicableforBeams;

	@Column(name = "REASON_FOR_CHANGEDTLS")
	private String reasonForChangedtls;

	@Column(name = "instituteAdd")
	private String instituteAdd;

	@Column(name = "departmentNameEn")
	private String departmentNameEn;

	@Column(name = "instName")
	private String instName;

	@Column(name = "pfacno")
	private String pfacno;

	@Column(name = "pfdescription")
	private String pfdescription;

	@Column(name = "gisapplicable")
	private String gisapplicable;

	@Column(name = "gisgroup")
	private String gisgroup;

	@Column(name = "dcpsaccountmaintainby")
	private String dcpsaccountmaintainby;

	@Column(name = "accountmaintainby")
	private String accountmaintainby;

	@Column(name = "indiApproveOrderNo")
	private String indiApproveOrderNo;

	@Column(name = "pfseries")
	private String pfseries;

	@Column(name = "dcps_no")
	private String dcpsNo;

	@Column(name = "isgpfflag")
	private Long isgpfflag;

	@Column(name = "crtId")
	private Long crtId;

	@Column(name = "headOfAccCode")
	private String headOfAccCode;

	@Transient
	private String employeeBirthPlace;

	@Transient
	private String nameOfPostDesg;

	@Transient
	private String ppanNo;
	@Transient
	private String flatUnitNo;
	@Transient
	private String employeeFatherHubandName;
	@Transient
	private String employeeSpouseName;
	@Transient
	private String employeeBankPinCode;
	@Transient
	private String buildingName;

	@Transient
	private String empPermanentFlatUnitNo;
	@Transient
	private String empPermanentBuildingName;
	@Transient
	private String empPermanentLocality;
	@Transient
	private String empPermanentDistrict;
	@Transient
	private String empPermanentState;
	@Transient
	private String empPermanentCountry;
	@Transient
	private String empPermanentPinCode;
	@Transient
	private String empNominee1GuardName;
	@Transient
	private String empNominee1InvalidCondn;
	@Transient
	private String empNominee2GuardName;
	@Transient
	private String empNominee2InvalidCondn;
	@Transient
	private String empNominee3GuardName;
	@Transient
	private String empNominee3InvalidCondn;
	@Transient
	private String NSDLStatus;
	@Transient
	private String USPerson;
	@Transient
	private String countryofTax;
	@Transient
	private String addressOfTax;
	@Transient
	private String cityOfTax;
	@Transient
	private String stateofTax;
	@Transient
	private String postCodeofTax;
	@Transient
	private String tinOrPan;
	@Transient
	private String state;;
	@Transient
	private String country;
	@Transient
	private String district;
	@Transient
	private String empSTDCode;
	@Transient
	private String empPhoneNo;
	@Transient
	private String employeeBankName;
	@Transient
	private String employeeBankBranchName;
	@Transient
	private String employeeBankBankAddress;
	@Transient
	private MultipartFile signatureAttachmentIdnew;
	@Transient
	private MultipartFile photoAttachmentIdnew;
	@Transient
	private Long displayNameonPranCard;
	@Transient
	private Long dobProof;
	@Transient
	private Long eduQual;
	@Transient
	private Long incomeRange;
	@Transient
	private String uidNo1;
	@Transient
	private String uidNo2;
	@Transient
	private Long superAnnAge;

	@Column(name = "accMaintainedByOther")
	private String accMaintainedByOther;

	@Transient
	private String uidNo3;

	@Column(name = "PAY_IN_PAY_BAND")
	private Long payInPayBand;

	@Column(name = "GRADE_PAY")
	private Long gradePay;

	@Column(name = "BANK_CODE")
	private Long bankCode;

	@Column(name = "IFSC_CODE")
	private String ifscCode;

	@Column(name = "BANK_ACNT_NO")
	private String bankAcntNo;

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
	private Date gradeId;

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

	@Column(name = "locality")
	private String locality;

	@Column(name = "seven_pc_basic")
	private Double sevenPcBasic;

	@Column(name = "remark")
	private String remark;

	@Column(name = "post_detail_id")
	private Long postdetailid;

	@Column(name = "dcps_gpf_flag")
	private String dcpsgpfflag;

	@Column(name = "state_matrix_7pc_id")
	private Long svnthpaybasic;

	@Column(name = "pyhical_handicapped")
	private String physicallyHandicapped;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "appointment_date")
	private Date dtInitialAppointmentParentInst;

	@Column(name = "seven_pc_level")
	private Long sevenPcLevel;

	@Column(name = "updated_basic_date")
	private Date updatedBasicDate;

	@Column(name = "updated_basic_user_id")
	private Long updatedBasicUserId;

	@Column(name = "updated_basic_percentage")
	private Long updatedBasicPercent;

	@Column(name = "updated_basic_witheff_date")
	private Date updatedBasicwitheffDate;

	@Column(name = "revised_basic")
	private Double revisedBasic;

	@Column(name = "relieving_date")
	private Date relievingDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "new_joining_date")
	private Date newJoiningDate;

	@Column(name = "ac_dcps_maintained_by ")
	private Long acDcpsMaintedBy;

	@Column(name = "buckle_no")
	private Long buckleNo;

	@Column(name = "is_changeParent_department")
	private Boolean isChangeParentDepartment;

	@Column(name = "reason_for_change_parent_field_dept ")
	private String reasonForChngParentFieldDept;

	@Column(name = "pran_no ")
	private String pranNo;

	@Column(name = "emp_type")
	private String empType;

	@Column(name = "reason_for_rejectorapproved")
	private String reasonForRejOrApprv;

	@Column(name = "is_mapped_with_nps")
	private Character isMappedWithNps;

	@Column(name = "hra_basic")
	private Double hraBasic;

	@Column(name = "BEGIS_CATG")
	private String begisCatg;

	@Column(name = "joining_relieving_reason")
	private String joiningRelievingReason;

	@Column(name = "city_class")
	private String cityClass;

	@Column(name = "GID_ID")
	private Long gisId;

	@Column(name = "pay_scal_desc")
	private String payScaleDesc;

	@Column(name = "HOD_lOC_CODE")
	private Long parentFieldDepartmentId;

	@Column(name = "QUALIFICATION")
	private String qualification;

	@Column(name = "APPOINTMENT")
	private String appointment;

	@Column(name = "EMP_TYPES")
	private String teaching;

	@Column(name = "SEC_QUALIFICATION")
	private String secqualification;

	@Column(name = "MORE_QUALIFICATION")
	private String morequalification;
}
