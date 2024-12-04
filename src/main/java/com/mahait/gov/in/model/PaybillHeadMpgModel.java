package com.mahait.gov.in.model;

import java.math.BigInteger;
import java.sql.Date;

import lombok.Data;

@Data
public class PaybillHeadMpgModel {
	
private static final long serialVersionUID = 1L;
	
	
    private Long paybillGenerationTrnDetailId;  
    private int billCategory;
	private Long schemeBillgroupId;
	private int billGroupId;
	private int paybillMonth;
	private int paybillYear;
	private int billTypeId;
	private int approveFlag;
	private int billNetAmount;
	private int billGrossAmt;
	private int voucherNo;
	private Date voucherDate;
	private String ddoCode;
	private int status;
	private int reasonForRejection;
	private Long createdUserId;
	private Date createdDate;
	private Integer updatedUserId;
	private Date updatedDate;
	private char isActive;
	private String billGroup;
	private String schemeName;
	private String paybillType;
	private String majorHead;
	private String minorHead;
	private String subMajorHead;
	private String subMinorHead;
	private Integer noOfEmployee;
	private String macId;
	
	
	

}
