package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.ComponentMstEntity;
import com.mahait.gov.in.entity.ComponentParameterMstEntity;
import com.mahait.gov.in.entity.ComponetMappingEntity;
import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.repository.ComponentMappingRepository;
import com.mahait.gov.in.repository.ComponentMstRepository;
import com.mahait.gov.in.repository.ComponentParameterMstRepo;
import com.mahait.gov.in.repository.DeptEligibilityForAllowAndDeductRepo;

@Service
@Transactional
public class ComponentMappingServiceImpl implements ComponentMappingService {

	@Autowired
	DeptEligibilityForAllowAndDeductRepo DeptEligibilityForAllowAndDeductRepo;
	@Autowired
	ComponentParameterMstRepo componentParameterMstRepo;
	@Autowired
	ComponentMappingRepository componentMappingRepository;
	@Autowired
	ComponentMstRepository 	componentMstRepository;
	@Autowired
	DeptEligibilityForAllowAndDeductService deptEligibilityForAllowAndDeductService;

	@Override
	public List<ComponentParameterMstEntity>  transferData(List<DeptEligibilityForAllowAndDeductEntity> list) {
		List<ComponentParameterMstEntity> targetList = new ArrayList<>();  
		for (DeptEligibilityForAllowAndDeductEntity source : list) {
			ComponentParameterMstEntity target = new ComponentParameterMstEntity();
			target.setCompntParameterName(source.getDepartmentAllowdeducName());
			targetList.add(target);
			componentParameterMstRepo.transferData(targetList);
		}
		return targetList;
	}
	
	
	@Override
	public List<ComponetMappingEntity> setMandatoryFieldsForComponents(Map<String, List<String>> componentParameterMap) {
		  List<ComponetMappingEntity> mappings = new ArrayList<>();
		  for (Map.Entry<String, List<String>> entry : componentParameterMap.entrySet()) {
	            String componentName = entry.getKey();
	            List<String> parameterNames = entry.getValue();
	            
	            ComponentParameterMstEntity componentParameterMstEntity = componentParameterMstRepo.findByComponentName(componentName)
	                    .orElseThrow(() -> new RuntimeException("Component not found: " + componentName));
	            
	            
	            for (String parameterName : parameterNames) {
	                ComponentMstEntity componentMstEntity = componentMstRepository.findByParameterName(parameterName)
	               .orElseThrow(() -> new RuntimeException("Required Parameter not found: " + parameterName)); 
	                
	                ComponetMappingEntity mapping = new ComponetMappingEntity();
	                mapping.setComponentParameterMstEntity(componentParameterMstEntity);
	                mapping.setComponentMstEntity(componentMstEntity);
	                mapping.setIsMandatory(true);  // Mark as mandatory

	                mappings.add(mapping);
	            }           
	}
		return componentMappingRepository.save(mappings);
	
	
	}
	
	


}

