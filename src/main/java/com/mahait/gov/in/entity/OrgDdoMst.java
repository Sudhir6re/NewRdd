package com.mahait.gov.in.entity;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "ORG_DDO_MST")
public class OrgDdoMst {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ddo_id")
	    private Long ddoId;

	    @Column(name = "ddo_code", nullable = false, length = 15)
	    private String ddoCode;

	    @Column(name = "ddo_name", length = 500)
	    private String ddoName;

	    @Column(name = "ddo_personal_name", length = 500)
	    private String ddoPersonalName;

	    @Column(name = "post_id")
	    private Long postId;

	    @Column(name = "attachment_id")
	    private Long attachmentId;

	    @Column(name = "lang_id", nullable = false)
	    private Short langId;

	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Column(name = "start_date")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date startDate;

	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Column(name = "end_date")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date endDate;
	    
	    

	    @Column(name = "activate_flag")
	    private Short activateFlag;

	    @Column(name = "created_by", nullable = false)
	    private Long createdBy;

	    @Column(name = "created_by_post", nullable = false)
	    private Long createdByPost;

	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    @Column(name = "created_date", nullable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdDate;

	    @Column(name = "updated_by")
	    private Long updatedBy;

	    @Column(name = "updated_by_post")
	    private Long updatedByPost;

	    @Column(name = "updated_date")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date updatedDate;

	    @Column(name = "db_id", nullable = false)
	    private Short dbId;

	    @Column(name = "short_name", length = 150)
	    private String shortName;

	    @Column(name = "major_head", length = 4)
	    private String majorHead;

	    @Column(name = "demand", length = 3)
	    private String demand;

	    @Column(name = "ddo_no")
	    private Integer ddoNo;

	    @Column(name = "cardex_no")
	    private Short cardexNo;

	    @Column(name = "trn_counter")
	    private Short trnCounter;

	    @Column(name = "admin_flag")
	    private Short adminFlag;

	    @Column(name = "office_code", length = 20)
	    private String officeCode;

	    @Column(name = "location_code", nullable = false, length = 20)
	    private String locationCode;

	    @Column(name = "dept_loc_code", length = 20)
	    private String deptLocCode;

	    @Column(name = "hod_loc_code", length = 20)
	    private String hodLocCode;
	    
	    
	    @Column(name = "is_co")
	    private Short isCo;

	    @Column(name = "is_cs")
	    private Short isCs;

	    @Column(name = "type")
	    private Short type;

	    @Column(name = "verified")
	    private Integer verified;

	    @Column(name = "dsgn_code", length = 45)
	    private String dsgnCode;

	    @Column(name = "dsgn_name", length = 400)
	    private String dsgnName;

	    @Column(name = "ddo_office", length = 300)
	    private String ddoOffice;

	    @Column(name = "sub_office_code", length = 20)
	    private String subOfficeCode;

	    @Column(name = "attached_parent_loc_code", length = 20)
	    private String attachedParentLocCode;

	    @Column(name = "office_unique_code", length = 50)
	    private String officeUniqueCode;

	    @Column(name = "remarks", length = 200)
	    private String remarks;

	    @Column(name = "verified_date")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date verifiedDate;

	    @Column(name = "fixedid")
	    private Long fixedid;

	    @Column(name = "tan_no", length = 20)
	    private String tanNo;

	    @Column(name = "itawardcircle", length = 20)
	    private String itawardcircle;

	    @Column(name = "bank_name", length = 20)
	    private String bankName;

	    @Column(name = "branch_name", length = 20)
	    private String branchName;

	    @Column(name = "account_no", length = 20)
	    private String accountNo;

	    @Column(name = "ifs_code", length = 20)
	    private String ifsCode;

   
	    @Column(name = "ddo_type")
	    private Long ddoType;

	    @Column(name = "admin_dept_type")
	    private Long adminDeptType;

	    @Column(name = "institute_type_id")
	    private Long instituteTypeId;

	    @Column(name = "status_flag", length = 20)
	    private String statusFlag;
	    
	    @Column(name = "bank_Passbook")
	    private String bankPassbook;
	    
	    @Column(name = "bank_Cheaque")
	    private String  bankCheaque;
	    
	    @Column(name = "dept_letter")
	    private String deptLetter;
	    
	    
	    
	}
