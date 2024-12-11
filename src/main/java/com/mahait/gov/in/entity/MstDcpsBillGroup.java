package com.mahait.gov.in.entity;


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
@Table(name="MST_DCPS_BILL_GROUP",schema="public")
public class MstDcpsBillGroup  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bill_group_id")
	private Long dcpsDdoBillGroupId;
	
	@Column(name="ddo_code")
	private String dcpsDdoCode;
	
	@Column(name="description")
	private String description;
	
	@Column(name="scheme_name")
	private String dcpsDdoBillSchemeName;
	
	@Column(name="scheme_code")
	private String dcpsDdoSchemeCode;
	
	@Column(name="TYPE_OF_POST")
	private String dcpsDdoBillTypeOfPost;
	

	@Column(name="SUB_BG_OR_NOT")
	private Integer subBGOrNot;
	
	
	@Column(name="LANG_ID")
	private Long langId;
	
	@Column(name="LOC_ID")
	private Long locId;
	
	@Column(name="DB_ID")
	private Long dbId;
	
	@Column(name = "CREATED_POST_ID")
	private Long postId;
	

	@Column(name = "CREATED_USER_ID")
	private Long userId;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_POST_ID")
	private Long updatedPostId;

	@Column(name = "UPDATED_USER_ID")
	private Long updatedUserId;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name = "bill_no")
	private Long billNo;
	

	@Column(name="BILL_DELETED")
	private Character billDeleted;
	
	@Column(name="BILL_DCPS")
	private Character billDcps;
	
	@Column(name="SUB_SCHEME_CODE")
	private String dcpsDdoSubSchemeCode;

	
	

}

