package com.mahait.gov.in.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GrossReportModel {
	
	private long locId;
	private String locName;
	private int yearId;
	private int monthId;
	private String district;
	private String office;
	private String sevaarthId;
	private String gender;
	private String desgn;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private Double age;
	private BigDecimal gross;
	private BigDecimal basicArr;
	private BigInteger daArr;
	private BigInteger gpfArr;
	private BigInteger dcpsArr;
	private BigInteger transArr;
	private BigInteger taArr;
	private BigInteger hrafifthPay;
	

}
