package com.mahait.gov.in.nps.entity;


import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="NSDL_DH",schema="public")
public class NSDLDHDtlsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NSDL_DH_id")
    private int nsdlDhId;  
	
	@Column(name="SR_NO")
    private int srno;
	
	@Column(name = "HEADER_NAME",length=2)
	private String headerName;
	
	@Column(name="DH_NO")
	private String dhNo;
	
	@Column(name = "DH_COL2")
	private String dhCol2;
	
	@Column(name = "DH_DDO_REG_NO")
	private String dhDDORegno;
	
	@Column(name = "BH_SD_COUNT")
	private BigInteger bhSDCnt;
	
	@Column(name = "DH_EMP_AMOUNT",length=11,precision=2)
	private Double dhEmpAmt;
	
	@Column(name = "DH_EMPLR_AMOUNT" ,length=11,precision=2)
	private Double dhEmplrAmt;
	

	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "DH_STATUS")
	private String dhStatus;
	
	@Column(name = "IS_LEGACY_DATA")
	private Character isLegacyData;

	public int getNsdlDhId() {
		return nsdlDhId;
	}

	public void setNsdlDhId(int nsdlDhId) {
		this.nsdlDhId = nsdlDhId;
	}

	public int getSrno() {
		return srno;
	}

	public void setSrno(int srno) {
		this.srno = srno;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getDhNo() {
		return dhNo;
	}

	public void setDhNo(String dhNo) {
		this.dhNo = dhNo;
	}

	public String getDhCol2() {
		return dhCol2;
	}

	public void setDhCol2(String dhCol2) {
		this.dhCol2 = dhCol2;
	}

	public String getDhDDORegno() {
		return dhDDORegno;
	}

	public void setDhDDORegno(String dhDDORegno) {
		this.dhDDORegno = dhDDORegno;
	}

	public BigInteger getBhSDCnt() {
		return bhSDCnt;
	}

	public void setBhSDCnt(BigInteger bhSDCnt) {
		this.bhSDCnt = bhSDCnt;
	}

	

	public Double getDhEmpAmt() {
		return dhEmpAmt;
	}

	public void setDhEmpAmt(Double dhEmpAmt) {
		this.dhEmpAmt = dhEmpAmt;
	}

	public Double getDhEmplrAmt() {
		return dhEmplrAmt;
	}

	public void setDhEmplrAmt(Double dhEmplrAmt) {
		this.dhEmplrAmt = dhEmplrAmt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDhStatus() {
		return dhStatus;
	}

	public void setDhStatus(String dhStatus) {
		this.dhStatus = dhStatus;
	}

	public Character getIsLegacyData() {
		return isLegacyData;
	}

	public void setIsLegacyData(Character isLegacyData) {
		this.isLegacyData = isLegacyData;
	}
		
}
