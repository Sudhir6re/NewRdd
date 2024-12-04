package com.mahait.gov.in.model;

import java.io.Serializable;

import lombok.Data;
@Data
public class MstStateModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String stateNameEn;
	private String stateNameMr;
	private int countryCode;
	private String countryNameEn;
	private String countryNameMr;
	private int stateCode;
	private char isActive;
	private int districtCode;
	private String districtNameEn;
	

}
