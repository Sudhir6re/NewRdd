package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.MstEmployeeEntity;

public interface UpdatePanNoRepo {

	List<Object[]> findAllEmployee(String userName);

	MstEmployeeEntity findEmpData(Long employeeId);

	//Serializable saveupdatePANhistory(UpdatePanNoHistryEntity updatePanNoHistryEntity);

	Serializable saveupdateMobNo(MstEmployeeEntity mstEmployeeEntity);

}
