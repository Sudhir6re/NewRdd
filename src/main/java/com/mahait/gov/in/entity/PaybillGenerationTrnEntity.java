package com.mahait.gov.in.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="PAYBILL_GENERATION_TRN",schema="public")
public class PaybillGenerationTrnEntity {
	 
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PAYBILL_GENERATION_TRN_ID")
    private Long paybillGenerationTrnId;  
	
	@Column(name="BILL_CATEGORY")
    private Integer billCategory;
	
	@Column(name="SCHEME_BILLGROUP_ID")
	private Long schemeBillgroupId;
	
	@Column(name = "PAYBILL_MONTH")
	private Integer paybillMonth;
	
	@Column(name = "PAYBILL_YEAR")
	private Integer paybillYear;
	
	@Column(name = "BILL_TYPE_ID")
	private Integer billTypeId;
	
	@Column(name = "APPROVE_FLAG")
	private Integer approveFlag;
	
	@Column(name = "BILL_NET_AMOUNT")
	private Double billNetAmount;
	
	@Column(name = "BILL_GROSS_AMT")
	private Double billGrossAmt;
	
	@Column(name = "VOUCHER_NO")
	private String voucherNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "VOUCHER_DATE")
	private Date voucherDate;
	
	@Column(name = "DDO_Code")
	private String ddoCode;
	
	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "REASON_FOR_REJECTION")
	private int reasonForRejection;
	
	@Column(name = "CREATED_USER_ID")
	private Long createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private Long updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "IS_ACTIVE")
	private Integer isActive;
	
	@Column(name = "BILL_CREATION_DATE")
	private Date billcreationDate;

	@Column(name = "NO_OF_EMPLOYEE")
	private Integer noOfEmployee;

	@Column(name = "AUTH_NO")
	private String authNo; 
	
	@Column(name = "CMP_FILE_STATUS")
	private Integer CMPFileStatus;
	
	@Column(name = "CMP_DATE")
	private Date CMPDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "CHEQUE_DATE")
	private Date chequeDate;

	@Column(name = "CHEQUE_NO")
	private String chequeNo; 
	
	@Column(name = "ACCOUNT_NO")
	private String accountNo; 
	
	@Column(name = "IFSC_CODE")
	private String ifscCode; 
	
	@Column(name = "is_api_called")
	private String isApiCalled;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "api_call_date")
	private Date apiCallDate;

	@Column(name = "CMP_DOWNLOAD_STATUS")
	private Integer cmpdownloadStatus;

	@Column(name = "remark")
	private String remark;
}
