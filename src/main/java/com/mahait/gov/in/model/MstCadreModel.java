package com.mahait.gov.in.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
@Data
public class MstCadreModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*private String fieldDepartmrnt;*/
	private String cadreGroup;
	private long cadreCode;
	private String cadreDescription;
	private String whetherMinisterial;
	private BigDecimal superAnnuationAge;
	private long cadreId;
	private long isActive;
	
	/*private String cadreControlledByOwnDepartment;*/
	
	
}
