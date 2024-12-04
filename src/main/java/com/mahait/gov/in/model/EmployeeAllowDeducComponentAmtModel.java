package com.mahait.gov.in.model;

import java.util.Date;

import lombok.Data;


@Data
public class EmployeeAllowDeducComponentAmtModel {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private int empAwnDedCompoAmtId;  
	private Long employeeId;  
	private double[] existingAmt;  
	private double[] netAmt;  
	private int departmentAllowDeducId;  
	private int departmentAllowdeducCode;
	private String[] sevaarthId;  
	private int isType;  
	private int ddoCode;  
	private char isActive;  
	private Long createdUserId;  
	private Date createdDate;
	private Long updatedUserId;
	private Date updatedDate;
	private String[] empName;
	private boolean srno;
	private int deptallowcode;
	private int deptcode;
	
	
	private String componentType;
	
	
	/*public getEmployeeAllowDeducComponentAmtModel()
	{
		this.EmployeeAllowDeducComponentAmtModel=EmployeeAllowDeducComponentAmtModel;
	}*/
	
	
	
}
