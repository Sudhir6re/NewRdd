package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="broken_period_allow_deduc_mst",schema="public")
public class BrokenPeriodAllowDeducEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BROKEN_PERIOD_ALLOW_DEDUC_ID")
	private Long brokenPeriodAllowDeducId;
	@ManyToOne
	@JoinColumn(name = "BROKEN_PERIOD_ID",  nullable=true,insertable = false, updatable = false)
	BrokenPeriodEntity brokenPeriodEntity;
	
	
	@Column(name ="BROKEN_PERIOD_ID")
	private Long brokenPeriodId;
	
	
	@Column(name ="allow_deduc_code")
	private Integer allowDeducCode;
	
	
	@ManyToOne
	@JoinColumn(name = "allow_deduc_code", insertable = false, updatable = false)
	private DeptEligibilityForAllowAndDeductEntity deptEligibilityForAllowAndDeductEntity;
	
	
	@Column(name ="allow_deduc_amt")
	private Double allowDeducAmt;
	@Column(name ="is_type")
	private Integer istype;
	@Column(name ="status")
	private Integer status;
	@Column(name ="sevaarth_id")
	private String sevaarthid;
	@Column(name ="CREATED_USER_ID")
	private Integer createdUserId;
	@Column(name ="CREATED_DATE")
	private Date createdDate;
	@Column(name ="UPDATED_USER_ID")
	private Integer updatedUserId;
	@Column(name ="UPDATED_DATE")
	private Date updatedDate;

	}
