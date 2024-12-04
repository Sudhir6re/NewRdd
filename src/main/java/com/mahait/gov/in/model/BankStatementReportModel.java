package com.mahait.gov.in.model;

import lombok.Data;

@Data
public class BankStatementReportModel {

	String empName;
	String accno;
	String bankname;
	String bankBranchName;
	String ifsccode;
	String monname;
	Double Totalamt;
	Double netamt;
	private Integer yearId;
	private Integer monthId;
	private Long billGroup;
}
