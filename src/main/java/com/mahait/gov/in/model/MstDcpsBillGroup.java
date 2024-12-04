package com.mahait.gov.in.model;

import java.util.Date;

import lombok.Data;
@Data
public class MstDcpsBillGroup {

	private Long dcpsDdoBillGroupId;
	private String dcpsDdoCode;
	private String dcpsDdoBillDescription;
	private String dcpsDdoBillSchemeName;
	private String dcpsDdoSchemeCode;
	private String dcpsDdoBillTypeOfPost;
	private Long subBGOrNot;
	private Long LangId;
	private Long LocId;
	private Long DbId;
	private Long PostId;
	private Long UserId;
	private Date CreatedDate;
	private Long UpdatedPostId;
	private Long UpdatedUserId;
	private Date UpdatedDate;
	private Long billNo;
	private Character billDeleted;
	private Character billDcps;
	private String dcpsDdoSubSchemeCode;
	
}
