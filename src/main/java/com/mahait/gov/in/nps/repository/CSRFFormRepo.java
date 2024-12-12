package com.mahait.gov.in.nps.repository;

import java.util.List;

import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.NSDLBHDtlsEntity;
import com.mahait.gov.in.nps.entity.TrnNpsRegFileEntity;
import com.mahait.gov.in.nps.model.MstEmployeeNPSModel;

import jakarta.validation.Valid;

public interface CSRFFormRepo {

	List<Object[]> findAllEmployees(String ddoCode);

	MstEmployeeEntity findEmployeeBySevaarthId(Long empId);
	
	MstEmployeeNPSModel findCSRFEmployeeBySevaarthId(Long empId);
	
	List<MstNomineeDetailsEntity> findNomineeBySevaarthId(int sevaarthId);

	public Long  saveCSRF(MstEmployeeNPSEntity mstEmployeeNPSEntity);

	MstEmployeeNPSEntity findEmployeeDtlsBySevaarthId(Long empId);

	List<Object[]> findAllCSRFApprovedEmployees();

	List<Object[]> findAllCSRFApprovedEmployees(String sevaarthId, String ddoAsst);

	List<MstEmployeeNPSEntity> getEmpDataForTextFile(String userName,int countEmp);

	String getDDOLevel2FromDDO1(String userName);

	List<Object[]> getDeptNameFromDDO2(String userName);

	List<Object[]> getempData(String sevaarthId);

	Integer getSeqUpdate();

	Long getSequenceForTextFile(String sequenceName);

	MstEmployeeNPSEntity getEmployeeNPSEntity(Integer employeeId);

	int saveTrnNpsRegFile(TrnNpsRegFileEntity trnNpsRegFile);

	String getPayScale(Long payScaleCode);

	TrnNpsRegFileEntity findTrnNpsFileEntityById(Integer id);

	void updateTrnNpsFileEntity(TrnNpsRegFileEntity trnNpsRegFileEntity);

	List<Object[]> findNpsIdByAckNo(String ackNo);

	MstEmployeeNPSEntity findEmployeeByNpsid(Integer npsId);

	void updatePranNumberByNpsId(MstEmployeeNPSEntity mstEmployeeNPSEntity);

	void updateEmployeeByEmpId(MstEmployeeEntity mstEmployeeEntity) ;

	String getStateName(String empState);

	List<Object[]> viewCSRFPhotoSign(int empId);

	String getDtoRegNumber(String ddoLevel2);

	Long getNextSeqNum(String seqName);

	Integer getRegSeqNo(String strDate);

	String getDdoRegNumber(String userName);

	int gettrano(String userName);

	void updateBhEntity(NSDLBHDtlsEntity nSDLBHDtlsEntity);

	NSDLBHDtlsEntity findBHEntityById(Integer bhId);

	Object saveOrUpdate(@Valid MstEmployeeEntity mstEmployeeEntity);

	List<CmnLookupMst> findCityClass();

	List<MstNomineeDetailsEntity> findNomineeDtls(Long empId);

	String findBankName(Long bankCode);

	String findBankBranchName(Long bankBranchCode);


}
