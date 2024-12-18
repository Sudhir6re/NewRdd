package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DeptEligibilityForAllowAndDeductModel;
import com.mahait.gov.in.model.EmployeeAllowDeducComponentAmtModel;

import jakarta.validation.Valid;

public interface DeptEligibilityForAllowAndDeductService {

	public List<DeptEligibilityForAllowAndDeductEntity> findDeptEligibilityForAllowAndDeductList();

	public int deleteMpgDdoAllowDeduc(int action, Object object);

	public int saveMpgDdoAllowDeduc(Object object, int action, Object[] serialid, String effectiveDate, Object object2);

	public List<Object[]> findallowDeductLevel2(String ddoCode2);

	public List<Object[]> findlevel1DDOAgaintlevel2(String userName);

	public List<DeptEligibilityForAllowAndDeductEntity> findDeptNonGovDeductList();

	public int saveEmployeeNonGovDuesDeduct(
			@Valid EmployeeAllowDeducComponentAmtModel employeeAllowDeducComponentAmtModel, OrgUserMst messages);

	public List<DeptEligibilityForAllowAndDeductEntity> findDeptAllowAndDeductList();

	public List<Object[]> getEmployeeAgainstId(int allowDeducComponentId, String ddoCode, String sevaarthId);
	public int saveAllowDeductionMst(@Valid DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,
			OrgUserMst messages);

}
