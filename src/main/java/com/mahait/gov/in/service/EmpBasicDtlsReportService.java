package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.model.RegularReportModel;

public interface EmpBasicDtlsReportService {

	List<MstEmployeeEntity> lstDDOWiseEmp(String ddoCode);

	List<RegularReportModel> findEmpBasicDtls(Integer yearId, Integer monthId, Long billGroup, String ddoCode, String sevaarthId);

	
	

}
