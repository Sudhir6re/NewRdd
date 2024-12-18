package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.MstDepartmentEntity;
import com.mahait.gov.in.model.ZpAdminOfficeMstModel;

public interface DepartmentMasterService {

	List<MstDepartmentEntity> listOfDept();

	List<ZpAdminOfficeMstModel> lstadminOfc();

}
