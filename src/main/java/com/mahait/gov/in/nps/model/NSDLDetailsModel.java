package com.mahait.gov.in.nps.model;

import java.util.List;

import lombok.Data;

@Data
public class NSDLDetailsModel {

	private String fileId;
	private Double amtEmpContibution;
	private Double amtEmprContibution;
	private Long transaction;
	private int fileValdtResl;
	private String sevaarthId;
	private String empName;
	private String PRAN;
	private String ddoCode;
	private String ddoRegNo;
	private Double totalAmt;
	private String empStatus;

	private Integer month;
	private Integer year;
	private Integer treasuryId;
	private String officeName;
	private Double grossTotal;
	private Double netTotal;
	private String dcpsId;
	private String transactionNumber;

	private Integer bhId;
	private String nsdlStatusCode;
	private String deptName;
	private Double totalEmpContri;
	private Double totalEmprContri;
	private Double totalContriAmt;
	private Integer deptCode;
	private String action;
	
	private List<NSDLDetailsModel> nsdlDetailsModelList;

	

}
