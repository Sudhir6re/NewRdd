package com.mahait.gov.in.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.mahait.gov.in.entity.AppoinmentEntity;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.EmployeeAllowDeducComponentAmtEntity;
import com.mahait.gov.in.entity.LoanEmployeeDtlsEntity;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.MstEmployeeDetailEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstGpfDetailsEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.QualificationEntity;
import com.mahait.gov.in.model.DDOScreenModel;
import com.mahait.gov.in.model.MstCadreModel;
import com.mahait.gov.in.model.MstEmployeeModel;

import jakarta.validation.Valid;

public interface MstEmployeeService {

	DdoOffice findAllGroup(String ddocode);

	public List<MstEmployeeEntity> findAllWorkingEmployeeByDDOCodeAndBillGroup(String ddoCode, Long SchemeBillgroupId,
			int month, int year);

	public EmployeeAllowDeducComponentAmtEntity findGRPComponentsData(String sevaarthId, int allowDedCode);

	public int getpayCommissionAgainstEmployee(String sevaarthId);

	public List<Object[]> employeeAllowDeduction(String sevaarthId);

	public LoanEmployeeDtlsEntity findGPFADetails(String sevaarthid, int commoncodeComponentGpfaCode);

	public LoanEmployeeDtlsEntity findGPFAdvDetails(String sevaarthId, int commoncodeComponentGpfaCode);

	public List<DDOScreenModel> findDDOScreenDataTable(String locale, long locId);

	public List<Object[]> getInstitueDtls(String userName);

	public List<MstCadreModel> getCadreMstData(String locale, long locId);

	public Object getDcpsAccnMaintainby();

	public Object getAccountMaintainby();

	public Object getGISGroup();

	public Object getGISApplicable();

	public Object getRelation();

	public List<Object[]> findEmployeeConfigurationGetSixPayScale(int i);

	public List<Object[]> findEmployeeConfigurationGetpayscale(int payCommission);

	public List<Object[]> findEmployeeConfigurationGetCurrentPost(Long designationId, String userName,
			String postdetailid, long locId);

	public List<Object[]> getCadreGroupMstData(String language, String cadreid);

	public List<MstEmployeeModel> getCadreGroupMstDataNew(String cadreid, String dob);

	public List<Object[]> findEmployeeConfigurationpayScaleSeven(int payScaleSeven);

	public List<AppoinmentEntity> getAppoitnment(String language);

	public List<Object[]> findEmployeeConfigurationGetsvnbasicpay(String payscale);

	public List<MstDesignationEntity> getDesignationMstData(String locale, long locId);

	List<Object[]> employeeConfigurationGetCurrenOffice(long postdetailid, String userName, long locId);

	List<Object[]> employeeConfigurationGetCurrenOfficeAddress(long adminDepartmentId, String userName, long locId);

	List<Object[]> getBankBranch(String bankid);

	List<Object[]> getPfSeries(String accmainby);

	long updateEmployeeConfiguration(@Valid MstEmployeeModel mstEmployeeModel, MultipartFile[] files);

	long saveEmployeeConfiguration(@Valid MstEmployeeModel mstEmployeeModel, MultipartFile[] files);

	String[] savePhotoSignature(MultipartFile[] files, String DeptNm, Long long1, String existphotpath,
			String existsignpath);

	String[] savePhotoSignature(MultipartFile[] files, String DeptNm, Long empid);

	List<MstEmployeeModel> getApproveEmployeeDetails(String strddo, String language, long locId);

	MstEmployeeModel getEmployeeinfo(Long employeeId);

	List<MstNomineeDetailsEntity> getNominees(String string);

	public List<Object[]> GetCurrentPostByLvlTwo(long designationId, String ddocode, long locId);

	long getLocationCode(String ddoCode);

	void saveGpfDetails(MstGpfDetailsEntity mstGpfDetailsEntity);

	long getCount(String sevaarthid);

	public List<Long> approveEmployeeConfiguration(String empid, String sevaarthid, String dcpsgpfflg);

	int getSevaarthid(String sevaarthid);

	int checkSevaarthIdExistInGpfDetailMst(String empid);

	public List<QualificationEntity> getQualification(String language);

	List<MstEmployeeModel> getDcpsEmployeeDetails(String strddo, String language, long locId, OrgUserMst messages);

	Character getLastDigit(String dcpsnum);

	Map getMappingData();

	List<Long> approveDcpsEmployeeConfiguration(String empid, String dcpsnum, String lStrSevarthEmpCode,
			String dcpsgpfflg);

	public String createNewUser(String sevaarthid, OrgUserMst message, MstEmployeeModel mstEmployeeModel);

	MstEmployeeDetailEntity updateEmployeeDetails(Long empid);

	Object findAllEmployeesByDDOName(String userName);

	List<MstEmployeeModel> findDraftCaseList(OrgUserMst messages, Long cASE_STATUS);

	long updateDraftCase(@Valid MstEmployeeModel mstEmployeeModel, MultipartFile[] files);

	Integer deleteEmployeesByIds(List<Long> employeeIds, OrgUserMst orgUserMst);

	List<Long> rejectEmployeeConfiguration(String empid);

	List<CmnLookupMst> getLookupValuesForParentAG(Long agType);

	String approveDcpsEmpByDdo(String empid, OrgUserMst message);



	// public List<MstBankBranchEntity> getIfscCodeByBranchId(int branchId);

}
