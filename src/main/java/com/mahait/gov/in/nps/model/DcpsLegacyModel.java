package com.mahait.gov.in.nps.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DcpsLegacyModel {
	
	private  String dcpsNo;
	private String employeeFullNameEn;
	private String sevaarthId;
	
	private Date dob;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doj;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date superAnnDate;
	
	private Long mulTwoYearBasic;
	private Long mulOneYearBasic;
	private String pranNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date empServiceEndDate;
	
	private String pranStatus;
	private String reptDdoCode;
	
	private String action;
	
	private String txtSevaarthId;
	private String txtDcpsNo;
	private String employeeFullName;
	
	private String remark;
	
	private Long total;
	private Long employerInterest;
	private Long empInterest;
	private Long employerContri;
	private Long empContri;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contriEndDt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contriStartDt;
	
	private String period;
	
	private String ddoCode;
	
	List<DcpsLegacyModel> lstDcpsLegacyModel;
	
	private String status;
	private String locName;
	private String dtoRegNo;
	private String ddoRegNo;
	private Long npsId;
	
	private String extn;
	private String extnFlag;
	
	private Integer year;
	private Integer month;
	
	private String fileName;
	

	private Double bhEmpAmt;
	
	private Double bhEmplrAmt;
	
	private String transactionId;
 
	private String fileStatus;
	
	
	private String bhBatchFixId;
	
	private String bankRefno = "0";

	private String bdsNo = "0";

	private Long voucherNo = 0L;
	private Timestamp voucherDate;
	
}
