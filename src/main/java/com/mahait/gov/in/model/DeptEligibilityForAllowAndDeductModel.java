package com.mahait.gov.in.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DeptEligibilityForAllowAndDeductModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer departmentAllowdeducId;
	private Integer departmentAllowdeducCode;
	private String departmentAllowdeducName;
	private Integer schemeBillGrpId;
	private Integer isType;
	private boolean isChecked;	
	private boolean checkBox;	
	
	private Integer employeeId;
	private String sevaarthId;
	private Integer nonComputational;
	
	private List<DeptEligibilityForAllowAndDeductModel> lstDeptEligibilityForAllowAndDeductModel;
	
	
}
