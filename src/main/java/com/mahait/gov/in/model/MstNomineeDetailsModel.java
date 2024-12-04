package com.mahait.gov.in.model;

import java.util.Date;

import lombok.Data;
@Data
public class MstNomineeDetailsModel {


	private Long nomineeid;
	
	private Long employeeId;

	private String relation;
	
	private String nomineename;
	
	private String nomineeaddress;
	
	private String dob;
	
	private Integer percent_share;
		private String isactive;
	
	private Date createddate;
	
	private Long createdid;
	
	private Date updatedate;
	
	private String age;

	private Long updateid;
	
	
	
}
