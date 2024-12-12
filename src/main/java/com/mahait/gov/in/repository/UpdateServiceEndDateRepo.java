package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.MstEmployeeEntity;

public interface UpdateServiceEndDateRepo {

	List<Object[]> findAllEmployee(String userName);

	MstEmployeeEntity findEmpData(Long employeeId);

	Serializable saveupdateSED(MstEmployeeEntity mstEmployeeEntity);

}
