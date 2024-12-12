package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.MstEmployeeEntity;

public interface EmpChangeBasicDtlsRepo {

	List<Object[]> findEmpChangeBasicDtls(String ddo);

	 Serializable saveChangeBasicdtls(MstEmployeeEntity mstEmployeeEntity);

	MstEmployeeEntity findEmpData(Long employeeId);
	


}
