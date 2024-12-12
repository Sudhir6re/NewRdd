package com.mahait.gov.in.nps.entity;

import java.math.BigInteger;
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
@Table(name="trn_nps_reg_file",schema="public")
public class TrnNpsRegFileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "batch_fixed_id")
	private Integer  batchFixId;
	
	@Column(name = "ack_no")
	private BigInteger ackNo;
	
	@Column(name = "nps_id")
	private Long npsId;
	
	@Column(name = "is_active")
	private String isActive;
	
	@Column(name = "ref_seq")
	private Integer refSeq;
	
	@Column(name = "total_emp_in_batch")
	private Integer totalEmpInBatch;
	
	@Column(name = "nsdl_ref_no")
	private Integer nsdlRefNo;

	
	@Column(name = "is_pran_generated")
	private Integer isPranGenerated;
	
	@Column(name = "CREATED_USER_ID")
	private Long createdUserId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	
	
	@Column(name = "nsdl_status_code")
	private String nsdlStatusCode;
	
	
	
	

	

	
	
}
