package com.mahait.gov.in.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class NewRegDDOModel {
	
	
	public String sevaarthId;
	private Integer employeeId;
	private String empName;
	private boolean checkboxid;
	private String ddoCode;
	private String lvl1ddoCode;
	private String lvl2ddoCode;
	private String lvl3ddoCode;
	private String lvl1ddoName;
	private String lvl2ddoName;
	private String officeName;
	private String stateId;
	private String talukaId;
	private String districtId;
	private String town;
	private Date dob;
	private char gender;
	private String desgName;
	private String village;
	private String address;
	private String cityClass;
	private String fax;
	private String subSchemeCode;
	private String schemeCode;
	private String schemeName;
	private String cmbsubDdos;
	private String email;
	private Long instNo;
	private Long pincode;
	private Long telNo;
	private Long mobNo;
	private Long dcpsDdoOfficeMstId;
	private Integer perofGrant;
	private String instDdoCode;
	private String instSchemeCode;
	private String status;
	private String cityName;
	private BigInteger cityId;
	private Long instituteType;
	private Long designationId;
	private String bankName;

	private String branchName;
	
	
    private String bankPassbook;
    
    private String  bankCheaque;
    
    private String deptLetter;
	
	
	
	public List<NewRegDDOModel> emplist=new ArrayList<>();

}
