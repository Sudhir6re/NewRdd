package com.mahait.gov.in.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.service.EmplyeeComponentMappingloginService;

@Controller
@RequestMapping("/ddoast")
public class EmplyeeComponentMappingLogicController {
	Logger logger = LoggerFactory.getLogger(EmplyeeComponentMappingLogicController.class);
	@Autowired
	EmplyeeComponentMappingloginService emplyeeComponentMappingloginService;

	@GetMapping("/mappedComponent")
	public void getMapEmply() {

		List<DeptEligibilityForAllowAndDeductEntity> lstDeptEligibilityForAllowAndDeductEntity = emplyeeComponentMappingloginService
				.findAllComponentDetails();
          
		List<Map<String, Object>> map = emplyeeComponentMappingloginService.getEmployeeList();

		for (Map<String, Object> employeeMap : map) {
			for (Map.Entry<String, Object> entry : employeeMap.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
           boolean isAmountGreaterThanZero=false;
           
				Integer deptAllowDeducCode = 0;
				BigInteger empIdBigInt = (BigInteger) employeeMap.get("employee_id");
				Long empId = empIdBigInt.longValue(); 
				//Long empId = (Long) employeeMap.get("employee_id");
				empId=0l;
				String sevaarthId = (String) employeeMap.get("sevaarth_id");
                if(value instanceof Double) {
                 double value2 = Double.valueOf(value.toString());
                 if(value2>0) {
                	 isAmountGreaterThanZero=true;
                }
                if(value instanceof Integer) {
                    Integer value3 = Integer.valueOf(value.toString());
                    if(value3>0) {
                   	 isAmountGreaterThanZero=true;
                   }
                    }
               
                if(isAmountGreaterThanZero) {
                	
				List<DeptEligibilityForAllowAndDeductEntity> duplicateList = lstDeptEligibilityForAllowAndDeductEntity.stream().filter(s -> s.getDepartmentAllowdeducColNm().toLowerCase().equals(key.toLowerCase())).collect(Collectors.toList());
				if (duplicateList.size() > 0) {

					deptAllowDeducCode = duplicateList.get(0).getDepartmentAllowdeducCode();

					if (deptAllowDeducCode != null) {

						emplyeeComponentMappingloginService.mapComonent(empId, sevaarthId, deptAllowDeducCode);
					}
				}
			}
		}
		}
	}
		}

}
