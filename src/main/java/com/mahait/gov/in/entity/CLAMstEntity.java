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
@Table(name="cla_mst",schema="public")
public class CLAMstEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cla_mst_id")
	private Long claMstId;
	
	@Column(name="city_group")
	private String cityGrp;

	@Column(name="IS_TYPE")
	private Integer isType;
	
	@Column(name="AMOUNT",length =13,precision=2)
	private Double amount;
	
	@Column(name="min_basic")
	private Double minBasic;
	
	@Column(name="max_basic")
	private Double maxBasic;
	
	@Column(name="allow_deduc_code")
	private Long allowDeducCode;
	
	@Column(name="PERCENTAGE")
	private Integer percentage;
	
	@Column(name="pay_commission_code")
	private Long payCommissionCode;
	
	@Column(name = "created_user_id")
	private Long createdUserId;
	
	@Column(name = "updated_user_id")
	private Long updatedUserId;
	
	@Column(name = "svnPcLvl")
	private Long svnPcLvl;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	
	@Column(name = "is_active")
	private char isActive;

	
}