package com.mahait.gov.in.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UpdateServiceEndDateModel {
	public String sevaarthId;
	private Long employeeId;
	private String employeeFullNameEn;
	private boolean checkboxid;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sed;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	
	private String designation; 
	private Integer designationCode;
	private Integer designationId;
	
	
	private Long cadreid;

	
	public List<UpdateServiceEndDateModel> emplist=new ArrayList<>();

}
