package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "department_allowdeduc_mst", schema = "public")
public class DeptEligibilityForAllowAndDeductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPARTMENT_ALLOWDEDUC_ID")
	private Integer departmentAllowdeducId;

	@Column(name = "DEPARTMENT_ALLOWDEDUC_CODE")
	private Integer departmentAllowdeducCode;

	@Column(name = "DEPARTMENT_ALLOWDEDUC_NAME")
	private String departmentAllowdeducName;

	@Column(name = "department_allowdeduc_col_nm")
	private String departmentAllowdeducColNm;

	@Column(name = "head_account_code")
	private String headAccountCode;

	@Column(name = "IS_TYPE")
	private Integer isType;

	@Column(name = "IS_ACTIVE")
	private Character isActive;

	@Column(name = "CREATED_USER_ID")
	private Long createdUserId;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "DEPARTMENT_ALLOWDEDUC_SEQ")
	private Integer deptAllowDeducSeq;

	@Column(name = "is_non_computation_component")
	private Integer isNonComputationComponent;

	@Column(name = "method_name")
	private String methodName;

	@Column(name = "broken_method_name")
	private String brokenMethodName;

	@Column(name = "is_Allowdeduc_Type_sum")
	private Integer isAllowdeducTypeSum;

	@Column(name = "formulas")
	private String formulas;

	@Column(name = "is_non_government")
	private Integer isNonGovernment;

	@Column(name = "is_Loan_Adv")
	private Integer isLoanAdv;

}
