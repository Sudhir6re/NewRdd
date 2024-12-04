package com.mahait.gov.in.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UpdateUIDModel {
	
	private Long employeeId;
	private String sevaarthId;
	private String employeeFullNameEn;
	private String uidNo;
	private boolean checkboxid;
	public List<UpdateUIDModel> emplist=new ArrayList<>();
	

}
