package com.mahait.gov.in.model;

import java.util.Date;

public class OrgDdoMst {

	// Fields

	private Long ddoId;

	private String ddoCode;

	private String ddoName;

	private String ddoPersonalName;

	private Long postId;

	private Long attachmentId;

	private Long langId;

	private Date startDate;

	private Date endDate;

	private Long activateFlag;

	private Long createdBy;

	private Long createdByPost;

	private Date createdDate;

	private Long updatedBy;

	private Long updatedByPost;

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

	private String ifsCode;
	
	private Long ddoType;

	
	private Long instituteType;
	
	private Long DDO_TYPE;

	
	private Long ADMIN_DEPT_TYPE;
	
	
	
	// Constructors
	
	private Long statusFlag;

	
	
	
	public Long getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Long statusFlag) {
		this.statusFlag = statusFlag;
	}
	
	public Long getDDO_TYPE()
	{
		
		DDO_TYPE=ddoType;
		return DDO_TYPE;
	}

	public void setDDO_TYPE(Long DDO_TYPE)
	{
		this.DDO_TYPE = DDO_TYPE;
		ddoType=DDO_TYPE;
	}

	public Long getADMIN_DEPT_TYPE()
	{
		return ADMIN_DEPT_TYPE;
	}

	public void setADMIN_DEPT_TYPE(Long admin_dept_type)
	{
		ADMIN_DEPT_TYPE = admin_dept_type;
	}

	/** default constructor */
	public OrgDdoMst() {

	}

	/** minimal constructor */
	public OrgDdoMst(Long ddoId, String ddoCode, String ddoName, Long postId, Long langId, Date startDate, Long activateFlag, Long createdBy, Long createdByPost, Date createdDate, Long dbId,
			Boolean adminFlag, Boolean isCo, Boolean isCs) {

		this.ddoId = ddoId;
		this.ddoCode = ddoCode;
		this.ddoName = ddoName;
		this.postId = postId;
		this.langId = langId;
		this.startDate = startDate;
		this.activateFlag = activateFlag;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
		this.dbId = dbId;
		this.adminFlag = adminFlag;
		this.isCo = isCo;
		this.isCs = isCs;
	}

	/** full constructor */
	public OrgDdoMst(Long ddoId, String ddoCode, String ddoName, Long postId, Long attachmentId, Long langId, Date startDate, Date endDate, Long activateFlag, Long createdBy, Long createdByPost,
			Date createdDate, Long updatedBy, Long updatedByPost, Date updatedDate, Long dbId, String shortName, String majorHead, String demand, Integer ddoNo, Integer cardexNo, Integer trnCounter,
			Boolean adminFlag, String officeCode, String locationCode, String deptLocCode, String hodLocCode, Boolean isCo, Boolean isCs, Short type) {

		this.ddoId = ddoId;
		this.ddoCode = ddoCode;
		this.ddoName = ddoName;
		this.postId = postId;
		this.attachmentId = attachmentId;
		this.langId = langId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activateFlag = activateFlag;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedByPost = updatedByPost;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.shortName = shortName;
		this.majorHead = majorHead;
		this.demand = demand;
		this.ddoNo = ddoNo;
		this.cardexNo = cardexNo;
		this.trnCounter = trnCounter;
		this.adminFlag = adminFlag;
		this.officeCode = officeCode;
		this.locationCode = locationCode;
		this.deptLocCode = deptLocCode;
		this.hodLocCode = hodLocCode;
		this.isCo = isCo;
		this.isCs = isCs;
		this.type = type;
		
	}
	public OrgDdoMst(Long ddoId, String ddoCode, String ddoName, Long postId, Long attachmentId, Long langId, Date startDate, Date endDate, Long activateFlag, Long createdBy, Long createdByPost,
			Date createdDate, Long updatedBy, Long updatedByPost, Date updatedDate, Long dbId, String shortName, String majorHead, String demand, Integer ddoNo, Integer cardexNo, Integer trnCounter,
			Boolean adminFlag, String officeCode, String locationCode, String deptLocCode, String hodLocCode, Boolean isCo, Boolean isCs, Short type,Long ddoType,Long instituteType) {

		this.ddoId = ddoId;
		this.ddoCode = ddoCode;
		this.ddoName = ddoName;
		this.postId = postId;
		this.attachmentId = attachmentId;
		this.langId = langId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activateFlag = activateFlag;
		this.createdBy = createdBy;
		this.createdByPost = createdByPost;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedByPost = updatedByPost;
		this.updatedDate = updatedDate;
		this.dbId = dbId;
		this.shortName = shortName;
		this.majorHead = majorHead;
		this.demand = demand;
		this.ddoNo = ddoNo;
		this.cardexNo = cardexNo;
		this.trnCounter = trnCounter;
		this.adminFlag = adminFlag;
		this.officeCode = officeCode;
		this.locationCode = locationCode;
		this.deptLocCode = deptLocCode;
		this.hodLocCode = hodLocCode;
		this.isCo = isCo;
		this.isCs = isCs;
		this.type = type;
		this.ddoType=ddoType;
		//added by Demolisher
		//this.instituteType=instituteType;
		
	}
	// Property accessors
	public Long getDdoId() {

		return this.ddoId;
	}

	public void setDdoId(Long ddoId) {

		this.ddoId = ddoId;
	}
	public Long getddoType() {

		return this.ddoType;
	}

	public void setddoType(Long ddoType) {

		this.ddoType = ddoType;
	}

	public String getDdoCode() {

		return this.ddoCode;
	}

	public void setDdoCode(String ddoCode) {

		this.ddoCode = ddoCode;
	}

	public String getDdoName() {

		return this.ddoName;
	}

	public void setDdoName(String ddoName) {

		this.ddoName = ddoName;
	}

	public Long getPostId() {

		return this.postId;
	}

	public void setPostId(Long postId) {

		this.postId = postId;
	}

	public Long getAttachmentId() {

		return this.attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {

		this.attachmentId = attachmentId;
	}

	public Long getLangId() {

		return this.langId;
	}

	public void setLangId(Long langId) {

		this.langId = langId;
	}

	public Date getStartDate() {

		return this.startDate;
	}

	public void setStartDate(Date startDate) {

		this.startDate = startDate;
	}

	public Date getEndDate() {

		return this.endDate;
	}

	public void setEndDate(Date endDate) {

		this.endDate = endDate;
	}

	public Long getActivateFlag() {

		return this.activateFlag;
	}

	public void setActivateFlag(Long activateFlag) {

		this.activateFlag = activateFlag;
	}

	public Long getCreatedBy() {

		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {

		this.createdBy = createdBy;
	}

	public Long getCreatedByPost() {

		return this.createdByPost;
	}

	public void setCreatedByPost(Long createdByPost) {

		this.createdByPost = createdByPost;
	}

	public Date getCreatedDate() {

		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {

		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {

		this.updatedBy = updatedBy;
	}

	public Long getUpdatedByPost() {

		return this.updatedByPost;
	}

	public void setUpdatedByPost(Long updatedByPost) {

		this.updatedByPost = updatedByPost;
	}

	public Date getUpdatedDate() {

		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {

		this.updatedDate = updatedDate;
	}

	public Long getDbId() {

		return this.dbId;
	}

	public void setDbId(Long dbId) {

		this.dbId = dbId;
	}

	public String getShortName() {

		return this.shortName;
	}

	public void setShortName(String shortName) {

		this.shortName = shortName;
	}

	public String getMajorHead() {

		return this.majorHead;
	}

	public void setMajorHead(String majorHead) {

		this.majorHead = majorHead;
	}

	public String getDemand() {

		return this.demand;
	}

	public void setDemand(String demand) {

		this.demand = demand;
	}

	public Integer getDdoNo() {

		return this.ddoNo;
	}

	public void setDdoNo(Integer ddoNo) {

		this.ddoNo = ddoNo;
	}

	public Integer getCardexNo() {

		return this.cardexNo;
	}

	public void setCardexNo(Integer cardexNo) {

		this.cardexNo = cardexNo;
	}

	public Integer getTrnCounter() {

		return this.trnCounter;
	}

	public void setTrnCounter(Integer trnCounter) {

		this.trnCounter = trnCounter;
	}

	public Boolean isAdminFlag() {

		return this.adminFlag;
	}

	public void setAdminFlag(Boolean adminFlag) {

		this.adminFlag = adminFlag;
	}

	public String getOfficeCode() {

		return this.officeCode;
	}

	public void setOfficeCode(String officeCode) {

		this.officeCode = officeCode;
	}

	public String getLocationCode() {

		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {

		this.locationCode = locationCode;
	}

	public String getDeptLocCode() {

		return this.deptLocCode;
	}

	public void setDeptLocCode(String deptLocCode) {

		this.deptLocCode = deptLocCode;
	}

	public String getHodLocCode() {

		return this.hodLocCode;
	}

	public void setHodLocCode(String hodLocCode) {

		this.hodLocCode = hodLocCode;
	}

	public Boolean isIsCo() {

		return this.isCo;
	}

	public void setIsCo(Boolean isCo) {

		this.isCo = isCo;
	}

	public Boolean isIsCs() {

		return this.isCs;
	}

	public void setIsCs(Boolean isCs) {

		this.isCs = isCs;
	}

	public Short getType() {

		return this.type;
	}

	public void setType(Short type) {

		this.type = type;
	}

	/**
	 * @return the ddoPersonalName
	 */
	public String getDdoPersonalName() {

		return ddoPersonalName;
	}

	/**
	 * @param ddoPersonalName
	 *            the ddoPersonalName to set
	 */
	public void setDdoPersonalName(String ddoPersonalName) {

		this.ddoPersonalName = ddoPersonalName;
	}

	/**
	 * @return the isCo
	 */
	public Boolean isCo() {

		return isCo;
	}

	/**
	 * @param isCo
	 *            the isCo to set
	 */
	public void setCo(Boolean isCo) {

		this.isCo = isCo;
	}

	/**
	 * @return the isCs
	 */
	public Boolean isCs() {

		return isCs;
	}

	/**
	 * @param isCs
	 *            the isCs to set
	 */
	public void setCs(Boolean isCs) {

		this.isCs = isCs;
	}

	/**
	 * @return the designCode
	 */
	public String getDesignCode() {

		return designCode;
	}

	/**
	 * @param designCode
	 *            the designCode to set
	 */
	public void setDesignCode(String designCode) {

		this.designCode = designCode;
	}

	/**
	 * @return the designName
	 */
	public String getDesignName() {

		return designName;
	}

	/**
	 * @param designName
	 *            the designName to set
	 */
	public void setDesignName(String designName) {

		this.designName = designName;
	}

	/**
	 * @return the ddoOffice
	 */
	public String getDdoOffice() {

		return ddoOffice;
	}

	/**
	 * @param ddoOffice
	 *            the ddoOffice to set
	 */
	public void setDdoOffice(String ddoOffice) {

		this.ddoOffice = ddoOffice;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {

		return Remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {

		Remarks = remarks;
	}
	public String getTanNo() {

		return tanNo;
	}
	public void setTanNo(String tanNo) {

		this.tanNo = tanNo;
	}
	public String getItaWardNo() {

		return itaWardNo;
	}
	public void setItaWardNo(String itaWardNo) {

		this.itaWardNo = itaWardNo;
	}

	public String getBankName() {

		return bankName;
	}

	public void setBankName(String bankName) {

		this.bankName = bankName;
	}

	public String getBranchName() {

		return branchName;
	}

	public void setBranchName(String branchName) {

		this.branchName = branchName;
	}
	public String getAccountNo() {

		return accountNo;
	}

	public void setAccountNo(String accountNo) {

		this.accountNo = accountNo;
	}

	public String getIfsCode() {

		return ifsCode;
	}

	public void setIfsCode(String ifsCode) {

		this.ifsCode = ifsCode;
	}

	//added by Demolisher
	public Long getInstituteType() {
		return instituteType;
	}


	public void setInstituteType(Long instituteType) {
		this.instituteType = instituteType;
	}

	
}
