package com.mahait.gov.in.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * @author parvez
 *
 */
@Data
public class AnnualIncrementModel {

	String ddoCode;
	String sevaarthId;
	String empname;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date incrementOrderDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date presentIncreDate;
	private boolean checkboxid;
	String ddoName;
	String officeName;
	char isActive;
	Integer monthId;
	Integer yearId;
	String orderNo;
	String postName;
	Double basicPay;
	Integer currbasic;
	Integer incrBasic;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date incrDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date incrToDate; 
	Integer basicPayIncrementId;
	String empCount;
	String status;
	Integer payCommId;
	public List<AnnualIncrementModel> annualIncrementModelList = new ArrayList<>();
	
}
