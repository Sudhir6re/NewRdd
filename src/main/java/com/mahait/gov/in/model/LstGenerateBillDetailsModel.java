package com.mahait.gov.in.model;

import lombok.Data;

@Data
public class LstGenerateBillDetailsModel {
	
	private String paybillGenerationTrnId;
	private String  billDescription;
	private String schemeCode;
	private String schemeName;
	private Double billGrossAmt;
	private Double billNetAmt;
	private int isActive;
	private String ddoCode;
	private Integer noOfEmployee;
	private String authno;
	
	
	

}
