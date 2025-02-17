package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CONSOLIDATE_PAYBILL_TRN", schema = "public")
public class ConsolidatePayBillTrnEntity {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONSOLIDATE_PAYBILL_TRN_ID")
	private Long consolidatePaybillTrnId;

	@Column(name = "IS_ACTIVE")
	private Integer isActive;

	@Column(name = "status")
	private Integer status;

	@Column(name = "PAYBILL_MONTH")
	private Integer paybillMonth;

	@Column(name = "PAYBILL_YEAR")
	private Integer paybillYear;

	@Column(name = "SCHEME_CODE")
	private String schemeCode;

	@Column(name = "DDO_Code")
	private String ddoCode;

	@Column(name = "GROSS_AMT")
	private Double GrossAmt;

	@Column(name = "NET_AMT")
	private Double netAmt;

	@Column(name = "AUTH_NO")
	private Integer authNo;

	@Column(name = "CMP_FILE_STATUS")
	private Integer CMPFileStatus;

	@Column(name = "CMP_DATE")
	private Date CMPDate;
	
	
	
	@Column(name = "CMP_DOWNLOAD_DATE")
	private Date cmpDownloadDate;

	@Column(name = "BILL_TYPE")
	private Integer billType;

	@Column(name = "CMP_DOWNLOAD_STATUS")
	private Integer cmpdownloadStatus;

	@Column(name = "CREATED_USER_ID")
	private Long createdUserId;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_USER_ID")
	private Long updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "PF")
	private Double pf;

	@Column(name = "PT")
	private Double pt;

	@Column(name = "INCOME_TAX")
	private Double it;

	@Column(name = "HRR")
	private Double hrr;

	@Column(name = "DCPS_ARR")
	private Double dcpsArr;

	@Column(name = "GIS")
	private Double gis;
	
	
	@Column(name = "gis_zp")
	private Double gisZp;
	
	

	@Column(name = "GROUP_ACC_POLICY")
	private Double accPolicy;

	@Column(name = "REVENUE_STAMP")
	private Double revenueStamp;

	@Column(name = "TOTAL_DEDUCTION")
	private Double totalDeduct;

	@Column(name = "DCPS")
	private Double dcps;
	
	@Column(name = "no_of_employee")
	private Integer noOfEmployee;

}
