package com.mahait.gov.in.nps.model;

import java.math.BigInteger;

import lombok.Data;

@Data
public class DdoWiseNpsContriModel {
	
	private String ddocode;
	private BigInteger empCountWithPran;
	private BigInteger empCountWithNoPran;
	private Long totalEmpContri;
	private Long totalEmprContri;
	private Long totalAmount;
	
}
