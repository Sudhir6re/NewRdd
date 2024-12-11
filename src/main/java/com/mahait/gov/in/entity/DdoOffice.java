package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "MST_DCPS_DDO_OFFICE")
public class DdoOffice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DCPS_DDO_OFFICE_MST_ID", precision = 20, scale = 0)
	private Long dcpsDdoOfficeIdPk;

	@Column(name = "DDO_CODE")
	private String dcpsDdoCode;

	@Column(name = "OFF_NAME")
	private String dcpsDdoOfficeName;

	@Column(name = "DDO_OFFICE")
	private String dcpsDdoOfficeDdoFlag;

	@Column(name = "STATE")
	private String dcpsDdoOfficeState;

	@Column(name = "DISTRICT")
	private String dcpsDdoOfficeDistrict;

	@Column(name = "TALUKA")
	private String dcpsDdoOfficeTaluka;

	@Column(name = "TOWN")
	private String dcpsDdoOfficeTown;

	@Column(name = "VILLAGE")
	private String dcpsDdoOfficeVillage;

	@Column(name = "ADDRESS1")
	private String dcpsDdoOfficeAddress1;

	@Column(name = "ADDRESS2")
	private String dcpsDdoOfficeAddress2;

	@Column(name = "OFFICE_PIN")
	private Long dcpsDdoOfficePin;

	@Column(name = "OFFICE_CITY_CLASS")
	private String dcpsDdoOfficeCityClass;

	@Column(name = "TEL_NO1")
	private Long dcpsDdoOfficeTelNo1;

	@Column(name = "TEL_NO2")
	private Long dcpsDdoOfficeTelNo2;

	@Column(name = "FAX")
	private Long dcpsDdoOfficeFax;

	@Column(name = "EMAIL")
	private String dcpsDdoOfficeEmail;

	@Column(name = "TRIBAL_AREA_FLAG")
	private String dcpsDdoOfficeTribalFlag;

	@Column(name = "HILLY_AREA_FLAG")
	private String dcpsDdoOfficeHillyFlag;

	@Column(name = "NAXALITE_AREA_FLAG")
	private String dcpsDdoOfficeNaxaliteAreaFlag;

	@Column(name = "LANG_ID")
	private Long LangId;

	@Column(name = "LOC_ID")
	private Long LocId;

	@Column(name = "DB_ID")
	private Long DbId;

	@Column(name = "CREATED_POST_ID")
	private Long PostId;

	@Column(name = "CREATED_USER_ID")
	private Long UserId;

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date CreatedDate;

	@Column(name = "UPDATED_POST_ID")
	private Long UpdatedPostId;

	@Column(name = "UPDATED_USER_ID")
	private Long UpdatedUserId;

	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date UpdatedDate;

	@Column(name = "DICE_CODE")
	private Long DiceCode;

	@Column(name = "STATUS_FLAG")
	private Long statusFlag;

	@Column(name = "GRANT_APPLICABLE")
	private String dcpsDdoOfficeGrant;

	@Column(name = "REASON_FOR_REJECTION")
	private String reasonForRejection;

	@Column(name = "UNIQUE_INSTITUTE_NO")
	private String uniqueInstituteNo;

}