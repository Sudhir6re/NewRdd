package com.mahait.gov.in.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="excess_Pay_recovery",schema="public")
public class ExcessPayRecoveryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="excess_Pay_recovery_id")
	private Long excessPayrecoveryId;
	
	@Column(name="sevaarth_id")
	private String sevaarthId;
	
	
	@Column(name="pay_month")
	private Integer payMonth;
	
	
	@Column(name="pay_year")
	private Integer payYear;
	
	@Column(name="recovery_month")
	private Integer recoveryMonth;
	
	
	@Column(name="recovery_year")
	private Integer recoveryYear;
	
	@Column(name="amt")
	private Double amt;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="pay_Item")
	private Integer payItem;
	
	@Column(name="voucher_no")
	private String voucherno;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="voucher_date")
	private Date voucherdate;
	
	@Column(name="prin_amount")
	private Double prinAmt;
	
	@Column(name="total_inst_no")
	private Integer totalInstNo;
	
	@Column(name="loan_prin_inst_no")
	private Integer loanPrinInstNo;
	
	@Column(name="prin_emi_amount")
	private Double prinEmiAmt;
	
	@Column(name="total_paid_amt")
	private Double totalPaidAmt;
	
	@Column(name="is_active")
	private char isActive;

	@Column(name="created_date")
	private Date createDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	

}
