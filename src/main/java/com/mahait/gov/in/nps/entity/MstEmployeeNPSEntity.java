package com.mahait.gov.in.nps.entity;

import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="employee_nps_mst",schema="public")
public class MstEmployeeNPSEntity {
	
	/*@OneToOne(cascade = CascadeType.ALL, mappedBy = "mstEmployeeNPSEntity", orphanRemoval = true) 
	  private List<MstEmployeeEntity> mstEmployeeEntity = new ArrayList<>();*/

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_nps_id")
	private Long employeeNPSId;
	

	@Column(name="employee_id")
	private Long employeeId;

	@Column(name="sevaarth_id")
	private String sevaarthId;

	@Column(name="is_active")
	private Integer isActive;
	
	@Column(name="employee_full_name")
	private String employeeFullName;
	
	@Column(name = "salutation")
	private String salutation;
	
	@Column(name = "ppan_no")
	private String ppanNo;
	
	@Column(name = "employee_mother_name")
	private String employeeMotherName;
	
	@Column(name = "employee_first_name")
	private String employeeFirstName;
	
	@Column(name = "employee_middle_name")
	private String employeeMiddleName;
	
	@Column(name = "employee_last_name")
	private String employeeLastName;
	
	@Column(name = "employee_date_of_birth")
	private Date employeeDOB;
	
	@Column(name = "employee_birth_place ")
	private String employeeBirthPlace;
	
	@Column(name = "employee_gender")
	private char employeeGender;
	
	@Column(name = "employee_father_husband_name")
	private String employeeFatherHubandName;
	
	@Column(name = "employee_spouse_name")
	private String employeeSpouseName;
	
	@Column(name = "employee_pan")
	private String employeePan;
	
	@Column(name = "employee_aadhar")
	private String employeeAadhar;
	
	@Column(name = "employee_date_of_joining")
	private Date employeeDOJ;
	
	@Column(name = "employee_date_of_retirement")
	private Date employeeDateOfRetirement;
	
	@Column(name = "employee_employement_class")
	private String employeeEmploymentClass;
	
	@Column(name = "employee_bank_account_no")
	private String employeeBankAccountNo;
	
	@Column(name = "employee_bank_name")
	private String employeeBankName;
	
	@Column(name = "employee_bank_branch_name")
	private String employeeBankBranchName;
	
	@Column(name = "employee_bank_pin_code")
	private String employeeBankPinCode;
	
	@Column(name = "employee_bank_branch_address")
	private String employeeBankBankAddress;
	
	@Column(name = "ifsc_code")
	private String IFSCCode;
	
	@Column(name = "flat_unit_no")
	private String flatUnitNo;
	
	@Column(name = "building_name")
	private String buildingName;
	
