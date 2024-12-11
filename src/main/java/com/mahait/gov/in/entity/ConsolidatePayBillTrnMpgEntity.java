package com.mahait.gov.in.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="CONSOLIDATE_PAYBILL_TRN_MPG",schema="public")
public class ConsolidatePayBillTrnMpgEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CONSOLIDATE_PAYBILL_TRN_MPG_ID")
    private Long ConsolidatePaybillTrnMpgId;
	
	
	@Column(name = "PAYBILL_GENERATION_TRN_ID")
	private Long PaybillGenerationTrnId;
	
	@Column(name = "DDO_CODE")
	private String ddoCode;
	
	@Column(name="CONSOLIDATE_PAYBILL_TRN_ID")
    private Long ConsolidatePaybillTrnId;

	

}
