package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.MstEmployeeEntity;

public interface EmpBasicDtlsReportRepo {

	List<MstEmployeeEntity> lstDDOWiseEmp(String ddoCode);

	List<Object[]> findEmpBasicDtls(Integer yearId, Integer monthId, Long billGroup, String ddoCode, String sevaarthId);

}
