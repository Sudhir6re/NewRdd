package com.mahait.gov.in.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class OrganisationDtlsModel {

	private String ddoCode;
	private String officeName;
	private String stateId;
	private String districtId;
	private String talukaId;
	private String city;
	private String village;
	private String Address;
	private Long pin;
	private String cityClass;
	private String instiNo;
	private String PercGrant;
	private Long tel1;
	private Long tel2;
	private String email;
	private Long fax;
	private String cityId;

	private Long instituteType;
	private String ddoOffice;

	private String bankId;
	private String parentAdminDepartmentId;
	private String ParentFieldDepartmentId;
	private String ItoWardCircle;
	private String designationId;
	private String bankCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	private String tanNo;

	private String itaWardNo;

	private String bankName;

	private String branchName;

	private String accountNo;

	private String ifscCode;
	private String Remarks;

	private String bankPassbook;

	private String bankCheaque;

	private String deptLetter;

    private String deptLocCode;
    
    private String hodLocCode;
    
    private String adminDeptName;
    
    private String fieldDeptName;
    
    private String ddoPersonalName;
    
    
    
	
	
}
