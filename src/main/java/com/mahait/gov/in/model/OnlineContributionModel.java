package com.mahait.gov.in.model;

import java.util.Date;

import lombok.Data;

@Data
public class OnlineContributionModel {
	
	private Integer yearId;
	private Integer monthId;
	private String DeptName;
	private Long billGroup;
	private Long paybillId;
	private String name;
	private Double basicpay;
	private Double svnpcda;
	private Double dp;
	
	private Double emprContribution;
	
	private String action;
	private Long paymentType;
	private Date contriStartDate;
	private Date contriEndDate;
	private Date contribution;
	private String ddoCode;
}

