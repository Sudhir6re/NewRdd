package com.mahait.gov.in.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;



@Data
public class MstGrOrderModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long grOrderId;
	private Integer subDepartmentId;
	private Integer ddoId;
	private String sanctionOrderNo;
	private String ddoCode;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;
	
	
	private String subDepartmentNameEn;
	private Integer districtCode;
	private String districtConcatCode;
	private Integer talukaCode;
	private String talukaConcatCode;
	private Integer departmentId;
	
	
	private String departmentNameEn;
	private char isActive;
	private BigDecimal orderType;
	private Integer createdUserId;
	private Date createdDate;
	private Integer updatedUserId;
	private Date updatedDate;
	
	
	
	private String isApplicableLocation;
	private String isApplicableForEndDt;
	
	private MultipartFile[] documentPath;
	
	
	private String docPath;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	private String grDescription;
	private String officeName;
	
	
	
	
	
	
	

}
