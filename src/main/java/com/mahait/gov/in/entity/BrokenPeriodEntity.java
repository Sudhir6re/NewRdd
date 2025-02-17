package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "broken_period_pay_mst", schema = "public")
public class BrokenPeriodEntity {
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "broken_period_id")
	private Long brokenPeriodId;
	@Column(name = "SEVAARTH_ID")
	private String sevaarthid;
	
	@Column(name = "EMP_ID")
	private Long empId;
	
	
	@Column(name = "MONTH_ID")
	private Integer monthId;
	@Column(name = "YEAR_ID")
	private Integer yearId;
	@Column(name = "FROM_DATE")
	private Date fromDate;
	@Column(name = "TO_DATE")
	private Date toDate;
	@Column(name = "NO_OF_DAYS")
	private Integer noOfDays;

	@Column(name = "BASIC_PAY")
	private Integer basicPay;

	@Column(name = "NET_PAY")
	private Integer netPay;
	@Column(name = "REASON")
	private String reason;
	@Column(name = "REMARKS")
	private String remarks;
	@Column(name = "DDO_CODE")
	private String ddoCode;
	private String basicForCalculation;
	@Column(name = "CREATED_USER_ID")
	private Integer createdUserId;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

}
