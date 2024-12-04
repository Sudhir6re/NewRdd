package com.mahait.gov.in.model;

import java.io.Serializable;

public class MstTalukaModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String countryName;
	private String stateName;
	private String districtName;
	private String talukaNameEn;
	private String talukaNameMr;
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getTalukaNameEn() {
		return talukaNameEn;
	}
	public void setTalukaNameEn(String talukaNameEn) {
		this.talukaNameEn = talukaNameEn;
	}
	public String getTalukaNameMr() {
		return talukaNameMr;
	}
	public void setTalukaNameMr(String talukaNameMr) {
		this.talukaNameMr = talukaNameMr;
	}
	
	

}
