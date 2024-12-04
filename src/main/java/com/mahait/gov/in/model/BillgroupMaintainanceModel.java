package com.mahait.gov.in.model;


import java.util.List;

import lombok.Data;
@Data
public class BillgroupMaintainanceModel

{
	
private Long schemeId;
private String dcpsDdoSchemeCode;
private String description;
private String typeOfPost;
private String group;
private String dcpsDdoBillTypeOfPost;
private String dcpsDdoSubSchemeCode;

private String sevaarthId;
private String  billDescription;
private String ddoCode;
private String schemeCode;
private String schemeName;
private char isactive;
private Integer districtId;
private Long billGroupId;
private Integer ddoMapId;

private String dcpsEmpIdstoBeDetached;
private String dcpsEmpIdstoBeAttached;
private String status;
private List<Rltdcpsbillgroupclassmodel> billgroupclass;



}
