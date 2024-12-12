package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.MstEmployeeEntity;

public interface UpdateDOBRepo {

	List<Object[]> findAllEmployee(String userName);

	MstEmployeeEntity findEmpData(Long employeeId);

	Serializable saveupdatedob(MstEmployeeEntity mstEmployeeEntity);

	List<Object[]> getEmpDobBySevaarthId(String sevaarthId);

}
