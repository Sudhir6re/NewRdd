package com.mahait.gov.in.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UpdatePanNoModel {
 
	public String sevaarthId;
	private Long employeeId;
	private String employeeFullNameEn;
	private boolean checkboxid;
	private String panNo;
	private String designation; 
	private Integer designationCode;
	private Integer designationId;

	
	public List<UpdatePanNoModel> emplist=new ArrayList<>();


	
}
