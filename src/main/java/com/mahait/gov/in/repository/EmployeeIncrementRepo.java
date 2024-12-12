package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.EmployeeIncrementEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;


public interface EmployeeIncrementRepo {


	String officeName(String userName);

	List<Object[]> lstEmpforMTR21(String orderNo, int levelRoleVal, String ddoCode);//,String locId

	List<Object[]> getIncrementDataForReptDDO(String userName, String currYear);

	List<EmployeeIncrementEntity> findEmp(String orderNo);

	void updateEmpBasicPay(MstEmployeeEntity mstEmployeeEntity);

	Serializable approveAnnualIncrement(EmployeeIncrementEntity employeeIncrementEntity);

	MstEmployeeEntity findEmpByEmpId(Long employeeId);

}
