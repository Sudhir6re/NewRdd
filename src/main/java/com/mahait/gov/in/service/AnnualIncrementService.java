package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.EmployeeIncrementEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.AnnualIncrementModel;

public interface AnnualIncrementService {
	public List<MstPayCommissionEntity> findAppPayCommssion();

	public List<Object[]> findAllEmpForDue(String id, Long payCommision, String ddoCode);

	public Long saveAnnualIncrement(EmployeeIncrementEntity employeeIncrementEntity);

	public List<Object[]> findNextMatrix(Integer empIds, Integer basicPay, Integer level);

	public List<AnnualIncrementModel> getEmpDataForIncrementApproval(String userName);

	public List<AnnualIncrementModel> lstEmpforMTR21(String orderNo, String orderDate,String ddoCode);

	public String officeName(String userName);

	public int approveAnnualIncrement(AnnualIncrementModel annualIncrementModel, OrgUserMst messages);

	public List<Object[]> findOrderNoAlreadyExists(String certificateNumber, String userName);

	public List<AnnualIncrementModel> getEmpStatus(String userName);

	public EmployeeIncrementEntity getPreIncDtsByempId(int i);

	public void updateEmpIncrementToDate(EmployeeIncrementEntity previousIncrementDtlsObj, OrgUserMst messages);

	public String newMatrixId(String sevaarthIds1, String basicPaysIncrementes1);

	public String oldMatrixId(String sevaarthIds1, String basicPays1);

	public void saveAnnualIncrementBasicMst(MstEmployeeEntity mstEmployeeEntity);

	public MstEmployeeEntity getSevenPcBasicDetail(String sevaarthIds1);

	public EmployeeIncrementEntity findOldAnnualIncrement(Integer employeeId);

}
