package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "MST_BANK_PAY")
public class MstBankPay {

	@Id
	@Column(name = "BANK_ID")
	private Long bankId;

	@Column(name = "BANK_CODE", length = 20)
	private String bankCode;

	@Column(name = "BANK_NAME", nullable = false, length = 100)
	private String bankName;

	@Column(name = "B_SHORT_NAME", length = 10)
	private String shortName;

	@Column(name = "BANK_ADDRESS", length = 100)
	private String bankAddress;

	@Column(name = "LANG_ID")
	private Short langId;

	@Column(name = "ACTIVATE_FLAG", nullable = false)
	private Long activateFlag;

	@Column(name = "START_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Column(name = "CREATED_USER_ID", nullable = false)
	private Integer createdUserId;

	@Column(name = "CREATED_POST_ID", nullable = false)
	private Integer createdPostId;

	@Column(name = "CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "UPDATED_USER_ID")
	private Integer updatedUserId;

	@Column(name = "UPDATED_POST_ID")
	private Integer updatedPostId;

	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name = "DB_ID")
	private Short dbId;

	@Column(name = "LOCATION_CODE", nullable = false, length = 10)
	private String locationCode;

	@Column(name = "MICR_CODE", length = 20)
	private String micrCode;

	@Column(name = "BANK_GROUP_ID")
	private Long bankGroupId;

	@Column(name = "ACC_NO_LENGTH", columnDefinition = "bigint default 20")
	private Long accNoLength;

	// Constructors, getters, and setters omitted for brevity
	// Implement as per your requirements
}
