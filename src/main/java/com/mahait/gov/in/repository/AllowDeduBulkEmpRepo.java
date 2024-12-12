package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.model.DeptEligibilityForAllowAndDeductModel;

public interface AllowDeduBulkEmpRepo {

	int updateAllowDeductBulkEmplComp(DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel, String ddoCode);

	void checkComponentAlreadyPresent(DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel, String ddoCode);

	List<Object[]> getListEmpBySchemBillGroup(String userName, Long schemeBillGrpId,
			Integer departmentAllowdeducCode);

	List<Object[]> findpaybill(String billNumber, String userName);

	List<Object[]> findAllEmployeesByDDOName(String ddoCode);
	
	
	

}
