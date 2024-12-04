/**
 * 
 */
package com.mahait.gov.in.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class PayBillViewApprDelBillModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String monthName;
	private String yearName;
	private String billType;
	private String billStatus;
	private String billNumber;
	
	private String empName;
	private String componentName;
	private Double previousMonthAmount;
	private Double currentMonthAmount;
	private Double difference;
	private String dateAndTime;
	private String color; 
	private String voucherNo; 
	private String voucherDate; 
	private Integer bankId; 
	
	private String action; 
	
	
}
