package com.mahait.gov.in.entity;

import java.math.BigInteger;
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
@Table(name="loan_employee_dtls",schema="public")
public class LoanEmployeeDtlsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="loan_employee_adv_id")
	private Long loanemployeeadvid;
	
	@Column(name="employee_id")
	private Long employeeid;
	
	@Column(name="sevaarth_id")
	private String sevaarthid;
	
	@Column(name="department_allowdeduc_code")
	private Long departmentallowdeduccode ;
	
	@Column(name="loan_type_id")
	private Long loantypeid ;
	
	@Column(name="loan_prin_amt")
	private Double loanprinamt ;
	
	@Column(name="loan_interest_amt")
	private Double loaninterestamt ;
	
	@Column(name="loan_prin_inst_no")
	private Integer loanprininstno ;
	
	@Column(name="loan_int_inst_no")
	private Integer loanintinstno;
	
	@Column(name="loan_emi_amt")
	private Double  loanemiamt ;
	
	@Column(name="loan_account_no")
	private String loanaccountno ;
	
	@Column(name="loan_date")
	private Date  loandate ;
	
	@Column(name="trn_counter")
	private Integer trncounter ;
	
	@Column(name="loan_int_emi_amt")
	private Double loanintemiamt ;
	
	@Column(name="loan_prin_emi_amt")
	private Double loanprinemiamt;
	
	@Column(name="loan_sanc_order_no")
	private String loansancorderno ;
	
	@Column(name="loan_activate_flag")
	private Integer loanactivateflag ;
	
	@Column(name="odd_inst_no")
	private Double oddinstno;
	
	@Column(name="odd_inst_amt")
	private Double oddinstamt;
	
	@Column(name="is_approved")
	private Integer isapproved ;
	
	@Column(name="voucher_no")
	private String voucherno;
	
	@Column(name="voucher_date")
	private Date voucherdate;
	
	@Column(name="loan_sanc_order_date")
	private Date loansancorderdate;
	
	@Column(name="loan_rec_type")
	private Integer loanrectype ;
	
	@Column(name="loan_rec_remarks")
	private String loanrecremarks;
	
	@Column(name="multi_inst_recvd")
	private Integer multiinstrecvd ;
	
	@Column(name="multi_amt_recvd")
	private Double multiamtrecvd ;
	
	@Column(name="loan_update_order_no")
	private String loanupdateorderno;
	
	@Column(name="loan_update_order_date")
	private Date loanupdateorderdate ;
	
	@Column(name="start_date")
	private Date startdate ;
	
	@Column(name="end_date")
	private Date enddate ;
	
	@Column(name="created_by")
	private BigInteger createdby ;
	
	@Column(name="created_by_post")
	private BigInteger createdbypost ;
	
	@Column(name="created_date")
	private Date createddate  ;
	
	@Column(name="updated_by")
	private BigInteger updatedby;
	
	@Column(name="updated_by_post")
	private BigInteger updatedbypost ;
	
	@Column(name="updated_date")
	private Date updateddate ;

	@Column(name="total_recovered_amt")
	private Double totalRecoveredAmt ;
	
	@Column(name="total_recovered_inst")
	private int totalRecoveredInst ;
	
	@Column(name="gpf_app_no")
	private int gpfAppNo ;
	
	@Column(name="sanc_amt_gpf_II")
	private Double sancAmtGpfII ;

	@Column(name="sanc_inst_gpf_II")
	private Integer sancInstGpfII ;

	@Column(name="inst_amt_gpf_II")
	private Double instAmtgpfII;
	
	@Column(name="total_recovered_amt_gpf_II")
	private Double totalRecoveredAmtGpfII ;
	
	@Column(name="total_recovered_inst_gpf_II")
	private Integer totalRecoveredInstGpfII ;
	
}
