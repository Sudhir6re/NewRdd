package com.mahait.gov.in.service;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.entity.ComponentParameterMstEntity;
import com.mahait.gov.in.entity.ComponetMappingEntity;
import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;

public interface ComponentMappingService {
//	public void updatePrivileges(Long compId, boolean typeAllowance, boolean typeOfComponet, boolean payCommision,
//			boolean amount, boolean startDate, boolean endDate, boolean percentage, boolean minBasic, boolean maxBasic,
//			boolean cityClass, boolean gradePayorSevenPcLevelLow, boolean grdePayorSevenPcLevelhigh,
//			boolean premiumAmount, boolean cityGroup);

	public List<ComponetMappingEntity> setMandatoryFieldsForComponents(Map<String, List<String>> componentParameterMap);

	public List<ComponentParameterMstEntity> transferData(
			List<DeptEligibilityForAllowAndDeductEntity> list);
}
