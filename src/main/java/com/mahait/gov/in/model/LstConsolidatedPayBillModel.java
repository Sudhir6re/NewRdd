package com.mahait.gov.in.model;

import java.math.BigInteger;

import lombok.Data;

@Data
public class LstConsolidatedPayBillModel {
	
	private BigInteger consolidatePayBillTrnId;
	private String schemeCode;
	private String schemeName;
	private double billGrossAmt;
	private double billNetAmount;
	private char isActive;
	private Integer isActiveInt;
	
	private Integer authNo;
	
	private Long eKuberCount;
	private String cmpDownloadStatus;
	
	
	
	

}
