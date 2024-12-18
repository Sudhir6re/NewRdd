package com.mahait.gov.in.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.repository.EmplyeeComponentMappingloginRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class EmplyeeComponentMappingloginServiceImpl implements EmplyeeComponentMappingloginService {
	
	
	@Autowired
	EmplyeeComponentMappingloginRepository emplyeeComponentMappingloginRepository;

	@Override
	public List<DeptEligibilityForAllowAndDeductEntity> findAllComponentDetails() {
		// TODO Auto-generated method stub
		return emplyeeComponentMappingloginRepository.findAllComponentDetails();
	}

	@Override
	public List<Map<String, Object>> getEmployeeList() {
		return emplyeeComponentMappingloginRepository.getEmployeeList();
	}

	@Override
	public void mapComonent(Long empId, String sevaarthId, Integer deptAllowDeducCode) {
		 emplyeeComponentMappingloginRepository.mapComonent(empId,sevaarthId,deptAllowDeducCode);
		}

	
}
