package com.mahait.gov.in.nps.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.TrnNpsRegFileEntity;
import com.mahait.gov.in.nps.model.CSRFFormModel;
import com.mahait.gov.in.nps.model.MstEmployeeNPSModel;

import jakarta.validation.Valid;

public interface CSRFFormService {

	public List<CSRFFormModel> findAllEmployees(String ddoCode);

	public MstEmployeeEntity findEmployeeBySevaarthId(Long long1);
	
	public MstEmployeeNPSModel findCSRFEmployeeBySevaarthId(Long employeeId);

	public List<MstNomineeDetailsEntity> findNomineeBySevaarthId(int sevaarthId);

	public Long saveCSRF(MstEmployeeEntity mstEmployeeEntity,MultipartFile[] files,String ddoLevel2);

	public MstEmployeeNPSEntity findEmployeeDtlsBySevaarthId(Long empId);

	public List<CSRFFormModel> findAllCSRFApprovedEmployees();

	public List<Object[]> getEmpData(String sevaarthId, String ddoAsst);

	public List<MstEmployeeNPSEntity> getEmpDataForTextFile(String userName,int countEmp);

	public String getDDOLevel2FromDDO1(String userName);

	public List<Object[]> getDeptNameFromDDO2(String ddoLevel2);

	public List<Object[]> getempData(String sevaarthId);

	public Integer getSeqUpdate();

	public Long getSequenceForTextFile(String string);

	public int saveTrnNpsRegFile(TrnNpsRegFileEntity trnNpsRegFile);

	public String getPayScale(Long payScaleCode);

	public TrnNpsRegFileEntity findTrnNpsFileEntityById(Integer id);

	public void updateTrnNpsFileEntity(TrnNpsRegFileEntity trnNpsRegFileEntity);

	public List<Object[]> findNpsIdByAckNo(String string);

	public MstEmployeeNPSEntity findEmployeeByNpsid(Integer npsId);

	public void updatePranNumberByNpsId(MstEmployeeNPSEntity mstEmployeeNPSEntity);

	public void updateEmployeeByEmpId(MstEmployeeEntity mstEmployeeEntity);

	public String getStateName(String empState);

	public List<Object[]> viewCSRFPhotoSign(int empId);

	public String getDtoRegNumber(String ddoLevel2);

	Long getNextSeqNum(String seqName);

	Integer getRegSeqNo(String strDate);

	public String getDdoRegNumber(String userName);

	
	public int gettrano(String userName);

	public void updatemstEmployeeEntity(@Valid MstEmployeeEntity mstEmployeeEntity);

	public List<CmnLookupMst> findCityClass();

	public List<MstNomineeDetailsEntity> findNomineeDtls(Long empId);

	public String findBankName(Long bankCode);

	public String findBankBranchName(Long bankBranchCode);

	
}
