package com.mahait.gov.in.nps.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "frm_form_s1_dtls_1", schema = "public")
public class FormS1DetailsEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_s1_id", nullable = false)
    private Long formS1Id;

    @Column(name = "sevarth_id", nullable = false, length = 20)
    private String sevarthId;

    @Column(name = "dcps_id", nullable = false, length = 21)
    private String dcpsId;

    @Column(name = "designation", length = 100)
    private String designation;

    @Column(name = "title", length = 8)
    private String title;

    @Column(name = "emp_name", length = 90)
    private String empName;

    @Column(name = "emp_first_name", length = 30)
    private String empFirstName;

    @Column(name = "emp_middle_name", length = 30)
    private String empMiddleName;

    @Column(name = "emp_last_name", length = 30)
    private String empLastName;

    @Column(name = "father_name_first", length = 30)
    private String fatherNameFirst;

    @Column(name = "father_name_middle", length = 30)
    private String fatherNameMiddle;

    @Column(name = "father_name_last", length = 30)
    private String fatherNameLast;

    @Column(name = "mother_name_first", length = 30)
    private String motherNameFirst;

    @Column(name = "emp_dob", length = 10)
    private String empDob;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "dto_code", nullable = false, length = 7)
    private String dtoCode;

    @Column(name = "ddo_code", length = 10)
    private String ddoCode;

    @Column(name = "pan_no", length = 10)
    private String panNo;

    @Column(name = "aadhar_no", length = 12)
    private String aadharNo;

    @Column(name = "present_address_flatno", length = 30)
    private String presentAddressFlatNo;

    @Column(name = "present_address_building", length = 30)
    private String presentAddressBuilding;

    @Column(name = "present_address_taluka", length = 30)
    private String presentAddressTaluka;

    @Column(name = "present_address_landmark", length = 30)
    private String presentAddressLandmark;

    @Column(name = "present_address_district", length = 30)
    private String presentAddressDistrict;

    @Column(name = "present_address_state", length = 2)
    private String presentAddressState;

    @Column(name = "present_address_country", length = 2)
    private String presentAddressCountry;

    @Column(name = "present_address_pincode", length = 6)
    private String presentAddressPincode;

    @Column(name = "permanent_address_flatno", length = 30)
    private String permanentAddressFlatNo;

    @Column(name = "permanent_address_building", length = 30)
    private String permanentAddressBuilding;

    @Column(name = "permanent_address_taluka", length = 30)
    private String permanentAddressTaluka;

    @Column(name = "permanent_address_landmark", length = 30)
    private String permanentAddressLandmark;

    @Column(name = "permanent_address_district", length = 30)
    private String permanentAddressDistrict;

    @Column(name = "permanent_address_state", length = 2)
    private String permanentAddressState;

    @Column(name = "permanent_address_country", length = 2)
    private String permanentAddressCountry;

    @Column(name = "permanent_address_pincode", length = 6)
    private String permanentAddressPincode;

    @Column(name = "phone_no", length = 14)
    private String phoneNo;

    @Column(name = "mobile_no", length = 14)
    private String mobileNo;

    @Column(name = "email_id", length = 90)
    private String emailId;

    @Column(name = "sms_sub_flag", length = 1)
    private String smsSubFlag;

    @Column(name = "email_sub_flag", length = 1)
    private String emailSubFlag;

    @Column(name = "hindi_sub_flag", length = 1, columnDefinition = "bpchar default 'N'")
    private String hindiSubFlag;

    @Column(name = "emp_doj", length = 10)
    private String empDoj;

    @Column(name = "emp_dor", length = 10)
    private String empDor;

    @Column(name = "emp_class", length = 1)
    private String empClass;

    @Column(name = "emp_dept", length = 40)
    private String empDept;

    @Column(name = "emp_ministry", length = 40)
    private String empMinistry;

    @Column(name = "emp_ddo", length = 75)
    private String empDdo;

    @Column(name = "pay_scale", length = 50)
    private String payScale;

    @Column(name = "basic_salary", length = 10)
    private String basicSalary;

    @Column(name = "ppan", length = 16)
    private String ppan;

    @Column(name = "bank_details_flag", length = 1)
    private String bankDetailsFlag;

    @Column(name = "bank_details_type", length = 20)
    private String bankDetailsType;

    @Column(name = "bank_account_no", length = 30)
    private String bankAccountNo;

    @Column(name = "bank_name", length = 30)
    private String bankName;

    @Column(name = "bank_branch", length = 30)
    private String bankBranch;

    @Column(name = "bank_address", length = 50)
    private String bankAddress;

    @Column(name = "bank_state", length = 2, columnDefinition = "bpchar default '19'")
    private String bankState;

    @Column(name = "bank_country", length = 2, columnDefinition = "bpchar default 'IN'")
    private String bankCountry;

    @Column(name = "bank_pin", length = 8)
    private String bankPin;

    @Column(name = "emp_number_nominee", length = 1)
    private String empNumberNominee;

    @Column(name = "nominee_name_1", length = 30)
    private String nomineeName1;

    @Column(name = "nominee_dob_1", length = 12)
    private String nomineeDob1;

    @Column(name = "nominee_realtion_1", length = 30)
    private String nomineeRelation1;

    @Column(name = "nominee_percent_share_1", length = 3)
    private String nomineePercentShare1;

    @Column(name = "nominee_gardian_name_1", length = 30)
    private String nomineeGuardianName1;

    @Column(name = "nominee_render_condition_1", length = 50)
    private String nomineeRenderCondition1;

    @Column(name = "nominee_name_2", length = 30)
    private String nomineeName2;

    @Column(name = "nominee_dob_2", length = 10)
    private String nomineeDob2;

    @Column(name = "nominee_realtion_2", length = 30)
    private String nomineeRelation2;

    @Column(name = "nominee_percent_share_2", length = 3)
    private String nomineePercentShare2;

    @Column(name = "nominee_gardian_name_2", length = 30)
    private String nomineeGuardianName2;

    @Column(name = "nominee_render_condition_2", length = 50)
    private String nomineeRenderCondition2;

    @Column(name = "nominee_name_3", length = 30)
    private String nomineeName3;

    @Column(name = "nominee_dob_3", length = 10)
    private String nomineeDob3;

    @Column(name = "nominee_realtion_3", length = 30)
    private String nomineeRelation3;

    @Column(name = "nominee_percent_share_3", length = 3)
    private String nomineePercentShare3;

    @Column(name = "nominee_gardian_name_3", length = 30)
    private String nomineeGuardianName3;

    @Column(name = "nominee_render_condition_3", length = 50)
    private String nomineeRenderCondition3;

    @Column(name = "pran_no", length = 50)
    private String pranNo;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "bankifsccode", length = 11)
    private String bankIfscCode;

    @Column(name = "empmaritalstatus", length = 1)
    private String empMaritalStatus;

    @Column(name = "ddo_reg_no", nullable = false, length = 10)
    private String ddoRegNo;

    @Column(name = "ackno", length = 17)
    private String ackno;

    @Column(name = "file_verify_status", length = 1)
    private String fileVerifyStatus;
    
    @Column(name = "nsdl_status")
    private Integer nsdlStatus;
    
    @Column(name = "batch_id", length = 27)
    private String batchId;
    
    @Column(name = "photo_attachment_name", length = 50)
    private String photoAttachmentName;
    
    
    @Column(name = "sign_attachment_name", length = 50)
    private String signAttachmentName;
    
    
    @Column(name = "images_location_path", length = 200)
    private String imagesLocationPath;
    
    @Column(name = "pran_status")
    private Integer pranStatus;
    
    @Column(name = "nominee_address_1", length = 40)
    private String nomineeAddress1;
    
    @Column(name = "nominee_address_2", length = 40)
    private String nomineeAddress2;
    
    @Column(name = "nominee_address_3", length = 40)
    private String nomineeAddress3;
    
    
    @Column(name = "status_flag",columnDefinition = "integer default 1")
    private Integer statusFlag;
    

}
