package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.List;

import com.mahait.gov.in.entity.AppoinmentEntity;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.EmployeeAllowDeducComponentAmtEntity;
import com.mahait.gov.in.entity.LoanEmployeeDtlsEntity;
import com.mahait.gov.in.entity.MstCadreGroupEntity;
import com.mahait.gov.in.entity.MstDcpsDetailsEntity;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.MstEmployeeDetailEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstGisdetailsEntity;
import com.mahait.gov.in.entity.MstGpfDetailsEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.OrgPostDetailsRlt;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.QualificationEntity;
import com.mahait.gov.in.entity.ZpRltDdoMap;
import com.mahait.gov.in.model.MstEmployeeModel;

public interface MstEmployeeRepo {
	
	public List<MstEmployeeEntity> findAllWorkingEmployeeByDDOCodeAndBillGroup(String ddoCode, Long billGroupId,
			int month, int year);

	public DdoOffice findAllGroup(String ddoCode);

	public int getpayCommissionAgainstEmployee(String sevaarthId);
	
	public List<Object[]> findEmployeeAllowanceDeduction(String sevaarthId);

	public EmployeeAllowDeducComponentAmtEntity findGRPComponentsData(String sevaarthId, int allowDedCode);

	public LoanEmployeeDtlsEntity findGPFADetails(String sevaarthid, int commoncodeComponentGpfaCode);

	public LoanEmployeeDtlsEntity findGPFAdvDetails(String sevaarthid, int commoncodeComponentGpfaCode);
	
	public List<Object[]> findDDOScreenDataTable(long loc_id);

	public List<Object[]> getInstitueDtls(String ddocode);

	public List<Object[]> getCadreMstData(long fielddeptId);

	public List<MstCadreGroupEntity> getGISGroup();

	public List<MstRoleEntity> findAll();

	public long getFieldDeptId(long loc_id);

	public List<Object[]> findEmployeeConfigurationGetSixPayScale(int payCommission);

	public List<Object[]> findEmployeeConfigurationpayScaleSeven(int payCommission);

	public List<Object[]> getgroupname(String strCadreId);

	public List<Object[]> getCadreGroupMstDataNew(String cadreid);

	public List<AppoinmentEntity> getAppoitnment(String language);

	public List<Object[]> getSvnPayscale();

	public List<Object[]> getSvnPcData(String payscale);

	public List<Object[]> getDesignationMstData(long fielddeptId);

	public List<Object[]> findEmployeeConfigurationGetCurrentPost(Long designationId, String ddocode,
			String currpostcode, long loc_id);

	public List<Object[]> employeeConfigurationGetCurrenOffice(long postdetailid, String userName,
			long locId);

	public List<Object[]> employeeConfigurationGetCurrenOfficeAddress(long adminDepartmentId, String userName,
			long locId);

	public List<Object[]> getBankBranch(String bankid);

	public List<Object[]> getPfSeries(String accmainby);

	public MstEmployeeEntity findbyemplid(Long long1);


	public MstGpfDetailsEntity findbyGPFid(Long gpf_id);

	public MstGisdetailsEntity findbyGisid(Long gisid);

	public int updateEmployeeConfiguration(MstEmployeeEntity objEntity, MstEmployeeModel mstEmployeeModel,
			MstNomineeDetailsEntity[] lArrNomineeDtls);

	Long deleteNomineeDtls(Long empid);

	MstDcpsDetailsEntity findbyDcpsid(Long dcpsid);

	public BigInteger findbySevaarthCount(String sevaarth);

	public List saveEmployeeConfiguration(MstEmployeeEntity objEntity, MstEmployeeModel mstEmployeeModel,
			MstNomineeDetailsEntity[] lArrNomineeDtls);

	public String updateImagePath(String string, String string2, Long integer);

	public List<MstEmployeeEntity> getApproveEmployeeDetails(String strddo);

	public String getDesignationName(String strDesgId);

	public MstEmployeeModel getEmployeeinfo(Long employeeId);

	public List<MstNomineeDetailsEntity> getNominees(String empId);

	public List<Object[]> GetCurrentPostByLvlTwo(long designationId, String ddocode, long loc_id);


	public String getCmnLocationMst(String ddoCode);

	public long getLocationCode(String getLocationCode);

	public long getCount(String tempSevarthEmpCode);

	public List<Long> approveEmployeeConfiguration(String empid, String sevaarthid, String dcpsgpfflg);

	public int getSevaarthid(String sevaarthid);

	public int checkSevaarthIdExistInGpfDetailMst(String sevaarthid);

	public List<QualificationEntity> getQualification(String language);

	public List<MstEmployeeEntity> getDcpsEmployeeDetails(String strddo, OrgUserMst messages);

	public List<Long> approveDcpsEmployeeConfiguration(String empid, String dcpsnumber, String sevaarthid,
			String dcpsgpfflg);

	public OrgUserMst saveUserInfo(OrgUserMst lObjUserMst);

	public OrgPostDetailsRlt findPostdetailById(Long postdetailid);

	public String saveUserId(String sevaarthId, long user_id);

	public MstEmployeeDetailEntity updateEmployeesDetails(Long empid);

	
	public List<Object[]> findAllEmployeesByDDOName(String userName);

	public List<Object[]> findDraftCaseList(OrgUserMst messages, Long cASE_STATUS);

	public Integer deleteEmployeesByIds(List<Long> employeeIds, OrgUserMst orgUserMst);

	public List<Long> rejectEmployeeConfiguration(String empid);

	public List<CmnLookupMst> getLookupValuesForParentAG(Long agType);

	public String approveDcpsEmpByDdo(String empid, OrgUserMst message);

	public List<ZpRltDdoMap> findDdoByReptDdoCode(String reptDdoCode);

	public List<MstDesignationEntity> getDesigsForPFDAndCadre(String cadre, String fieldDept);

	public Long validateAccountNum(String accountNum, String employeeid);

	public Long validateUIDUniq(String uid, String employeeid);

	public Long validatePancard(String pranno, String employeeid);

	public Long validatePranNo(String pranno, String employeeid);

	public Long validateEmail(String email, String employeeid);

	public Long validateMobileno(Long mobno, String employeeid);

	public Long validateTelephone(String telphone, String employeeid);




//	public List<MstBankBranchEntity> getIfscCodeByBranchId(int branchId);


	

}
