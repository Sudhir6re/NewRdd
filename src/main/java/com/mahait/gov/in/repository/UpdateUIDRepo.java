package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.MstEmployeeEntity;

public interface UpdateUIDRepo {

	List<Object[]> findAllEmployee(String userName);

	MstEmployeeEntity findEmpData(Long empid);

	Serializable saveupdateMobNo(MstEmployeeEntity mstEmployeeEntity);


}
