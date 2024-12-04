package com.mahait.gov.in.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpLoanModel {
	String sevaarthId;
	String employeeName;
	String designName;
	String gpfNo;
	String orgnisationName;
	private Long employeeid;
	private Long loantypeid ;
	private Double loanprinamt ;
	private Double loaninterestamt ;
	private Integer loanprininstno ;
	private Integer loanintinstno;
	private Double  loanemiamt ;
	private String loanaccountno ;
	private Double loanintemiamt ;
	private Double loanprinemiamt;
	private String loansancorderno ;
	private Double oddinstno;
	private String voucherno;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date voucherdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date loansancorderdate;
	private String startdate ;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enddate ;
	private String loanAdvName;
	private Double oddinstamt;
	private String officeName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date loanDate;
	private Integer loanStatus;
	private Integer loanEmpAdvId;
	private Double RecoveredAmount;
	private Double totalRecoveredAmount;
	private Double totalRecoveredInstMent;
	private Double totalRecoveredInstAmt;
	private Integer totalRecoveredInstallment;
	
	private String dcpsNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doj;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date serviceEndDate;
	private Integer dcpsId;
	private String orgInstName;

	
	private String appNo;
	private String pfDesc;
	private Integer appId;
	private Long deptAllowdeducCode;

}
