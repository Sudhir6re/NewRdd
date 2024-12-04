package com.mahait.gov.in.model;

import lombok.Data;

@Data
public class MpgSchemeBillGroupModel {
	
private static long serialVersionUID = 1L;
	
	/*private String majorHead;
	private String subMajorHead;
	private String minorHead;
	private String subMinorHead;
	private String subHead;
	private String demandCode;*/
	private String sevaarthId;
	private Long schemebillGroupId;
	private String  billDescription;
	private String ddoCode;
	private String schemeCode;
	private String schemeName;
	private char isactive;
	private Long districtId;
	private Long billGroupId;
	private Long ddoMapId;
	private Long schemeId;
	private String dcpsEmpIdstoBeDetached;
	private String dcpsEmpIdstoBeAttached;
	private String status;
	private Long type;
	private String postIdstoBeDetached;
	private String postIdstoBeAttached;
	
	

}
