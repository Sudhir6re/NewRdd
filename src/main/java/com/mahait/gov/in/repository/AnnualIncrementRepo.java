package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.EmployeeIncrementEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;

public interface AnnualIncrementRepo {
	public List<MstPayCommissionEntity> findAppPayCommssion();

	public List<Object[]> findAllEmpForDue(String id, Long payCommision, String ddoCode);

	public Long saveAnnualIncrement(EmployeeIncrementEntity employeeIncrementEntity);

	public List<Object[]> findNextMatrix(Integer empIds, Integer basicPay, Integer level);

	public List<Object[]> getEmpDataForIncrementApproval(String userName);

	public List<Object[]> lstEmpforMTR21(String orderNo, String orderDate, String ddoCode);

	public String officeName(String userName);

	public List<EmployeeIncrementEntity> findEmp(String orderNo);

	public Serializable approveAnnualIncrement(EmployeeIncrementEntity obj);

	public List<Object[]> findOrderNoAlreadyExists(String certificateNumber);

	public List<Object[]> getEmpStatus(String userName);

	public MstEmployeeEntity findEmpByEmpId(Long employeeId);

	public void updateEmpBasicPay(MstEmployeeEntity mstEmployeeEntity);

	public EmployeeIncrementEntity getPreIncDtsByempId(int empId1);

	public void updateEmpIncrementToDate(EmployeeIncrementEntity previousIncrementDtlsObj);

	public String newMatrixId(String sevaarthIds1, String basicPaysIncrementes1);

	public String oldMatrixId(String sevaarthIds1, String basicPays1);

	public void updateSevenPcBasicMst(MstEmployeeEntity mstEmployeeEntity);

	public MstEmployeeEntity getSevenPcBasicDetails(String sevaarthIds1);

	public EmployeeIncrementEntity findOldAnnualIncrement(Integer employeeId);

	public String getEmpCountAgainstOrderNo(String orderNo, String userName);

}
