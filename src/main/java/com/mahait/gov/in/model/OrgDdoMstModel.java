package com.mahait.gov.in.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class OrgDdoMstModel {

	private String ddoCode;

	private Long ddoId;

	private String ddoName;

	private String ddoPersonalName;

	private Long postId;

	private Long attachmentId;

	private Long langId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	private Long activateFlag;

	private Long createdBy;

	private Long createdByPost;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;

	private Long updatedBy;

	private Long updatedByPost;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedDate;

	private Long dbId;

	private String shortName;

	private String majorHead;

	private String demand;

	private Integer ddoNo;

	private Integer cardexNo;

	private Integer trnCounter;

	private Boolean adminFlag;

	private String officeCode;

	private String locationCode;

	private String deptLocCode;

	private String hodLocCode;

	private Boolean isCo;

	private Boolean isCs;

	private Short type;

	private String designCode;

	private String designName;

	private String ddoOffice;

	private String Remarks;

	private String tanNo;

	private String itaWardNo;

	private String bankName;

	private String branchName;

	private String accountNo;

	private String ifscCode;

	private Long ddoType;

	private Long instituteType;

	private Long DDO_TYPE;

	private Long ADMIN_DEPT_TYPE;

	// Constructors

	private Long statusFlag;

	private String bankId;
	private String parentAdminDepartmentId;
	private String ParentFieldDepartmentId;
	private String ItoWardCircle;
	private String designationId;
	private String bankCode;

	private String bankPassbook;

	private String bankCheaque;

	private String DeptLetter;

}
