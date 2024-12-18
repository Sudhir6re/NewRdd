package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.model.DeptEligibilityForAllowAndDeductModel;
import com.mahait.gov.in.model.MstEmployeeModel;

public interface AllowDeduBulkEmpService {

	int updateAllowDeductBulkEmplComp(DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel, String string);

	void checkComponentAlreadyPresent(DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel, String string);

	List<MstEmployeeModel> getListEmpBySchemBillGroup(String userName, Long schemeBillGrpId,
			Integer departmentAllowdeducCode);

	List<Object[]> findpaybill(String billNumber, String userName);

	List<MstEmployeeModel> findAllEmployeesByDDOName(String ddoCode);

}
