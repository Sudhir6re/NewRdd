package com.mahait.gov.in.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;



@Data
public class DdoOfficeModel {
	
	@JsonProperty("dcpsDdoOfficeIdPk")
    private String dcpsDdoOfficeIdPk;
	 
	 
    private String dcpsDdoCode;
    private String dcpsDdoOfficeName;
    private String dcpsDdoOfficeDdoFlag;
    private String dcpsDdoOfficeState;
    private String dcpsDdoOfficeDistrict;
    private String dcpsDdoOfficeTaluka;
    private String dcpsDdoOfficeTown;
    private String dcpsDdoOfficeVillage;
    private String dcpsDdoOfficeAddress1;
    private Long dcpsDdoOfficePin;
    private String dcpsDdoOfficeCityClass;
    private Long dcpsDdoOfficeTelNo1;
    private Long dcpsDdoOfficeTelNo2;
    private Long dcpsDdoOfficeFax;
    private String dcpsDdoOfficeEmail;
    private String dcpsDdoOfficeGrant;
    private Date CreatedDate;
    private Long statusFlag;

}

