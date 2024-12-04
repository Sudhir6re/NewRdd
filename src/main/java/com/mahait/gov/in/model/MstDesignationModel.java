package com.mahait.gov.in.model;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.Data;

@Data
public class MstDesignationModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*private Integer fieldDepartment;*/
	private BigInteger designationCode;
	private String designation;
	private String designationShortName;
	private Long designationId;
	private Integer cadreCode;
	private String cadreName;
	private int cadreGroup;
	private long isActive;
	
}
