package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="BEAMS_INTEGRATION_TRN",schema="public")
public class BeamsIntegrationEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BEAMS_INTEGRATION_TRN_ID")
	private int beamsIntegrationId;  
	
	@Column(name="CONSOLIDATE_ID")
	private Integer consolidateId;  
	   
	@Column(name="FIN_YEAR_1")
	private Integer finYear1;  
	
	@Column(name="FIN_YEAR_2")
	private Integer finYear2;  
	
	@Column(name="YEAR_MONTH")
	private Integer yearMonth;  
	
	@Column(name="SCHEME_CODE")
	private String schemeCode;  
	
	@Column(name="DTLHEAD_CODE")
	private String dtlHeadCode;  
	
	@Column(name="DDO_CODE")
	private String ddoCode;  
	
	@Column(name="BILL_GROSS_AMT")
	private Double billGrossAmt;  
	
	@Column(name="BILL_NET_AMT")
	private Double billNetAmt;  
	
	@Column(name="STATUS_CODE")
	private String statusCode;
	
	@Column(name="BILL_CREATION_DATE")
	private Date billCreationDate;
	
	@Column(name="NO_OF_BENEFICIARY")
	private Integer noOfBenifciary;

	@Column(name ="AUTH_NO")
	private String authNo;
	
	@Column(name ="BILL_VALID_STATUS")
	private String billValidStatus;
	
	@Column(name ="VOUCHER_NO")
	private Integer voucherNo;
	
	@Column(name ="VOUCHER_DATE")
	private Date voucherDate;
	
	@Column(name ="AUTH_SLIP")
	private byte[] authSlip;
	
	@Column(name ="CREATED_USER_ID")
	private Integer createdUserId;
	
	@Column(name ="CREATED_DATE")
	private Date cratedDate;
	
	@Column(name ="UPDATED_USER_ID")
	private Integer updatedUserId;
	
	@Column(name ="UPDATED_DATE")
	private Date updatedDate;
	
	
}
