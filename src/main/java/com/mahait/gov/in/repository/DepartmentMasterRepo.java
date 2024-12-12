package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.MstDepartmentEntity;

public interface DepartmentMasterRepo {

	List<MstDepartmentEntity> listOfDept();

	List<Object[]> lstadminOfc();

}
