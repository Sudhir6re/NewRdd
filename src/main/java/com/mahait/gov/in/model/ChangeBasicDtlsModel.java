package com.mahait.gov.in.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class ChangeBasicDtlsModel {
	
	private String ppo;
	private String SevaarthId;
	private String EmpName;
	private Double basicSal;
	private Integer percentage;
	private Long empId;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enhFamPensDate;
	
	private Double enhFamPensAmt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date famPensDate;
	
	private Double famPensAmt;
	
	private String withEffDate;
	private Double revisedSal;
	private boolean checkboxid;
	public List<ChangeBasicDtlsModel> changeBasicDtlsModelsList=new ArrayList<>();
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date revEnhFamPensDate;
	
	private Double revEnhFamPensAmt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date revFamPensDate;
	
	private Double revFamPensAmt;
	
	private Double revNetGratuity;
	
	private String action;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date withEffDateBasicPen;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date revisedwithEffDateBasicPen;
	
	
	

}
