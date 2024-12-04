package com.mahait.gov.in.model;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.Data;
@Data
public class MstSchemeModel implements Serializable{
	
	
private static final long serialVersionUID = 1L;
	
    private BigInteger schemeId;	
    private String majorHead;
	private String subMajorHead;
	private String minorHead;
	private String subMinorHead;
	private String subHead;
	private String demandCode;
	private String schemeCode;
	private String schemeName;
	private String schemeType;
	private char plan;
	private char isactive;
	private BigInteger finYear;
	private String financialYear;
	private String dcpsDdoCode;
	private BigInteger dcpsDdoSchemesId;
	private String dcpsDdoSchemeCode;
	private BigInteger billGrpId;
	private String billDesc;
	
	
	
	
}
