package com.mahait.gov.in.model;

import java.io.Serializable;

public class DDOScreenModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer departmentId;
	private Integer subDepartmentId;
	private String ddoName;
	private Character optradio;
	private String ddoCode;
	private Integer level;
	
	private Integer ddoRegID;
	private String deptName;
	private String subDeptName;
	private String subDeptShortName;
	private Integer levelHier;
	private int isActive;
	private String officeName;
	private int rId;
	
	private int treasuryCode;
	
	private String ddoCityCategory;
	private String cityGroup;
	
	
	
	
	public String getCityGroup() {
		return cityGroup;
	}
	public void setCityGroup(String cityGroup) {
		this.cityGroup = cityGroup;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	


	private int roleId;
	private String roleName;
	private String roleDesc;
	
	private String roleLevel;
	private int districtCode;
	private int countryCode;
	private int stateCode;
	private int talukaId;
	public Integer talukaCode;
	private String talukaConcatId;
	private char is_ddo_exist;
	private String approval_pending_at_level;
	private int parentDistrictCode;
	private int parentTalukaCode;
	private String villageName;
	private String office_name;
	
	
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public Integer getDdoRegID() {
		return ddoRegID;
	}
	public void setDdoRegID(Integer ddoRegID) {
		this.ddoRegID = ddoRegID;
	}
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getSubDepartmentId() {
		return subDepartmentId;
	}
	public void setSubDepartmentId(Integer subDepartmentId) {
		this.subDepartmentId = subDepartmentId;
	}
	public String getDdoName() {
		return ddoName;
	}
	public void setDdoName(String ddoName) {
		this.ddoName = ddoName;
	}
	public Character getOptradio() {
		return optradio;
	}
	public void setOptradio(Character optradio) {
		this.optradio = optradio;
	}
	public String getDdoCode() {
		return ddoCode;
	}
	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSubDeptName() {
		return subDeptName;
	}
	public void setSubDeptName(String subDeptName) {
		this.subDeptName = subDeptName;
	}
	public Integer getLevelHier() {
		return levelHier;
	}
	public void setLevelHier(Integer levelHier) {
		this.levelHier = levelHier;
	}
	public String getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	public int getTalukaId() {
		return talukaId;
	}
	public void setTalukaId(int talukaId) {
		this.talukaId = talukaId;
	}
	public String getTalukaConcatId() {
		return talukaConcatId;
	}
	public void setTalukaConcatId(String talukaConcatId) {
		this.talukaConcatId = talukaConcatId;
	}
	public char getIs_ddo_exist() {
		return is_ddo_exist;
	}
	public void setIs_ddo_exist(char is_ddo_exist) {
		this.is_ddo_exist = is_ddo_exist;
	}
	public String getApproval_pending_at_level() {
		return approval_pending_at_level;
	}
	public void setApproval_pending_at_level(String approval_pending_at_level) {
		this.approval_pending_at_level = approval_pending_at_level;
	}
	
	public int getParentDistrictCode() {
		return parentDistrictCode;
	}
	public void setParentDistrictCode(int parentDistrictCode) {
		this.parentDistrictCode = parentDistrictCode;
	}
	public int getParentTalukaCode() {
		return parentTalukaCode;
	}
	public void setParentTalukaCode(int parentTalukaCode) {
		this.parentTalukaCode = parentTalukaCode;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	public int getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSubDeptShortName() {
		return subDeptShortName;
	}
	public void setSubDeptShortName(String subDeptShortName) {
		this.subDeptShortName = subDeptShortName;
	}
	public Integer getTalukaCode() {
		return talukaCode;
	}
	public void setTalukaCode(Integer talukaCode) {
		this.talukaCode = talukaCode;
	}
	public int getTreasuryCode() {
		return treasuryCode;
	}
	public void setTreasuryCode(int treasuryCode) {
		this.treasuryCode = treasuryCode;
	}
	public String getDdoCityCategory() {
		return ddoCityCategory;
	}
	public void setDdoCityCategory(String ddoCityCategory) {
		this.ddoCityCategory = ddoCityCategory;
	}
	
	

	
	
	
	
	
	
	
}
