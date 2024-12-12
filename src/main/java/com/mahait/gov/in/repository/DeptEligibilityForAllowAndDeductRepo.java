package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.entity.EmployeeAllowDeducComponentAmtEntity;

public interface DeptEligibilityForAllowAndDeductRepo {

	public List<DeptEligibilityForAllowAndDeductEntity> findDeptEligibilityForAllowAndDeductList();
	public int saveAllowDeductionMst(DeptEligibilityForAllowAndDeductEntity mstDeptEligibilityForAllowAndDeductEntity);
	
	public List<DeptEligibilityForAllowAndDeductEntity> findDeptAllowAndDeductList();
	
	public int deleteMpgDdoAllowDeduc(int action, Object object);

	
	public int saveEmpMpgDdoAllowDeduc(Object object, int action, Object[] serialid, String effectiveDate,
			Object object2);

	public List<Object[]> findallowDeductLevel2(String ddoCode2);

	public List<Object[]> findlevel1DDOAgaintlevel2(String userName);

	public List<Object[]> getAllowDeductComponentByDDO(String ddoCode);
	public List<DeptEligibilityForAllowAndDeductEntity> findDeptNonGovDeductList();
	public EmployeeAllowDeducComponentAmtEntity findMstDeptByDeptId(String string, int deptallowcode);
	public void updateComponent(EmployeeAllowDeducComponentAmtEntity empdata);
	public Serializable saveEmployeeNonGovDuesDeduct(EmployeeAllowDeducComponentAmtEntity empAllDedCompEntity);
	public List<Object[]> getEmployeeAgainstId(int allowDeducComponentId, String ddoCode, String sevaarthId);
	public List<DeptEligibilityForAllowAndDeductEntity> findAll();

	


	
}
