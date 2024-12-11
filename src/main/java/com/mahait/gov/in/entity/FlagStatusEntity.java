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
@Table(name = "bill_flag_status", schema = "public")
public class FlagStatusEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="bill_status_id")
	private int billStatusId;
	
	@Column(name="bill_status_code")
	private int billStatusCode;
	
	@Column(name="is_active")
	private int isActive;
	
	@Column(name="status_description")
	private String billDescription;
	
	@Column(name="status_name")
	private String billName;
	
	
}
