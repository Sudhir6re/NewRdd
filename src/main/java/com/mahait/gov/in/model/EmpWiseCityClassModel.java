package com.mahait.gov.in.model;

import java.util.ArrayList;
import java.util.List;

public class EmpWiseCityClassModel {
	
	
	public String sevaarthId;
	private Integer employeeId;
	private String empName;
	private boolean checkboxid;
	private String districtId;
	private String talukaId;
	private String cityClass;
	
	public List<EmpWiseCityClassModel> emplist=new ArrayList<>();

	public String getSevaarthId() {
		return sevaarthId;
	}

	public void setSevaarthId(String sevaarthId) {
		this.sevaarthId = sevaarthId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public boolean isCheckboxid() {
		return checkboxid;
	}

	public void setCheckboxid(boolean checkboxid) {
		this.checkboxid = checkboxid;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getTalukaId() {
		return talukaId;
	}

	public void setTalukaId(String talukaId) {
		this.talukaId = talukaId;
	}

	public String getCityClass() {
		return cityClass;
	}

	public void setCityClass(String cityClass) {
		this.cityClass = cityClass;
	}

	public List<EmpWiseCityClassModel> getEmplist() {
		return emplist;
	}

	public void setEmplist(List<EmpWiseCityClassModel> emplist) {
		this.emplist = emplist;
	}
	
	
	
	
	

}
