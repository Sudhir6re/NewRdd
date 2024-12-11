package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="EMPLOYEE_ALLOWDEDUC_COMPONENT_AMT",schema="public")
public class EmployeeAllowDeducComponentAmtEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EMP_AWNDED_COMPO_AMT_ID")
	private int empAwnDedCompoAmtId;  
	
	@Column(name="employee_id")
	private Long employeeId;  
	   
	@Column(name="EXISTING_AMT")
	private double existingAmt;  
	
	@Column(name="NET_AMT")
	private double netAmt;  
	
	@Column(name="DEPARTMENT_ALLOWDEDUC_ID")
	private int departmentAllowDeducId;  
	
	@Column(name="SEVAARTH_ID")
	private String sevaarthId;  
	
	@Column(name="IS_TYPE")
	private int isType;  
	
	@Column(name="DDO_CODE")
	private int ddoCode;  
	
	@Column(name="IS_ACTIVE")
	private char isActive;  
	
	@Column(name="CREATED_USER_ID")
	private Long createdUserId;  
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="UPDATED_USER_ID")
	private Long updatedUserId;
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name ="EMPLOYEE_NAME")
	private String empName;
	
	@Column(name ="department_allowdeduc_code")
	private int deptallowcode;
	
	@Column(name ="department_code")
	private int deptcode;
	
	  

}
