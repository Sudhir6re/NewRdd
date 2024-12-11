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
@Table(name="STATE_MST",schema="public")
public class MstStateEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="State_ID")
	private int StateId;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_CODE",  nullable=true,insertable = false, updatable = false)
    private CountryMasterEntity mstCountryEntity;
	
	@Column(name="COUNTRY_CODE")
	private Integer countryCode;

	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name="state_name_en")
	private String stateNameEn;
	
	@Column(name="state_name_mr")
	private String stateNameMr;
	
	@Column(name = "nps_state_code")
	private Integer nps_state_code;
	
	@Column(name = "IS_ACTIVE")
	private Character isActive;
	
	@Column(name = "CREATED_USER_ID")
	private Integer createdUserId;
	
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	

}
