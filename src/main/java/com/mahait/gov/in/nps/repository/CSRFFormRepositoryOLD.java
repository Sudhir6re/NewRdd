package com.mahait.gov.in.nps.repository;

import java.util.List;

import com.mahait.gov.in.entity.EmployeeDetailEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.nps.entity.FormS1DetailsEntity;

public interface CSRFFormRepositoryOLD {

	List<Object[]> findAllEmployees(String ddoCode);

	FormS1DetailsEntity findEmployeeBySevaarthId(int empId);

	MstEmployeeEntity findEmployeeDtlsBySevaarthId(int empId);


}
