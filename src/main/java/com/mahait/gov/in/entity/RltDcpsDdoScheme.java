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

@Data
@Entity
@Table(name = "RLT_DCPS_DDO_SCHEMES", schema = "public")
public class RltDcpsDdoScheme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DDO_SCHEMES_ID")
	private BigInteger dcpsDdoSchemesId;

	@Column(name = "SCHEME_CODE")
	private String dcpsSchemeCode;

	@Column(name = "DDO_CODE")
	private String dcpsDdoCode;

	@Column(name = "LANG_ID")
	private BigInteger langId;

	@Column(name = "db_id")
	private BigInteger dbId;

	@Column(name = "loc_id")
	private BigInteger locId;

	@Column(name = "CREATED_USER_ID")
	private BigInteger createdUserId;

	@Column(name = "created_post_id")
	private BigInteger createdPostId;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_USER_ID")
	private BigInteger updatedUserId;

	@Column(name = "updated_post_id")
	private BigInteger updatedPostId;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "SUB_SCHEME_CODE")
	private String dcpsSubSchemeCode;

}