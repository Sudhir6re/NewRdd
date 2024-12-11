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

@Entity
@Data
@Table(name="paybill_status_updates",schema="public")
public class PaybillStatusEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PAYBILL_STATUS_ID")
    private int paybillStatusId;  
	
	@Column(name="USER_ID")
    private Long userId;
	
	@Column(name="MAC_ID")
	private String macId;
	
	@Column(name = "BILL_NO")
	private Long billNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "IS_ACTIVE")
	private Integer isActive;
	
}