	@Column(name = "locality")
	private String locality;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "pin_code")
	private Long pinCode;
	
	@Column(name = "employee_p_flat_unit_no")
	private String empPermanentFlatUnitNo;
	
	@Column(name = "employee_p_building_name")
	private String empPermanentBuildingName;
	
	@Column(name = "employee_p_locality")
	private String empPermanentLocality;
	
	@Column(name = "employee_p_district")
	private String empPermanentDistrict;
	
	@Column(name = "employee_p_state")
	private String empPermanentState;
	
	@Column(name = "employee_p_country")
	private String empPermanentCountry;
	
	@Column(name = "employee_p_pin_code")
	private String empPermanentPinCode;
	
	@Column(name = "employee_std_code")
	private String empSTDCode;
	
	@Column(name = "employee_phone_no")
	private String empPhoneNo;
	
	@Column(name = "employee_mobile_no")
	private String empMobileNo;
	
	@Column(name = "employee_email_id")
	private String empEmailId;
	
	@Column(name = "employee_nominee_1_name")
	private String empNominee1Name;
	
	@Column(name = "employee_nominee_1_dob")
	private Date empNominee1DOB;
	
	@Column(name = "employee_nominee_1_relationship")
	private String empNominee1relationship;
	
	@Column(name = "employee_nominee_1_share")
	private String empNominee1Share;
	
	@Column(name = "employee_nominee_1_guard_name")
	private String empNominee1GuardName;
	
	@Column(name = "employee_nominee_1_invalid_condn")
	private String empNominee1InvalidCondn;
	
	@Column(name = "employee_nominee_2_name ")
	private String empNominee2Name;
	
	@Column(name = "employee_nominee_2_dob")
	private Date empNominee2DOB;
	
	@Column(name = "employee_nominee_2_relationship")
	private String empNominee2relationship;
	
	@Column(name = "employee_nominee_2_share")
	private String empNominee2Share;
	
	@Column(name = "employee_nominee_2_guard_name")
	private String empNominee2GuardName;
	
	@Column(name = "employee_nominee_2_invalid_condn")
	private String empNominee2InvalidCondn;

	@Column(name = "employee_nominee_3_name ")
	private String empNominee3Name;
	
	@Column(name = "employee_nominee_3_dob")
	private Date empNominee3DOB;
	
	@Column(name = "employee_nominee_3_relationship")
	private String empNominee3relationship;
	
	@Column(name = "employee_nominee_3_share")
	private String empNominee3Share;
	
	@Column(name = "employee_nominee_3_guard_name")
	private String empNominee3GuardName;
	
	@Column(name = "employee_nominee_3_invalid_condn")
	private String empNominee3InvalidCondn;
	
	@Column(name = "nsdl_status")
	private String NSDLStatus;
	

	
	@Column(name = "country_of_tax")
	private String countryofTax;
	
	@Column(name = "address_of_tax")
	private String addressOfTax;
	
	@Column(name = "city_of_tax")
	private String cityOfTax;
	
	@Column(name = "state_of_tax")
	private String stateofTax;
	
	@Column(name = "post_code_of_tax")
	private String postCodeofTax;
	
	@Column(name = "tin_or_pan")
	private String tinOrPan;
	
	@Column(name = "tin_country")
	private String tinCountry;
	
	@Column(name = "ddo_reg_no")
	private String ddoRegNo;
	
	@Column(name = "ddo_code")
	private String ddoCode;
	
	
	
	@Column(name = "sot_language_code")
	private String sotLangCode;
	
	
	@Column(name = "pay_scale_desc")
	private String payScaleDesc;
	
	@Column(name = "file_sec_type")
	private String fileSecType;
	
	
	
	@Column(name = "us_person")
	private String usPerson;
	
	
	@Column(name = "sms_s_flag")
	private String smsSFlag;
	
	
	@Column(name = "email_s_flag")
	private String emailSflag;
	
	@Column(name = "hindi_flag")
	private String hindiFlag;
	
	@Column(name = "sec_type_flag")
	private String secTypeFlag;
	
	@Column(name = "fund_ratio")
	private String fundRatio;
	
	
	@Column(name = "min_upload_indicator")
	private String minUploadIndicator;
	
	@Column(name = "com_form_flag")
	private String comformFlag;
	
	@Column(name = "nationality")
	private String nationality;
	
	
	@Column(name = "fatca_dec_count")
	private Integer fatcaDecCount;
	
	
	@Column(name = "fatca_dec")
	private String fatcadec;
	
	
	@Column(name = "nodal_consent")
	private String nodalconsent;
	
	
	@Column(name="pran_no ")
	private String pranNo;
	

	@Column(name = "employee_marital_status")
	private String employeeMaritalStatus;
	
	@Column(name = "created_user_id")
	private Integer createduserId;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_user_id")
	private Integer updateduserId;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "employee_photo_attachment")
	private String employeePhotoAttachment;
	
	@Column(name = "employee_sign_attachment")
	private String employeeSignAttachment;
	
	@Column(name = "display_name_on_PRANCard")
	private Integer displayNameonPranCard;
	
	@Column(name = "dob_proof")
	private Integer dobProof;
	
	@Column(name = "educ_qual")
	private Integer eduQual;

	@Column(name = "income_range")
	private Integer incomeRange;
	
	
	
	@Column(name = "num_nominess")
	private Integer numNominess;
	
	
	
	@Column(name = "ack_no")
	private BigInteger ackNo;
	
	
	@Column(name = "title")
	private String title;
	
	
	@Column(name = "dto_reg_no")
	private String dtoRegNo;
	
	
	
	 

	@Column(name="nominee1_major_minor")
	private String nominee1MajorMinor;
	
	@Column(name="nominee2_major_minor")
	private String nominee2MajorMinor;
	
	@Column(name="nominee3_major_minor")
	private String nominee3MajorMinor;
	
	
	
	

	@Column(name="nominee1_address")
	private String nominee1Address;
	
	@Column(name="nominee2_address")
	private String nominee2Address;
	
	@Column(name="nominee3_address")
	private String nominee3Address;
	
	
	
	
	@Column(name="emp_class")
	private Integer empClass;
	
	
	
	
	
	
	
	
	

	
}
