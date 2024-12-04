package com.mahait.gov.in.model;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.mahait.gov.in.entity.OrgPostMst;

import lombok.Data;

@Data
public class MstEmployeeModel {
	//Fields
	private Long employeeId;
	private String sevaarthId;
	private Character gender;
	private Long salutation;
	private String employeeFullNameEn;
	private String employeeFNameEn;
	private String employeeMNameEn;
	private String employeeLNameEn;
	private String employeeFullNameMr;
	private String employeeFNameMr;
	private String employeeMNameMr;
	private String employeeLNameMr;
	private String locality;
	private Long appointmentId;
	private Long qId;
	private String employeeMotherName;
	private Character maritalStatus;
	private Double sevenPcBasic;
	private Long yearId;
	private String secqualification;
	private String morequalification;
	private Long sevenPCLevel;
	
	

	private Long empMapped;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dob;
	private Date nomineeDOB;

	private Character bloodGroup;
	private Long mobileNo1;
	
	private Long mobileNo2;
	private String landlineNo;
	private String emailId;
	private Long religionCode;
	private String eidNo;
	private String uidNo;				
	private String uidNo1;
	private String uidNo2;
	private String uidNo3;
	private String panNo;
	private String address1;
	private String address2;
	private String address3;
	private Long pinCode;
	private Long villageCode;
	private String villageName;
	private Long talukaCode;
	private Long districtCode;
	private Long stateCode;
	private Long countryCode;
	private Date appointmentDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date doj;
	private Long userId;
	private Long cadreId;
	private Long empClass;
	private Long firstDesignationId;
	private Long designationId;
	private Long parentAdminDepartmentId;
	private Long parentFieldDepartmentId;
	private Long adminDepartmentId;
	private Long subCorporationId;
	
	private Long fieldDepartmentId;
	private Long currentOfficeId;
	private Long payCommissionCode;
	private Long PayScaleCode;
	private String payscaleDesc;
	private String bankBranchName;
	private String postName;
	private String cadreName;
	private Long orderNo;
	private Long gisApplId;
	private String billDesc;


	private Long payInPayBand;
	private String payscalelevelId;
//	private Long svnthpaybasic;
	private Long gradePay;
	private Long bankId;
	private String ifscCode;
	private String bankAcntNo;
	private Long bankBranchId;
	private String ddoCode;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date approvalByDdoDate;
	private Long billgroupId;
	private Double basicPay;
	private Long percentageOfBasic;
	private String headActCode;
	private Character employeeType;
	private Long isActive;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date empServiceEndDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date superAnnDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dtJoinCurrentPost;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date withEffectFromDate;
	private String gradeId;
	private String photoAttachmentId;
	private String signatureAttachmentId;
	private Long createdUserId;
	private Date createdDate;
	private Date updatedDate;
	private Long updatedUserId;
	private String payCommissionName;
	private Long giscatagory;
	
	//Extra
	private String employeeFullName;
	private String designationName;
	private String departmentNameEn;
	//End
	
    private String action;
    private String deptNm;
    private String subdeptNm;

	private String remark;
    private Long postdetailid;
    private String dcpsgpfflag;
    private String dcpsaccountmaintainby;
    private String pfacno;
    private String accountmaintainby;
    private String pfdescription;
    private String gisapplicable;
    private String gisgroup;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date membership_date;
    private String nomineename;
    private String nomineeaddress;
    private Long percent_share;
    private String relation;
    private String indiApproveOrderNo;
   
	private String strArrNomineeName;
    private String strArrAddress;
    private String strArrDob;
    private String strArrPercentShare;
    private String strArrRelationship;
    
	private Long dcpsid;
	private String dcpsno;
    private Long gisid;
    private Long gpf_id;
    private Long nomineeid;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date rdob;
    private String pfseries;
    private String imagePath;
    private String imagePathSign;
   // private String deptNm;
	private Long svnthpaybasic;
	private String physicallyHandicapped;
	private Long superannuationage;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dtInitialAppointmentParentInst;
	private String instName;
	private String insttelnoone;
	private String insttelnotwo;
	private String instemail;
	private Long payScaleId;
	private String cityClass;
	private Long acDcpsMaintainedBy;
	private String instituteAdd;
	
	private Long buckleNo;
	private Boolean isChangeParentDepartment;
	private String reasonForChngParentFieldDept;
	private String pranNo;
	private String empType;
	private String gisRemark;
	private String rltnOther;
	private String accMaintainedByOther;
	private Double hraBasic;
	private String begisCatg;
	private String isAllowOrDeduct;
	private Double totalGrossAmt;
	private Double totalNetAmt;
	private Double totalDedAmt;
	private String label;
	private String appointment;
	private String qualification;
	private String teaching;
	
	private String reasonForChangedtls;
	
	private List<MstNomineeDetailsModel> lstMstNomineeDetails;
	
	public List<MstEmployeeModel> mstEmployeeModelList=new ArrayList<>();
	
	
	
}
