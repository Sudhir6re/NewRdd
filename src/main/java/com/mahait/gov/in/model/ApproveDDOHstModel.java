package com.mahait.gov.in.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ApproveDDOHstModel {

	String ddoCode;
	String ddoName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date fromDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date toDate;
	String status;


	public List<ApproveDDOHstModel> approveDDOHstModel = new ArrayList<>();


	
}
