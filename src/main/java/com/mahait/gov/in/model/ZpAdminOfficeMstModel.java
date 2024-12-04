package com.mahait.gov.in.model;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class ZpAdminOfficeMstModel {
	
	private Long ofcId;

	private String officeName;

	private String officeCode;

	private Long langId;

	private Long createdBy;

	private Timestamp createdDate;

	private Long updatedBy;

	private Timestamp updatedDate;

	private Long createdByPost;

	private String schemeCode;

	private String dcpsOffName;
	
    private Integer isActive;

}
