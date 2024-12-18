package com.mahait.gov.in.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UpdateDOBModel {
	
	
	public String sevaarthId;
	private Long employeeId;
	private String employeeFullNameEn;
	private boolean checkboxid;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	
	private String dateString;
	
	
	private String designation; 
	private Integer designationCode;
	private Integer designationId;

	
	
	
	
	
	public List<UpdateDOBModel> emplist=new ArrayList<>();

}
