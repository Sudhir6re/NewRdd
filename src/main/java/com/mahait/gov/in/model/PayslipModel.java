package com.mahait.gov.in.model;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PayslipModel {
	
	private String sevaarthId;
	private String empName;
	private String desgnName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doj;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dor;
	
	private Long mobileNo1;
	
	
	private String panNo;

	
	private Long payCommisionCode;
	private String uidNo;
	private String ifsc;
	
	private BigInteger bankAccNo;
	
	private String ddoCode;
	
	private String location;
	

	private String gpfNdcpsNo;   //pf description
		

	private Double grossTotalAmt;
	
	private Double totalDeduction;

	private Double totalNetAmt;

	
	private String netAmtInWord;
	
	
	private String subDeptNameEn;
	
	
	private String billGroupName;
	private Integer hraPercent;
	private Integer daPercent;
	private Long sevenPcLvl;
	private String Lvl;
	private String payComm;
	private Double basic;
	private String voucherNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date voucherDate;
	private Long billNo;
	private String accountNo;
	

}
