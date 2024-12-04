package com.mahait.gov.in.model;

import lombok.Data;

@Data
public class PaybillGenerationReportModel {
	private Long region;
	private int year;
	private int month;
	private String treasuryName;
	private int totalDDOs;
	private int totalBill;
	private int generated;
	private int notGenerated;

}