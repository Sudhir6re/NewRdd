package com.mahait.gov.in.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class BrokenPeriodModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int paybillgenerationtrnid;
	private String billdescription;
	//start
	private String deptalldetNm;
	private String deptalldetValue;// edp desc
	private int type; 
	private int deptallowdeducid;
	private String tempvalue; // insted of subdetail head and headofaccountcode
	private String tempempty;
	//end
	private String sevaarthid;
	private String ppoNo;
	private String empName;
	private String empId;
	private String month;
	private String year;
	private String noOfDays;
	private String paybillMonth;
	private String paybillYear;
	private String action;
	private String groupNm;
	private double gisAmount;
	private String ddocode;
	private Double arrears;
	private String remarks;
	private String desgName;
	private String methodName;
	private String formulas;
	private int isNonComputation;
	private int isNonGovernment;
	private int isRuleBased;
	private int isLoanAdv;
	
	
	
	

}
