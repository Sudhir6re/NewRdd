package com.mahait.gov.in.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostEntryModel {

	private String cmbTaluka;
	private String ddoCodeforFilter;
	private String ddoCode;
	private Long cmbSubFieldDept;
	private Long postTypeCmbBox;
	private String postSubTypeCmbBoxTemp;
	private String postSubTypeCmbBoxPerm;
	private String tempPostTypeCmbBox;
	private String designationCmb;
	private String subjectCmb;
	private long orderCmb;
	 private String purposeCmbBox;
	private long oldGrOrderId;
	private String action;

	private String currentOrder;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date currentOrderDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date oldGrOrderDate;

	private long cmbNewOrder;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date renewalStartDate;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date renewalPostStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date renewalEndDate;


	private String originalOrderCmb;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String originalOrderDate;

	private String renewalOrderCmb;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date renewalOrderDate;

	private Long officeCmb;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tempEndDate;

	private int postNumber;

	private String remarks;

	private String flag;
	private Long postId;
	private String givenurl;
	private Long districtId;
	
	
	private String postIdsToBeAttached;

}
