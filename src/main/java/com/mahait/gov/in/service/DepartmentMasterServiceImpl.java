package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstDepartmentEntity;
import com.mahait.gov.in.model.ZpAdminOfficeMstModel;
import com.mahait.gov.in.repository.DepartmentMasterRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartmentMasterServiceImpl implements DepartmentMasterService {
	
	@Autowired
	DepartmentMasterRepo departmentMasterRepo; 

	@Override
	public List<MstDepartmentEntity> listOfDept() {
		// TODO Auto-generated method stub
		return departmentMasterRepo.listOfDept();
	}

	@Override
	public List<ZpAdminOfficeMstModel> lstadminOfc() {
		List<Object[]> lstprop = departmentMasterRepo.lstadminOfc();
		List<ZpAdminOfficeMstModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				ZpAdminOfficeMstModel obj = new ZpAdminOfficeMstModel();
				obj.setOfficeCode(StringHelperUtils.isNullString(objLst[0]));
				obj.setOfficeName(StringHelperUtils.isNullString(objLst[1]));
				lstObj.add(obj);
			}
		}
		return lstObj;

	}

}
