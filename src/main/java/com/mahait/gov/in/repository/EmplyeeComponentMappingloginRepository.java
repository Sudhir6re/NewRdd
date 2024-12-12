package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;

public interface EmplyeeComponentMappingloginRepository {

	List<DeptEligibilityForAllowAndDeductEntity> findAllComponentDetails();

	List<Map<String, Object>> getEmployeeList();

	void mapComonent(Long empId, String sevaarthId, Integer deptAllowDeducCode);

}
