package com.mahait.gov.in.model;

import lombok.Data;

@Data
public class PaybillViewApproveDeleteModel {
	
	
	    private Long paybillGenerationTrnId;
	    private String billDescription;          
	    private String schemeCode;           
	    private String schemeName;           
	    private Long billGrossAmt;           
	    private Long billNetAmount;         
	    private Integer isActive;             
	    private Integer noOfEmployee;        
	    private String authNo;
		


}
