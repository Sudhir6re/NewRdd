package com.mahait.gov.in.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class DcpContributionModel {
	
    private Long dcpContributionId;

    private Long dcpEmpId;
    
    private String sevaarthId;
    
    private String dcpsNO;
    
    private String employeeName;

    private Long treasuryCode;

    private String ddoCode;

    private Long billGroupId;

    private String schemeCode;
    
    private String subSchemeCode;

    private Long payCommission;

    private String typeOfPayment;

    private int finYearId;

    private int monthId;

    private Integer basicPay;
    
    
    private Integer actualBasic;
    

    private Integer da;
    

    private Integer contribution;

    private Integer regStatus;

    private Long langId;

    private Long locId;

    private Long dbId;

    private Long createdPostId;

    private Long createdUserId;

    
    private Timestamp createdDate;

    private Long updatedPostId;

    private Long updatedUserId;

    private Timestamp updatedDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
	
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payArrearStartDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payArrearEndDate;
	
	
	
	
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dAArrearStartDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dAArrearEndDate;
	
	
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doj;

    private Long rltContriVoucherId;

    private int delayedFinYearId;

    private int delayedMonthId;

    private String employerContriFlag;

    private String status;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date voucherDate;

    private Long voucherNo;
    
    private String action;
    
    private String useType;
    
    private Integer daRate;
    
	private Double dp;
    
	private Integer emprContribution;
	
	private List<Long> deleteDcpContributionId;  
    
    
    List<DcpContributionModel> lstDcpContributionModel;
    
    
    private String schemeName;

}
