package com.mahait.gov.in.model;

import lombok.Data;

@Data
public class AquittanceRollReportModel {

	String empName;
	Double Totalamt;
	Double netamt;
	Double Totaldedamt;
	Double lic ;
	Double otrded ;
	Double cobank ;
	Double cohossoc ;
	Double recDept ;
	Double creditSoc ;
	Double misc ;
	private Integer yearId;
	private Integer monthId;
	private Long billGroup;
	
	
	
}
