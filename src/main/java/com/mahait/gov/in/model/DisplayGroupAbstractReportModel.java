package com.mahait.gov.in.model;

import java.io.Serializable;

public class DisplayGroupAbstractReportModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int paybillgenerationtrnid;
	private String billdescription;
	//start
	private String deptalldetNm;  // edp desc
	private int type;
	private int id;
	private int paybillmonth;
	private int paybillyear;
	private int deptallowdeducid;
	private String tempvalue; // insted of subdetail head and headofaccountcode
	private String tempempty;
	private String headAccountCode;
	private String allowavalueA;
	private String allowavalueB;
	private String allowavalueC;
	private String allowavalueD;
	private String allowavalueE;
	private String allowavalue;
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
	
	
	public String getAllowavalueA() {
		return allowavalueA;
	}
	public void setAllowavalueA(String allowavalueA) {
		this.allowavalueA = allowavalueA;
	}
	public String getAllowavalueB() {
		return allowavalueB;
	}
	public void setAllowavalueB(String allowavalueB) {
		this.allowavalueB = allowavalueB;
	}
	public String getAllowavalueC() {
		return allowavalueC;
	}
	public void setAllowavalueC(String allowavalueC) {
		this.allowavalueC = allowavalueC;
	}
	public String getAllowavalueD() {
		return allowavalueD;
	}
	public void setAllowavalueD(String allowavalueD) {
		this.allowavalueD = allowavalueD;
	}
	public String getAllowavalueE() {
		return allowavalueE;
	}
	public void setAllowavalueE(String allowavalueE) {
		this.allowavalueE = allowavalueE;
	}
	public String getAllowavalue() {
		return allowavalue;
	}
	public void setAllowavalue(String allowavalue) {
		this.allowavalue = allowavalue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getPaybillmonth() {
		return paybillmonth;
	}
	public void setPaybillmonth(int paybillmonth) {
		this.paybillmonth = paybillmonth;
	}
	public int getPaybillyear() {
		return paybillyear;
	}
	public void setPaybillyear(int paybillyear) {
		this.paybillyear = paybillyear;
	}
	@Override
	public String toString() {
		return "DisplayGroupAbstractReportModel [paybillgenerationtrnid=" + paybillgenerationtrnid + ", billdescription="
				+ billdescription + ", deptalldetNm=" + deptalldetNm + ", type=" + type + ", deptallowdeducid="
				+ deptallowdeducid + ", tempvalue=" + tempvalue + ", tempempty=" + tempempty + "]";
	}
	
	
	

}
