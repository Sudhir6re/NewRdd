package com.mahait.gov.in.model;

import java.io.Serializable;

public class DisplayOuterReportModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int paybillgenerationtrnid;
	private String billdescription;
	//start
	private String deptalldetNm;  // edp desc
	private int type;
	private int deptallowdeducid;
	private String tempvalue; // insted of subdetail head and headofaccountcode
	private String tempempty;
	private String headAccountCode;
	//end
	public int getPaybillgenerationtrnid() {
		return paybillgenerationtrnid;
	}
	public void setPaybillgenerationtrnid(int paybillgenerationtrnid) {
		this.paybillgenerationtrnid = paybillgenerationtrnid;
	}
	public String getBilldescription() {
		return billdescription;
	}
	public void setBilldescription(String billdescription) {
		this.billdescription = billdescription;
	}
	public String getDeptalldetNm() {
		return deptalldetNm;
	}
	public void setDeptalldetNm(String deptalldetNm) {
		this.deptalldetNm = deptalldetNm;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDeptallowdeducid() {
		return deptallowdeducid;
	}
	public void setDeptallowdeducid(int deptallowdeducid) {
		this.deptallowdeducid = deptallowdeducid;
	}
	public String getTempvalue() {
		return tempvalue;
	}
	public void setTempvalue(String tempvalue) {
		this.tempvalue = tempvalue;
	}
	public String getTempempty() {
		return tempempty;
	}
	public void setTempempty(String tempempty) {
		this.tempempty = tempempty;
	}
	
	public String getHeadAccountCode() {
		return headAccountCode;
	}
	public void setHeadAccountCode(String headAccountCode) {
		this.headAccountCode = headAccountCode;
	}
	@Override
	public String toString() {
		return "DisplayOuterReportModel [paybillgenerationtrnid=" + paybillgenerationtrnid + ", billdescription="
				+ billdescription + ", deptalldetNm=" + deptalldetNm + ", type=" + type + ", deptallowdeducid="
				+ deptallowdeducid + ", tempvalue=" + tempvalue + ", tempempty=" + tempempty + "]";
	}
	
	
	

}
