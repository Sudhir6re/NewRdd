package com.mahait.gov.in.nps.model;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class CSRFFormModel {
		private String sevaarthId;
		private String empName;
		private String designationName;
		private String DDOCode;
		private String officeName;
		private String dcpsId;
		private Date DOJ;
		private BigInteger empId;
		private Integer ackNo;
		private String ddoAsst;
		private String ddo;
		private Integer yearId;
		private Integer monthId;
		private String treasuryName;
		private String treasuryCode;
		
	}
